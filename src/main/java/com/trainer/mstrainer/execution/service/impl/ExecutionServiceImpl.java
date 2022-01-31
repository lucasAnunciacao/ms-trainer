package com.trainer.mstrainer.execution.service.impl;

import com.trainer.mstrainer.execution.model.ExecutionModel;
import com.trainer.mstrainer.execution.repository.ExecutionRepository;
import com.trainer.mstrainer.execution.service.ExecutionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ExecutionServiceImpl implements ExecutionService {

    @Autowired
    private ExecutionRepository executionRepository;

    public ExecutionServiceImpl(ExecutionRepository executionRepository) {
        this.executionRepository = executionRepository;
    }

    @Override
    public List<ExecutionModel> findAll() {
        return executionRepository.findAll();
    }

    @Override
    public Optional<ExecutionModel> findById(UUID id) {
        return executionRepository.findById(id);
    }

    @Override
    public ExecutionModel update(ExecutionModel executionModel) {
        return executionRepository.save(executionModel);
    }

    @Override
    public void delete(ExecutionModel executionModel) {
        executionRepository.delete(executionModel);
    }
}
