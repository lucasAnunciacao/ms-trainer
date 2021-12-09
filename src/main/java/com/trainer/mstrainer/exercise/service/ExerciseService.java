package com.trainer.mstrainer.exercise.service;

import com.trainer.mstrainer.exercise.model.ExerciseModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface ExerciseService {

    List<ExerciseModel> findAll();
    Optional<ExerciseModel> findById(UUID id);
    ExerciseModel update(ExerciseModel exerciseModel);
    void delete(ExerciseModel exerciseModel);
}
