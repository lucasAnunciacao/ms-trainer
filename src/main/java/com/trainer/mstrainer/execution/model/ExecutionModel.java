package com.trainer.mstrainer.execution.model;

import com.trainer.mstrainer.exercise.model.ExerciseModel;
import com.trainer.mstrainer.training.model.TrainingModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "execution")
public class ExecutionModel {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false, referencedColumnName = "id")
    private ExerciseModel exerciseModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false, referencedColumnName = "id")
    private TrainingModel trainingModel;

    @Column(name = "repetion")
    private Integer repetion;

    @Column(name = "intensity")
    private String intensity;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ExerciseModel getExerciseModel() {
        return exerciseModel;
    }

    public void setExerciseModel(ExerciseModel exerciseModel) {
        this.exerciseModel = exerciseModel;
    }

    public TrainingModel getTrainingModel() {
        return trainingModel;
    }

    public void setTrainingModel(TrainingModel trainingModel) {
        this.trainingModel = trainingModel;
    }

    public Integer getRepetion() {
        return repetion;
    }

    public void setRepetion(Integer repetion) {
        this.repetion = repetion;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }
}
