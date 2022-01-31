package com.trainer.mstrainer.realization.mother;

import com.trainer.mstrainer.realization.model.RealizationModel;

import java.util.Optional;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;

import static com.trainer.mstrainer.composition.mother.CompositionMother.createComposition;
import static com.trainer.mstrainer.training.mother.TrainingMother.CLIENT_UUID;
import static com.trainer.mstrainer.util.Util.dateFormat;

public class RealizationMother {
    public static final String REALIZATION_UUID = "a896698d-d545-41d7-a54f-0452b11cce86";

    public static RealizationModel createRealization() {

        RealizationModel realizationModel = new RealizationModel();
        realizationModel.setRealizationDate(dateFormat("12-16-2021"));
        realizationModel.setId(UUID.fromString(REALIZATION_UUID));
        realizationModel.setCompositionModel(createComposition());
        realizationModel.setClientId(UUID.fromString(CLIENT_UUID));

        return realizationModel;
    }

    public static Optional<RealizationModel> createRealizationOptional() {
        Optional<RealizationModel> realizationModelOptional = Optional.of(new RealizationModel());
        realizationModelOptional.get().setRealizationDate(dateFormat("12-16-2021"));
        realizationModelOptional.get().setId(UUID.fromString(REALIZATION_UUID));
        realizationModelOptional.get().setCompositionModel(createComposition());
        realizationModelOptional.get().setClientId(UUID.fromString(CLIENT_UUID));
        return realizationModelOptional;
    }

    public static Set<RealizationModel> createRealizationList() {
        Set<RealizationModel> realizationModel = new HashSet<>();
        realizationModel.add(createRealization());

        return realizationModel;
    }
}
