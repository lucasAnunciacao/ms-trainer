package com.trainer.mstrainer.training.mother;

import com.trainer.mstrainer.training.model.TrainingModel;

import java.util.Optional;
import java.util.UUID;

import static com.trainer.mstrainer.realization.mother.RealizationMother.createRealizationList;
import static com.trainer.mstrainer.util.Util.dateFormat;

public class TrainingMother {
    public static final String TRAINING_UUID = "7ceb84c4-73ee-4f4b-b8d1-ac3e335f3471";
    public static final String PROFESSOR_UUID = "0e96ba2e-59e7-4087-88d6-f37c9c1b85a7";
    public static final String CLIENT_UUID = "0baaa224-b55f-49ac-8de3-7417b3d27e95";

    public static TrainingModel createTraining() {

        TrainingModel trainingModel = new TrainingModel();
        trainingModel.setId(UUID.fromString(TRAINING_UUID));
        trainingModel.setDescription("descricao");
        trainingModel.setCreateDate(dateFormat("12-16-2021"));
        trainingModel.setClient(UUID.fromString(CLIENT_UUID));
        trainingModel.setProfessor(UUID.fromString(PROFESSOR_UUID));
        trainingModel.setFrequency(12);
        trainingModel.setRealizationModelSet(createRealizationList());

        return trainingModel;
    }

    public static Optional<TrainingModel> createTrainingOptional() {
        Optional<TrainingModel> trainingModelOptional = Optional.of(new TrainingModel());
        trainingModelOptional.get().setId(UUID.fromString(TRAINING_UUID));
        trainingModelOptional.get().setDescription("descricao");
        trainingModelOptional.get().setCreateDate(dateFormat("12-16-2021"));
        trainingModelOptional.get().setClient(UUID.fromString(CLIENT_UUID));
        trainingModelOptional.get().setProfessor(UUID.fromString(PROFESSOR_UUID));
        trainingModelOptional.get().setFrequency(12);
        trainingModelOptional.get().setRealizationModelSet(createRealizationList());

        return trainingModelOptional;
    }
}
