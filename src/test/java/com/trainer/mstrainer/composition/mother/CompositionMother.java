package com.trainer.mstrainer.composition.mother;

import com.trainer.mstrainer.composition.model.CompositionModel;

import java.util.Optional;
import java.util.UUID;

public class CompositionMother {
    public static final String COMPOSITION_UUID = "a896698d-d545-41d7-a54f-0452b11cce86";

    public static CompositionModel createComposition() {
        CompositionModel compositionModel = new CompositionModel();
        compositionModel.setId(UUID.fromString(COMPOSITION_UUID));
        compositionModel.setDescription("description");
        compositionModel.setCategory("category");
        return compositionModel;
    }

    public static Optional<CompositionModel> createCompositionOptional() {
        Optional<CompositionModel> compositionModelOptional = Optional.of(new CompositionModel());
        compositionModelOptional.get().setId(UUID.fromString(COMPOSITION_UUID));
        compositionModelOptional.get().setCategory("category");
        compositionModelOptional.get().setDescription("description");
        return compositionModelOptional;
    }
}
