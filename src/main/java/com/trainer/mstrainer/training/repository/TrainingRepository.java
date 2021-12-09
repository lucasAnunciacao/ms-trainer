package com.trainer.mstrainer.training.repository;

import com.trainer.mstrainer.training.model.TrainingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TrainingRepository extends JpaRepository<TrainingModel, UUID> {
}
