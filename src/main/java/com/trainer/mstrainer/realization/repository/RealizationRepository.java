package com.trainer.mstrainer.realization.repository;

import com.trainer.mstrainer.realization.model.RealizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RealizationRepository extends JpaRepository<RealizationModel, UUID> {
}
