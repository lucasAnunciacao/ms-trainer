package com.trainer.mstrainer.exercise.repository;

import com.trainer.mstrainer.exercise.model.ExerciseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExerciseRepository extends JpaRepository<ExerciseModel, UUID> {
}
