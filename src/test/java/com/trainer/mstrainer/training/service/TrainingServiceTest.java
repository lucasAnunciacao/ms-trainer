package com.trainer.mstrainer.training.service;

import com.trainer.mstrainer.training.model.TrainingModel;
import com.trainer.mstrainer.training.repository.TrainingRepository;
import com.trainer.mstrainer.training.service.impl.TrainingServiceImpl;
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

import static com.trainer.mstrainer.training.mother.TrainingMother.TRAINING_UUID;
import static com.trainer.mstrainer.training.mother.TrainingMother.createTraining;
import static com.trainer.mstrainer.training.mother.TrainingMother.createTrainingOptional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class TrainingServiceTest {

    @Mock
    TrainingService trainingService;

    @MockBean
    TrainingRepository trainingRepository;

    @BeforeEach
    public void setUp() {
        this.trainingService = new TrainingServiceImpl(trainingRepository);
    }

    @Test
    public void saveTrainingTest() {
        TrainingModel trainingModel = createTraining();
        when(trainingRepository.save(trainingModel)).thenReturn(trainingModel);

        TrainingModel update = trainingService.update(trainingModel);

        assertThat(update.getId()).isEqualTo(UUID.fromString(TRAINING_UUID));
        assertThat(update.getClient()).isEqualTo(createTraining().getClient());
        assertThat(update.getProfessor()).isEqualTo(createTraining().getProfessor());
        assertThat(update.getDescription()).isEqualTo(createTraining().getDescription());
        assertThat(update.getFrequency()).isEqualTo(createTraining().getFrequency());
        assertThat(update.getCreateDate()).isEqualTo(createTraining().getCreateDate());
    }

    @Test
    public void getByIdTrainingTest() {
        Optional<TrainingModel> trainingModel = createTrainingOptional();
        when(trainingRepository.findById(UUID.fromString(TRAINING_UUID))).thenReturn(trainingModel);

        Optional<TrainingModel> getTrainingById = trainingService.findById(UUID.fromString(TRAINING_UUID));

        assertThat(getTrainingById.isPresent()).isTrue();
        assertThat(getTrainingById.get().getId()).isEqualTo(UUID.fromString(TRAINING_UUID));
        assertThat(getTrainingById.get().getClient()).isEqualTo(createTraining().getClient());
        assertThat(getTrainingById.get().getProfessor()).isEqualTo(createTraining().getProfessor());
        assertThat(getTrainingById.get().getDescription()).isEqualTo(createTraining().getDescription());
        assertThat(getTrainingById.get().getFrequency()).isEqualTo(createTraining().getFrequency());
        assertThat(getTrainingById.get().getCreateDate()).isEqualTo(createTraining().getCreateDate());
    }

    @Test
    public void findAllTrainingTest() {
        List<TrainingModel> trainingModelList = new ArrayList<>();
        trainingModelList.add(createTraining());

        when(trainingRepository.findAll()).thenReturn(trainingModelList);

        List<TrainingModel> findallTraining = trainingService.findAll();

        assertThat(findallTraining.get(0).getClient()).isEqualTo(createTraining().getClient());
        assertThat(findallTraining.get(0).getProfessor()).isEqualTo(createTraining().getProfessor());
        assertThat(findallTraining.get(0).getDescription()).isEqualTo(createTraining().getDescription());
        assertThat(findallTraining.get(0).getFrequency()).isEqualTo(createTraining().getFrequency());
        assertThat(findallTraining.get(0).getCreateDate()).isEqualTo(createTraining().getCreateDate());
    }

    @Test
    public void deleteTrainingTest() {
        TrainingModel trainingModel = createTraining();

        trainingService.delete(trainingModel);

        verify(trainingRepository, times(1)).delete(trainingModel);
    }
}