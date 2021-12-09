package com.trainer.mstrainer.exercise.controller;

import com.trainer.mstrainer.exercise.model.ExerciseModel;
import com.trainer.mstrainer.exercise.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class ExerciseController {

    @Autowired
    private ExerciseService exerciseService;

    @RequestMapping(value = "/exercise", method = RequestMethod.GET)
    public ResponseEntity<List<ExerciseModel>> findAll() {
        List<ExerciseModel> exerciseModelList = exerciseService.findAll();
        return ResponseEntity.ok(exerciseModelList);
    }

    @RequestMapping(value = "/exercise/{id}", method = RequestMethod.GET)
    public ResponseEntity<ExerciseModel> findById(@PathVariable UUID id) {
        Optional<ExerciseModel> optionalExerciseModel = exerciseService.findById(id);
        return ResponseEntity.ok(optionalExerciseModel.orElse(null));
    }


    @RequestMapping(value = "/exercise", method = RequestMethod.POST)
    public ResponseEntity<ExerciseModel> create(@RequestBody ExerciseModel exerciseModel) {
        ExerciseModel exerciseModelResponse = exerciseService.update(exerciseModel);
        return ResponseEntity.ok(exerciseModelResponse);
    }

    @RequestMapping(value = "/exercise", method = RequestMethod.PUT)
    public ResponseEntity<ExerciseModel> update(@RequestBody ExerciseModel exerciseModel) {
        ExerciseModel exerciseModelResponse = exerciseService.update(exerciseModel);
        return ResponseEntity.ok(exerciseModelResponse);
    }

    @RequestMapping(value = "/exercise/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        ExerciseModel exerciseModel = new ExerciseModel();
        exerciseModel.setId(UUID.fromString(id));
        exerciseService.delete(exerciseModel);
    }
}
