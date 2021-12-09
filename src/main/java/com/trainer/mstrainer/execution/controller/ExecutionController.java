package com.trainer.mstrainer.execution.controller;

import com.trainer.mstrainer.execution.model.ExecutionModel;
import com.trainer.mstrainer.execution.service.ExecutionService;
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
public class ExecutionController {
    @Autowired
    private ExecutionService executionService;

    @RequestMapping(value = "/execution", method = RequestMethod.GET)
    public ResponseEntity<List<ExecutionModel>> findAll() {
        List<ExecutionModel> executionModelList = executionService.findAll();
        return ResponseEntity.ok(executionModelList);
    }

    @RequestMapping(value = "/execution/{id}", method = RequestMethod.GET)
    public ResponseEntity<ExecutionModel> findById(@PathVariable UUID id) {
        Optional<ExecutionModel> optionalExecutionModel = executionService.findById(id);
        return ResponseEntity.ok(optionalExecutionModel.orElse(null));
    }


    @RequestMapping(value = "/execution", method = RequestMethod.POST)
    public ResponseEntity<ExecutionModel> create(@RequestBody ExecutionModel executionModel) {
        ExecutionModel executionModelResponse = executionService.update(executionModel);
        return ResponseEntity.ok(executionModelResponse);
    }

    @RequestMapping(value = "/execution", method = RequestMethod.PUT)
    public ResponseEntity<ExecutionModel> update(@RequestBody ExecutionModel executionModel) {
        ExecutionModel executionModelResponse = executionService.update(executionModel);
        return ResponseEntity.ok(executionModelResponse);
    }

    @RequestMapping(value = "/execution/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String id) {
        ExecutionModel executionModel = new ExecutionModel();
        executionModel.setId(UUID.fromString(id));
        executionService.delete(executionModel);
    }
}
