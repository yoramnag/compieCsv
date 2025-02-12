package com.example.compieCsv.service;

import com.example.compieCsv.entity.Players;
import com.example.compieCsv.repository.PlayersRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
        List<Players> playersList = new ArrayList<Players>();
        try (InputStreamReader reader = new InputStreamReader(file.getInputStream())) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);

            // Loop through each record and save it to the database
            for (CSVRecord record : records) {
                Players player = new Players();
                player.setId(Integer.parseInt(record.get("id")));
                player.setNickname(String.valueOf(record.get("nickname")));
                playersList.add(player);
                playersRepository.save(player);
            }
        }
    }
}
