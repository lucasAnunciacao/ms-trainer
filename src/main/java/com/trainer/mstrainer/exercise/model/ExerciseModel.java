package com.trainer.mstrainer.exercise.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "exercise")
public class ExerciseModel {

    @Id
    private UUID id;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "group_exercise")
    private String groupExercise;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getGroupExercise() {
        return groupExercise;
    }

    public void setGroupExercise(String groupExercise) {
        this.groupExercise = groupExercise;
    }
}
