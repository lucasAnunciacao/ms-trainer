package com.trainer.mstrainer.exercise.service.impl;

import com.trainer.mstrainer.exercise.model.ExerciseModel;
import com.trainer.mstrainer.exercise.repository.ExerciseRepository;
import com.trainer.mstrainer.exercise.service.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    @Autowired
    private ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public List<ExerciseModel> findAll() {
        return exerciseRepository.findAll();
    }

    @Override
    public Optional<ExerciseModel> findById(UUID id) {
        return exerciseRepository.findById(id);
    }

    @Override
    public ExerciseModel update(ExerciseModel exerciseModel) {
        return exerciseRepository.save(exerciseModel);
    }

    @Override
    public void delete(ExerciseModel exerciseModel) {
        exerciseRepository.delete(exerciseModel);
    }
}
