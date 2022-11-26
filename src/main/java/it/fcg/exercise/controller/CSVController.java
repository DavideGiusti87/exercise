package it.fcg.exercise.controller;

import it.fcg.exercise.entity.User;
import it.fcg.exercise.service.CSVService;
import it.fcg.exercise.utils.CSVHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping("/api/csv")
public class CSVController {
    @Autowired
    CSVService service;

    @PostMapping("/upload")
    public ResponseEntity uploadFile(@RequestParam("file") MultipartFile file) {

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                service.save(file);

                return ResponseEntity.status(HttpStatus.OK).body(String.format("Uploaded the file successfully: %s",file.getOriginalFilename()));
            } catch (Exception e) {

                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(String.format("Could not upload the file: %s!",file.getOriginalFilename()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload a csv file!");
    }
}
