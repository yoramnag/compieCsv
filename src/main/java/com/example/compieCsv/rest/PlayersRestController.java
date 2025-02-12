package com.example.compieCsv.rest;

import com.example.compieCsv.service.PlayersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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


}
