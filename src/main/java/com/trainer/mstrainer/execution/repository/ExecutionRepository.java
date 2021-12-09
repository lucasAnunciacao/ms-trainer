package com.trainer.mstrainer.execution.repository;

import com.trainer.mstrainer.execution.model.ExecutionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExecutionRepository extends JpaRepository<ExecutionModel, UUID> {
}
