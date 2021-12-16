package com.trainer.mstrainer.training.service.impl;

import com.trainer.mstrainer.training.model.TrainingModel;
import com.trainer.mstrainer.training.repository.TrainingRepository;
import com.trainer.mstrainer.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrainingServiceImpl implements TrainingService {

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public List<TrainingModel> findAll() {
        return trainingRepository.findAll();
    }

    @Override
    public Optional<TrainingModel> findById(UUID id) {
        return trainingRepository.findById(id);
    }

    @Override
    public TrainingModel update(TrainingModel trainingModel) {
        return trainingRepository.save(trainingModel);
    }

    @Override
    public void delete(TrainingModel trainingModel) {
        trainingRepository.delete(trainingModel);
    }
}
