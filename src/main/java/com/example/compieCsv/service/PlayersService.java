package com.example.compieCsv.service;

import com.example.compieCsv.entity.Players;
import com.example.compieCsv.exception.PlayerNotFoundException;
import com.example.compieCsv.repository.PlayersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.web.client.RestTemplate;

@Service
public class PlayersService {

    @Autowired
    private PlayersRepository playersRepository;

    public void loadCsvFromResource(String fileName) throws IOException {
        // Get the CSV file from the resources folder
        try (InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("csv/" + fileName))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);

            // Loop through each record and save it to the database
            for (CSVRecord record : records) {
                Players player = new Players();
                player.setId(Integer.parseInt(record.get("id")));
                player.setNickname(record.get("nickname"));

                playersRepository.save(player);
            }
        }
    }

    public void loadCsvFromMultipartFile(MultipartFile file) throws Exception {
        // Read the CSV file from the MultipartFile
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);

            // Loop through each record and save it to the database
            for (CSVRecord record : records) {
                Players player = new Players();
                player.setId(Integer.parseInt(record.get("id")));
                player.setNickname(String.valueOf(record.get("nickname")));
                playersRepository.save(player);
            }
        }
    }

    public String getPlayersInfo(Players player) {
        ResponseEntity<String> response = callBalldontlie(String.valueOf(player.getId()));
        JsonObject jsonObject = JsonParser.parseString(String.valueOf(response.getBody()))
                .getAsJsonObject();
        JsonObject jsonObjectData = JsonParser.parseString(String.valueOf(jsonObject.get("data")))
                .getAsJsonObject();
        mapJsonToPlayer(jsonObjectData,player);

        System.out.println(jsonObjectData);
        return jsonObjectData.toString();
    }

    private void mapJsonToPlayer(JsonObject jsonObjectData, Players player) {
        String nickname = player.getNickname();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            player = objectMapper.readValue(jsonObjectData.toString(), Players.class);
            player.setNickname(nickname);
            playersRepository.save(player);
            System.out.println(player);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private ResponseEntity<String> callBalldontlie(String playerId) {
        // request url
        String url = "https://api.balldontlie.io/v1/players/{id}";
        // create an instance of RestTemplate
        RestTemplate restTemplate = new RestTemplate();
        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `Content-Type` and `Accept` headers
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("Authorization", "e23d8783-157c-4380-9c31-a30e7f56c8a6");
        // build the request
        HttpEntity request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class,
                playerId
        );
        return response;
    }

    public void updatePlayerInfoFromBalldontlie() {
        List<Players> playersList=new ArrayList<Players>();
        playersList = playersRepository.findAll();
        if (playersList.size() > 0){
            for (int i = 0; i < playersList.size(); i++) {
                getPlayersInfo(playersList.get(i));
            }
        }
    }

    public List<Players> findAll() {
        return playersRepository.findAll();
    }

    public void exportPlayersToCSV(List<Players> playersList, String filePath) throws IOException {
        CSVWriter writer = new CSVWriter(new FileWriter(filePath));
        String[] header = {"id", "nickname", "first_name", "last_name", "position", "height", "weight", "jersey_number", "college", "country", "draft_year", "draft_round", "draft_number", "team_id", "team_conference", "team_division", "team_city", "team_name", "team_full_name", "team_abbreviation"};
        writer.writeNext(header);

        for (Players player : playersList) {
            String[] data = {
                    String.valueOf(player.getId()),
                    player.getNickname(),
                    player.getFirst_name(),
                    player.getLast_name(),
                    player.getPosition(),
                    player.getHeight(),
                    player.getWeight(),
                    player.getJersey_number(),
                    player.getCollege(),
                    player.getCountry(),
                    player.getDraft_year(),
                    player.getDraft_round(),
                    player.getDraft_number(),
                    String.valueOf(player.getTeam().getId()),
                    player.getTeam().getConference(),
                    player.getTeam().getDivision(),
                    player.getTeam().getCity(),
                    player.getTeam().getName(),
                    player.getTeam().getFull_name(),
                    player.getTeam().getAbbreviation()
            };

            // Write player data to CSV
            writer.writeNext(data);
        }

        writer.close();
    }
}
