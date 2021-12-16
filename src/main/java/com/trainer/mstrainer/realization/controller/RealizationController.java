package com.trainer.mstrainer.realization.controller;

import com.trainer.mstrainer.realization.model.RealizationModel;
import com.trainer.mstrainer.realization.service.RealizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1")
public class RealizationController {

    @Autowired
    private RealizationService realizationService;

    @RequestMapping(value = "/realization", method = RequestMethod.GET)
    public ResponseEntity<List<RealizationModel>> findAll() {
        List<RealizationModel> realizationModelList = realizationService.findAll();
        return ResponseEntity.ok(realizationModelList);
    }

    @RequestMapping(value = "/realization/{id}", method = RequestMethod.GET)
    public ResponseEntity<RealizationModel> findById(@PathVariable UUID id) {
        Optional<RealizationModel> optionalrealizationModel = realizationService.findById(id);
        return ResponseEntity.ok(optionalrealizationModel.orElse(null));
    }


    @RequestMapping(value = "/realization", method = RequestMethod.POST)
    public ResponseEntity<RealizationModel> create(@RequestBody RealizationModel realizationModel) {
        RealizationModel realizationModelResponse = realizationService.update(realizationModel);
        return ResponseEntity.ok(realizationModelResponse);
    }

    @RequestMapping(value = "/realization", method = RequestMethod.PUT)
    public ResponseEntity<RealizationModel> update(@RequestBody RealizationModel realizationModel) {
        RealizationModel realizationModelResponse = realizationService.update(realizationModel);
        return ResponseEntity.ok(realizationModelResponse);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/realization/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        RealizationModel realizationModel = new RealizationModel();
        realizationModel.setId(UUID.fromString(id));
        realizationService.delete(realizationModel);
    }
}
