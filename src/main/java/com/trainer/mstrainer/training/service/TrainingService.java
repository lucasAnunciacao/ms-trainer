package com.trainer.mstrainer.training.service;

import com.trainer.mstrainer.training.model.TrainingModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface TrainingService {

    List<TrainingModel> findAll();
    Optional<TrainingModel> findById(UUID id);
    TrainingModel update(TrainingModel trainingModel);
    void delete(TrainingModel trainingModel);
}
