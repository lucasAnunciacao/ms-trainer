package com.trainer.mstrainer.composition.service.impl;

import com.trainer.mstrainer.composition.model.CompositionModel;
import com.trainer.mstrainer.composition.repository.CompositionRepository;
import com.trainer.mstrainer.composition.service.CompositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompositionServiceImpl implements CompositionService {

    @Autowired
    private CompositionRepository compositionRepository;

    public CompositionServiceImpl(CompositionRepository compositionRepository) {
        this.compositionRepository = compositionRepository;
    }

    @Override
    public List<CompositionModel> findAll() {
        return compositionRepository.findAll();
    }

    @Override
    public Optional<CompositionModel> findById(UUID id) {
        return compositionRepository.findById(id);
    }

    @Override
    public CompositionModel update(CompositionModel compositionModel) {
        return compositionRepository.save(compositionModel);
    }

    @Override
    public void delete(CompositionModel compositionModel) {
        compositionRepository.delete(compositionModel);
    }
}
