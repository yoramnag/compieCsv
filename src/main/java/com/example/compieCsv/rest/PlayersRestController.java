package com.example.compieCsv.rest;

import com.example.compieCsv.constants.PlayersConstants;
import com.example.compieCsv.dto.ResponseDto;
import com.example.compieCsv.entity.Players;
import com.example.compieCsv.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/api" , produces = MediaType.APPLICATION_JSON_VALUE)
public class PlayersRestController {

    @Autowired
    private PlayersService playersService;

    // Upload CSV from MultipartFile
    @PostMapping("/upload-csv")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            playersService.loadCsvFromMultipartFile(file);
            return ResponseEntity.ok("CSV Data Uploaded and Stored Successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading CSV file");
        }
    }

    // Load CSV from the resources folder
    @GetMapping("/load-players-csv-from-resource/{fileName}")
    public ResponseEntity<String> loadCsvFromResource(@PathVariable String fileName) {
        try {
            playersService.loadCsvFromResource(fileName);
            return ResponseEntity.ok("CSV Data Loaded from Resources Successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error loading CSV file from resources");
        }
    }

//    @Scheduled(cron = "0 0/15 * * * *")
    @GetMapping("/exportToCSV")
    public ResponseEntity<ResponseDto> exportToCSV(){
        playersService.getPlayersCsv();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(PlayersConstants.STATUS_201,PlayersConstants.MESSAGE_201));
    }

    @GetMapping("/getAllPlayers")
    public ResponseEntity<List<Players>> getAllCoordinates(){
        List<Players> playersListList = playersService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(playersListList);
    }






}
