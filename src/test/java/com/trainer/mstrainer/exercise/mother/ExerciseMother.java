package com.trainer.mstrainer.exercise.mother;

import com.trainer.mstrainer.exercise.model.ExerciseModel;

import java.util.Optional;
import java.util.UUID;

public class ExerciseMother {
    public static final String EXERCISE_UUID = "a896698d-d545-41d7-a54f-0452b11cce86";

    public static ExerciseModel createExercise() {
        ExerciseModel exerciseModel = new ExerciseModel();
        exerciseModel.setId(UUID.fromString(EXERCISE_UUID));
        exerciseModel.setGroupExercise("group exercise");
        exerciseModel.setPhotoUrl("photo url");
        return exerciseModel;
    }

    public static Optional<ExerciseModel> createExerciseOptional() {
        Optional<ExerciseModel> exerciseModelOptional = Optional.of(new ExerciseModel());
        exerciseModelOptional.get().setId(UUID.fromString(EXERCISE_UUID));
        exerciseModelOptional.get().setGroupExercise("group exercise");
        exerciseModelOptional.get().setPhotoUrl("photo url");
        return exerciseModelOptional;
    }
}
