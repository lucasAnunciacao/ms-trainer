package com.trainer.mstrainer.composition.repository;

import com.trainer.mstrainer.composition.model.CompositionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompositionRepository extends JpaRepository<CompositionModel, UUID> {
}
