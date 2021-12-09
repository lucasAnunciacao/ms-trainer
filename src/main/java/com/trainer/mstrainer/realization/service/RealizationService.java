package com.trainer.mstrainer.realization.service;

import com.trainer.mstrainer.realization.model.RealizationModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface RealizationService {

    List<RealizationModel> findAll();
    Optional<RealizationModel> findById(UUID id);
    RealizationModel update(RealizationModel realizationModel);
    void delete(RealizationModel realizationModel);
}
