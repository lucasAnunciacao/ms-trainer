package com.trainer.mstrainer.training.controller;

import com.trainer.mstrainer.training.model.TrainingModel;
import com.trainer.mstrainer.training.service.TrainingService;
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
public class TrainingController {
    @Autowired
    private TrainingService trainingService;

    @RequestMapping(value = "/training", method = RequestMethod.GET)
    public ResponseEntity<List<TrainingModel>> findAll() {
        List<TrainingModel> trainingModelList = trainingService.findAll();
        return ResponseEntity.ok(trainingModelList);
    }

    @RequestMapping(value = "/training/{id}", method = RequestMethod.GET)
    public ResponseEntity<TrainingModel> findById(@PathVariable UUID id) {
        Optional<TrainingModel> optionalTrainingModel = trainingService.findById(id);
        return ResponseEntity.ok(optionalTrainingModel.orElse(null));
    }


    @RequestMapping(value = "/training", method = RequestMethod.POST)
    public ResponseEntity<TrainingModel> create(@RequestBody TrainingModel trainingModel) {
        TrainingModel trainingModelResponse = trainingService.update(trainingModel);
        return ResponseEntity.ok(trainingModelResponse);
    }

    @RequestMapping(value = "/training", method = RequestMethod.PUT)
    public ResponseEntity<TrainingModel> update(@RequestBody TrainingModel trainingModel) {
        TrainingModel trainingModelResponse = trainingService.update(trainingModel);
        return ResponseEntity.ok(trainingModelResponse);
    }

    @RequestMapping(value = "/training/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        TrainingModel trainingModel = new TrainingModel();
        trainingModel.setId(UUID.fromString(id));
        trainingService.delete(trainingModel);
    }
}
