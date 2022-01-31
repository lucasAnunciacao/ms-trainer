package com.trainer.mstrainer.composition.controller;

import com.trainer.mstrainer.composition.model.CompositionModel;
import com.trainer.mstrainer.composition.service.CompositionService;
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
public class CompositionController {

    @Autowired
    private CompositionService compositionService;

    @RequestMapping(value = "/composition", method = RequestMethod.GET)
    public ResponseEntity<List<CompositionModel>> findAll() {
        List<CompositionModel> compositionModelList = compositionService.findAll();
        return ResponseEntity.ok(compositionModelList);
    }

    @RequestMapping(value = "/composition/{id}", method = RequestMethod.GET)
    public ResponseEntity<CompositionModel> findById(@PathVariable UUID id) {
        Optional<CompositionModel> optionalCompositionModel = compositionService.findById(id);
        return ResponseEntity.ok(optionalCompositionModel.orElse(null));
    }

    @RequestMapping(value = "/composition", method = RequestMethod.POST)
    public ResponseEntity<CompositionModel> create(@RequestBody CompositionModel compositionModel) {
        CompositionModel compositionModelResponse = compositionService.update(compositionModel);
        return ResponseEntity.ok(compositionModelResponse);
    }

    @RequestMapping(value = "/composition", method = RequestMethod.PUT)
    public ResponseEntity<CompositionModel> update(@RequestBody CompositionModel compositionModel) {
        CompositionModel compositionModelResponse = compositionService.update(compositionModel);
        return ResponseEntity.ok(compositionModelResponse);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/composition/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        CompositionModel compositionModel = new CompositionModel();
        compositionModel.setId(UUID.fromString(id));
        compositionService.delete(compositionModel);
    }
}
