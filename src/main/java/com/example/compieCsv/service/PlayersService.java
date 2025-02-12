package com.example.compieCsv.service;

import com.example.compieCsv.entity.Players;
import com.example.compieCsv.exception.PlayerNotFoundException;
import com.example.compieCsv.repository.PlayersRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
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

    public void updatePlayerInfo(String playerId) {
        Optional<Players> playerOpt=playersRepository.findById(Integer.valueOf(playerId));
        if(!playerOpt.isPresent()){
            throw new PlayerNotFoundException("player","player",playerId);
        }
        ResponseEntity<String> response = callBalldontlie(playerId);
        System.out.println(response);
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
}
