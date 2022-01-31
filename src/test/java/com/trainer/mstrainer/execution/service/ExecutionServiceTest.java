package com.trainer.mstrainer.execution.service;

import com.trainer.mstrainer.execution.model.ExecutionModel;
import com.trainer.mstrainer.execution.repository.ExecutionRepository;
import com.trainer.mstrainer.execution.service.impl.ExecutionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.text.ParseException;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

import static com.trainer.mstrainer.execution.mother.ExecutionMother.createExecution;
import static com.trainer.mstrainer.execution.mother.ExecutionMother.EXECUTION_UUID;
import static com.trainer.mstrainer.execution.mother.ExecutionMother.createExecutionOptional;
import static com.trainer.mstrainer.exercise.mother.ExerciseMother.createExercise;
import static com.trainer.mstrainer.training.mother.TrainingMother.createTraining;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ExecutionServiceTest {

    @Mock
    ExecutionService executionService;

    @MockBean
    ExecutionRepository executionRepository;

    @BeforeEach
    public void setUp() {
        this.executionService = new ExecutionServiceImpl(executionRepository);
    }

    @Test
    public void saveExecutionTest() throws ParseException {
        ExecutionModel executionModel = createExecution();
        when(executionRepository.save(executionModel)).thenReturn(executionModel);

        ExecutionModel update = executionService.update(executionModel);

        assertThat(update.getId()).isEqualTo(UUID.fromString(EXECUTION_UUID));
        assertThat(update.getExerciseModel().getGroupExercise()).isEqualTo(createExercise().getGroupExercise());
        assertThat(update.getExerciseModel().getId()).isEqualTo(createExercise().getId());
        assertThat(update.getExerciseModel().getPhotoUrl()).isEqualTo(createExercise().getPhotoUrl());
        assertThat(update.getExerciseModel().getGroupExercise()).isEqualTo(createExercise().getGroupExercise());
        assertThat(update.getTrainingModel().getId()).isEqualTo(createTraining().getId());
        assertThat(update.getTrainingModel().getDescription()).isEqualTo(createTraining().getDescription());
        assertThat(update.getTrainingModel().getClient()).isEqualTo(createTraining().getClient());
        assertThat(update.getTrainingModel().getFrequency()).isEqualTo(createTraining().getFrequency());
        assertThat(update.getTrainingModel().getProfessor()).isEqualTo(createTraining().getProfessor());
        assertThat(update.getTrainingModel().getCreateDate()).isEqualTo(createTraining().getCreateDate());
        assertThat(update.getIntensity()).isEqualTo("intensity");
        assertThat(update.getRepetion()).isEqualTo(12);
    }

    @Test
    public void getByIdExecutionTest() throws ParseException {
        Optional<ExecutionModel> executionModel = createExecutionOptional();
        when(executionRepository.findById(UUID.fromString(EXECUTION_UUID))).thenReturn(executionModel);

        Optional<ExecutionModel> getExecutionById = executionService.findById(UUID.fromString(EXECUTION_UUID));

        assertThat(getExecutionById.get().getId()).isEqualTo(UUID.fromString(EXECUTION_UUID));
        assertThat(getExecutionById.get().getExerciseModel().getGroupExercise()).isEqualTo(createExercise().getGroupExercise());
        assertThat(getExecutionById.get().getExerciseModel().getId()).isEqualTo(createExercise().getId());
        assertThat(getExecutionById.get().getExerciseModel().getPhotoUrl()).isEqualTo(createExercise().getPhotoUrl());
        assertThat(getExecutionById.get().getExerciseModel().getGroupExercise()).isEqualTo(createExercise().getGroupExercise());
        assertThat(getExecutionById.get().getTrainingModel().getId()).isEqualTo(createTraining().getId());
        assertThat(getExecutionById.get().getTrainingModel().getDescription()).isEqualTo(createTraining().getDescription());
        assertThat(getExecutionById.get().getTrainingModel().getClient()).isEqualTo(createTraining().getClient());
        assertThat(getExecutionById.get().getTrainingModel().getFrequency()).isEqualTo(createTraining().getFrequency());
        assertThat(getExecutionById.get().getTrainingModel().getProfessor()).isEqualTo(createTraining().getProfessor());
        assertThat(getExecutionById.get().getTrainingModel().getCreateDate()).isEqualTo(createTraining().getCreateDate());
        assertThat(getExecutionById.get().getIntensity()).isEqualTo("intensity");
        assertThat(getExecutionById.get().getRepetion()).isEqualTo(12);
    }

    @Test
    public void findAllExecutionTest() throws ParseException {
        List<ExecutionModel> executionModelList = new ArrayList<>();
        executionModelList.add(createExecution());

        when(executionRepository.findAll()).thenReturn(executionModelList);

        List<ExecutionModel> findallExecution = executionService.findAll();

        assertThat(findallExecution.get(0).getId()).isEqualTo(UUID.fromString(EXECUTION_UUID));
        assertThat(findallExecution.get(0).getExerciseModel().getGroupExercise()).isEqualTo(createExercise().getGroupExercise());
        assertThat(findallExecution.get(0).getExerciseModel().getId()).isEqualTo(createExercise().getId());
        assertThat(findallExecution.get(0).getExerciseModel().getPhotoUrl()).isEqualTo(createExercise().getPhotoUrl());
        assertThat(findallExecution.get(0).getExerciseModel().getGroupExercise()).isEqualTo(createExercise().getGroupExercise());
        assertThat(findallExecution.get(0).getTrainingModel().getId()).isEqualTo(createTraining().getId());
        assertThat(findallExecution.get(0).getTrainingModel().getDescription()).isEqualTo(createTraining().getDescription());
        assertThat(findallExecution.get(0).getTrainingModel().getClient()).isEqualTo(createTraining().getClient());
        assertThat(findallExecution.get(0).getTrainingModel().getFrequency()).isEqualTo(createTraining().getFrequency());
        assertThat(findallExecution.get(0).getTrainingModel().getProfessor()).isEqualTo(createTraining().getProfessor());
        assertThat(findallExecution.get(0).getTrainingModel().getCreateDate()).isEqualTo(createTraining().getCreateDate());
        assertThat(findallExecution.get(0).getIntensity()).isEqualTo("intensity");
        assertThat(findallExecution.get(0).getRepetion()).isEqualTo(12);
    }

    @Test
    public void deleteExecutionTest() throws ParseException {
        ExecutionModel executionModel = createExecution();

        executionService.delete(executionModel);

        verify(executionRepository, times(1)).delete(executionModel);
    }
}