package com.trainer.mstrainer.execution.mother;

import com.trainer.mstrainer.execution.model.ExecutionModel;

import java.util.Optional;
import java.util.UUID;

import static com.trainer.mstrainer.exercise.mother.ExerciseMother.createExercise;
import static com.trainer.mstrainer.training.mother.TrainingMother.createTraining;

public class ExecutionMother {
    public static final String EXECUTION_UUID = "e1d6bd9b-ef2c-4ae3-b6e3-caa801c7cec2";

    public static ExecutionModel createExecution() {
        ExecutionModel executionModel = new ExecutionModel();
        executionModel.setId(UUID.fromString(EXECUTION_UUID));
        executionModel.setExerciseModel(createExercise());
        executionModel.setIntensity("intensity");
        executionModel.setTrainingModel(createTraining());
        executionModel.setIntensity("intensity");
        executionModel.setRepetion(12);

        return executionModel;
    }

    public static Optional<ExecutionModel> createExecutionOptional() {
        Optional<ExecutionModel> executionModelOptional = Optional.of(new ExecutionModel());
        executionModelOptional.get().setId(UUID.fromString(EXECUTION_UUID));
        executionModelOptional.get().setExerciseModel(createExercise());
        executionModelOptional.get().setIntensity("intensity");
        executionModelOptional.get().setTrainingModel(createTraining());
        executionModelOptional.get().setIntensity("intensity");
        executionModelOptional.get().setRepetion(12);
        return executionModelOptional;
    }
}
