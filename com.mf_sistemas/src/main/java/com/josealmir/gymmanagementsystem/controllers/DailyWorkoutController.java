package com.josealmir.gymmanagementsystem.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.josealmir.gymmanagementsystem.model.workoutplan.DailyWorkout;
import com.josealmir.gymmanagementsystem.model.workoutplan.Exercise;
import com.josealmir.gymmanagementsystem.requests.DailyWorkoutRequest;
import com.josealmir.gymmanagementsystem.service.interfaces.DailyWorkoutService;

@RestController
@RequestMapping("/DailyWorkouts")
public class DailyWorkoutController {
    @Autowired
    private DailyWorkoutService dailyWorkoutService;

    @GetMapping
    public ResponseEntity<List<DailyWorkout>> getAllDailyWorkouts() {
        return new ResponseEntity<List<DailyWorkout>>(dailyWorkoutService.allDailyWorkouts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DailyWorkout>> getDailyWorkoutById(@PathVariable String id) {
        return new ResponseEntity<Optional<DailyWorkout>>(dailyWorkoutService.dailyWorkoutById(id), HttpStatus.OK);
    }
    @GetMapping("/day/{dayOfWeek}")
    public ResponseEntity<Optional<DailyWorkout>> getDailyWorkoutByDayOfWeek(@PathVariable String dayOfWeek) {
        return new ResponseEntity<Optional<DailyWorkout>>(dailyWorkoutService.dailyWorkoutByDayOfWeek(dayOfWeek), HttpStatus.OK);
    }
    @PostMapping
    public DailyWorkout createDailyWorkoutPlan(@RequestBody DailyWorkout dailyWorkout) {
        String dayOfWeek = dailyWorkout.getDayOfWeek();
        List<Exercise> exercises = dailyWorkout.getExercises();
        return dailyWorkoutService.createDailyWorkout(dayOfWeek, exercises);
    }
    @PutMapping("/{id}")
    public DailyWorkout updateDailyWorkout(@PathVariable String id, @RequestBody DailyWorkoutRequest dailyWorkoutRequest) {
        return dailyWorkoutService.updateDailyWorkout(id, dailyWorkoutRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDailyWorkout(@PathVariable String id) {
        dailyWorkoutService.deleteDailyWorkout(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
