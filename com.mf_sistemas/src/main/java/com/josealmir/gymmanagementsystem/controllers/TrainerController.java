package com.josealmir.gymmanagementsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josealmir.gymmanagementsystem.model.person.Trainer;
import com.josealmir.gymmanagementsystem.requests.TrainerRequest;
import com.josealmir.gymmanagementsystem.service.interfaces.TrainerService;

@RestController
@RequestMapping("/Trainers")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;

    @GetMapping
    public ResponseEntity<List<Trainer>> getAllTrainers() {
        return new ResponseEntity<List<Trainer>>(trainerService.allTrainers(), HttpStatus.OK);
    }
    @PostMapping
    public Trainer createTrainer(@RequestBody TrainerRequest trainerRequest) {
        String trainerId = trainerRequest.getTrainerId();
        String speciality = trainerRequest.getSpeciality();
        Double salary = trainerRequest.getSalary();
        String certificationNumber = trainerRequest.getCertificationNumber();
        return trainerService.createTrainer(trainerId, speciality, salary, certificationNumber);
    }
}
