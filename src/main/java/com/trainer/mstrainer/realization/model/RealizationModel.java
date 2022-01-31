package com.trainer.mstrainer.realization.model;

import com.trainer.mstrainer.composition.model.CompositionModel;
import com.trainer.mstrainer.training.model.TrainingModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "realization")
public class RealizationModel {

    @Id
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false, referencedColumnName = "id")
    private TrainingModel trainingModel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "composition_id", nullable = false, referencedColumnName = "id")
    private CompositionModel compositionModel;

    @Column(name = "realization_date")
    private Date realizationDate;

    @Column(name = "client_id")
    private UUID clientId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CompositionModel getCompositionModel() {
        return compositionModel;
    }

    public void setCompositionModel(CompositionModel compositionModel) {
        this.compositionModel = compositionModel;
    }

    public Date getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }
}
