package com.trainer.mstrainer.TrainingComposition.model;

import com.trainer.mstrainer.composition.model.CompositionModel;
import com.trainer.mstrainer.training.model.TrainingModel;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "training_composition")
public class TrainingCompostionModel {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "composition_id", nullable = false, referencedColumnName = "id")
    private CompositionModel compositionModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false, referencedColumnName = "id")
    private TrainingModel trainingModel;
}
