package com.trainer.mstrainer.realization.service.impl;

import com.trainer.mstrainer.realization.model.RealizationModel;
import com.trainer.mstrainer.realization.repository.RealizationRepository;
import com.trainer.mstrainer.realization.service.RealizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RealizationServiceImpl implements RealizationService {

    public RealizationServiceImpl(RealizationRepository realizationRepository) {
        this.realizationRepository = realizationRepository;
    }

    @Autowired
    private RealizationRepository realizationRepository;

    @Override
    public List<RealizationModel> findAll() {
        return realizationRepository.findAll();
    }

    @Override
    public Optional<RealizationModel> findById(UUID id) {
        return realizationRepository.findById(id);
    }

    @Override
    public RealizationModel update(RealizationModel realizationModel) {
        return realizationRepository.save(realizationModel);
    }

    @Override
    public void delete(RealizationModel realizationModel) {
        realizationRepository.delete(realizationModel);
    }
}
