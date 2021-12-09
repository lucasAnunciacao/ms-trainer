package com.trainer.mstrainer.training.model;

import com.trainer.mstrainer.TrainingComposition.model.TrainingCompostionModel;
import com.trainer.mstrainer.realization.model.RealizationModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "training")
public class TrainingModel {

    @Id
    private UUID id;

    @Column(name= "description")
    private String description;

    @Column(name= "create_date")
    private Date createDate;

    @Column(name= "frequency")
    private Integer frequency;

    @Column(name= "client_id")
    private UUID client;

    @Column(name= "professor_id")
    private UUID professor;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainingModel"
            , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RealizationModel> realizationModelSet;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "trainingModel"
            , cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TrainingCompostionModel> trainingCompostionModelsSet;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public UUID getClient() {
        return client;
    }

    public void setClient(UUID client) {
        this.client = client;
    }

    public UUID getProfessor() {
        return professor;
    }

    public void setProfessor(UUID professor) {
        this.professor = professor;
    }

    public Set<RealizationModel> getRealizationModelSet() {
        return realizationModelSet;
    }

    public void setRealizationModelSet(Set<RealizationModel> realizationModelSet) {
        this.realizationModelSet = realizationModelSet;
    }
}
