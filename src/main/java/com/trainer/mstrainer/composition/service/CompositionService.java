package com.trainer.mstrainer.composition.service;

import com.trainer.mstrainer.composition.model.CompositionModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface CompositionService {

    List<CompositionModel> findAll();
    Optional<CompositionModel> findById(UUID id);
    CompositionModel update(CompositionModel compositionModel);
    void delete(CompositionModel compositionModel);
}
