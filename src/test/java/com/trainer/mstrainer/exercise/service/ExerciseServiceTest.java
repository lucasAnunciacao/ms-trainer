package com.trainer.mstrainer.exercise.service;

import com.trainer.mstrainer.exercise.model.ExerciseModel;
import com.trainer.mstrainer.exercise.repository.ExerciseRepository;
import com.trainer.mstrainer.exercise.service.impl.ExerciseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import static com.trainer.mstrainer.exercise.mother.ExerciseMother.createExercise;
import static com.trainer.mstrainer.exercise.mother.ExerciseMother.EXERCISE_UUID;
import static com.trainer.mstrainer.exercise.mother.ExerciseMother.createExerciseOptional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ExerciseServiceTest {

    @Mock
    ExerciseService exerciseService;

    @MockBean
    ExerciseRepository exerciseRepository;

    @BeforeEach
    public void setUp() {
        this.exerciseService = new ExerciseServiceImpl(exerciseRepository);
    }

    @Test
    public void saveExerciseTest() {
        ExerciseModel exerciseModel = createExercise();
        when(exerciseRepository.save(exerciseModel)).thenReturn(exerciseModel);

        ExerciseModel update = exerciseService.update(exerciseModel);

        assertThat(update.getId()).isEqualTo(UUID.fromString(EXERCISE_UUID));
        assertThat(update.getGroupExercise()).isEqualTo(createExercise().getGroupExercise());
        assertThat(update.getPhotoUrl()).isEqualTo(createExercise().getPhotoUrl());
    }

    @Test
    public void getByIdExerciseTest() {
        Optional<ExerciseModel> exerciseModel = createExerciseOptional();
        when(exerciseRepository.findById(UUID.fromString(EXERCISE_UUID))).thenReturn(exerciseModel);

        Optional<ExerciseModel> getExerciseById = exerciseService.findById(UUID.fromString(EXERCISE_UUID));

        assertThat(getExerciseById.isPresent()).isTrue();
        assertThat(getExerciseById.get().getId()).isEqualTo(UUID.fromString(EXERCISE_UUID));
        assertThat(getExerciseById.get().getGroupExercise()).isEqualTo(createExercise().getGroupExercise());
        assertThat(getExerciseById.get().getPhotoUrl()).isEqualTo(createExercise().getPhotoUrl());
    }

    @Test
    public void findAllExerciseTest() {
        List<ExerciseModel> exerciseModelList = new ArrayList<>();
        exerciseModelList.add(createExercise());

        when(exerciseRepository.findAll()).thenReturn(exerciseModelList);

        List<ExerciseModel> findallExercise = exerciseService.findAll();

        assertThat(findallExercise.get(0).getId()).isEqualTo(UUID.fromString(EXERCISE_UUID));
        assertThat(findallExercise.get(0).getGroupExercise()).isEqualTo(createExercise().getGroupExercise());
        assertThat(findallExercise.get(0).getPhotoUrl()).isEqualTo(createExercise().getPhotoUrl());
    }

    @Test
    public void deleteExerciseTest() {
        ExerciseModel exerciseModel = createExercise();

        exerciseService.delete(exerciseModel);

        verify(exerciseRepository, times(1)).delete(exerciseModel);
    }
}