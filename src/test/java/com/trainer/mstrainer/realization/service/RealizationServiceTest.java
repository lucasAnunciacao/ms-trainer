package com.trainer.mstrainer.realization.service;

import com.trainer.mstrainer.realization.model.RealizationModel;
import com.trainer.mstrainer.realization.repository.RealizationRepository;
import com.trainer.mstrainer.realization.service.impl.RealizationServiceImpl;
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

import static com.trainer.mstrainer.realization.mother.RealizationMother.REALIZATION_UUID;
import static com.trainer.mstrainer.realization.mother.RealizationMother.createRealization;
import static com.trainer.mstrainer.realization.mother.RealizationMother.createRealizationOptional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RealizationServiceTest {

    @Mock
    RealizationService realizationService;

    @MockBean
    RealizationRepository realizationRepository;

    @BeforeEach
    public void setUp() {
        this.realizationService = new RealizationServiceImpl(realizationRepository);
    }

    @Test
    public void saveRealizationTest() {
        RealizationModel realizationModel = createRealization();
        when(realizationRepository.save(realizationModel)).thenReturn(realizationModel);

        RealizationModel update = realizationService.update(realizationModel);

        assertThat(update.getId()).isEqualTo(UUID.fromString(REALIZATION_UUID));
        assertThat(update.getClientId()).isEqualTo(createRealization().getClientId());
        assertThat(update.getRealizationDate()).isEqualTo(createRealization().getRealizationDate());
        assertThat(update.getCompositionModel().getId()).isEqualTo(createRealization().getCompositionModel().getId());
        assertThat(update.getCompositionModel().getCategory()).isEqualTo(createRealization().getCompositionModel().getCategory());
        assertThat(update.getCompositionModel().getDescription()).isEqualTo(createRealization().getCompositionModel().getDescription());
    }

    @Test
    public void getByIdRealizationTest() {
        Optional<RealizationModel> realizationModel = createRealizationOptional();
        when(realizationRepository.findById(UUID.fromString(REALIZATION_UUID))).thenReturn(realizationModel);

        Optional<RealizationModel> getRealizationById = realizationService.findById(UUID.fromString(REALIZATION_UUID));

        assertThat(getRealizationById.isPresent()).isTrue();
        assertThat(getRealizationById.get().getId()).isEqualTo(UUID.fromString(REALIZATION_UUID));
        assertThat(getRealizationById.get().getClientId()).isEqualTo(createRealization().getClientId());
        assertThat(getRealizationById.get().getRealizationDate()).isEqualTo(createRealization().getRealizationDate());
        assertThat(getRealizationById.get().getCompositionModel().getId()).isEqualTo(createRealization().getCompositionModel().getId());
        assertThat(getRealizationById.get().getCompositionModel().getCategory()).isEqualTo(createRealization().getCompositionModel().getCategory());
        assertThat(getRealizationById.get().getCompositionModel().getDescription()).isEqualTo(createRealization().getCompositionModel().getDescription());
    }

    @Test
    public void findAllRealizationTest() {
        List<RealizationModel> realizationModelList = new ArrayList<>();
        realizationModelList.add(createRealization());

        when(realizationRepository.findAll()).thenReturn(realizationModelList);

        List<RealizationModel> findallRealization = realizationService.findAll();

        assertThat(findallRealization.get(0).getId()).isEqualTo(UUID.fromString(REALIZATION_UUID));
        assertThat(findallRealization.get(0).getClientId()).isEqualTo(createRealization().getClientId());
        assertThat(findallRealization.get(0).getRealizationDate()).isEqualTo(createRealization().getRealizationDate());
        assertThat(findallRealization.get(0).getCompositionModel().getId()).isEqualTo(createRealization().getCompositionModel().getId());
        assertThat(findallRealization.get(0).getCompositionModel().getCategory()).isEqualTo(createRealization().getCompositionModel().getCategory());
        assertThat(findallRealization.get(0).getCompositionModel().getDescription()).isEqualTo(createRealization().getCompositionModel().getDescription());
    }

    @Test
    public void deleteRealizationTest() {
        RealizationModel realizationModel = createRealization();

        realizationService.delete(realizationModel);

        verify(realizationRepository, times(1)).delete(realizationModel);
    }
}