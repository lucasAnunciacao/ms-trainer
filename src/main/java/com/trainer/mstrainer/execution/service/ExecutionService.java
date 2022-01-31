package com.trainer.mstrainer.execution.service;

import com.trainer.mstrainer.execution.model.ExecutionModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ExecutionService {
    List<ExecutionModel> findAll();

    Optional<ExecutionModel> findById(UUID id);

    ExecutionModel update(ExecutionModel executionModel);

    void delete(ExecutionModel executionModel);
}
