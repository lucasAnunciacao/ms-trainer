package com.trainer.mstrainer.composition.service;

import com.trainer.mstrainer.composition.model.CompositionModel;
import com.trainer.mstrainer.composition.repository.CompositionRepository;
import com.trainer.mstrainer.composition.service.impl.CompositionServiceImpl;
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

import static com.trainer.mstrainer.composition.mother.CompositionMother.COMPOSITION_UUID;
import static com.trainer.mstrainer.composition.mother.CompositionMother.createComposition;
import static com.trainer.mstrainer.composition.mother.CompositionMother.createCompositionOptional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class CompositionServiceTest {

    @Mock
    CompositionService compositionService;

    @MockBean
    CompositionRepository compositionRepository;

    @BeforeEach
    public void setUp() {
        this.compositionService = new CompositionServiceImpl(compositionRepository);
    }

    @Test
    public void saveCompositionTest() {
        CompositionModel compositionModel = createComposition();
        when(compositionRepository.save(compositionModel)).thenReturn(compositionModel);

        CompositionModel update = compositionService.update(compositionModel);

        assertThat(update.getId()).isEqualTo(UUID.fromString(COMPOSITION_UUID));
        assertThat(update.getDescription()).isEqualTo("description");
        assertThat(update.getCategory()).isEqualTo("category");
    }

    @Test
    public void getByIdCompositionTest() {
        Optional<CompositionModel> compositionModel = createCompositionOptional();
        when(compositionRepository.findById(UUID.fromString(COMPOSITION_UUID))).thenReturn(compositionModel);

        Optional<CompositionModel> getCompositionById = compositionService.findById(UUID.fromString(COMPOSITION_UUID));

        assertThat(getCompositionById.isPresent()).isTrue();
        assertThat(getCompositionById.get().getId()).isEqualTo(UUID.fromString(COMPOSITION_UUID));
        assertThat(getCompositionById.get().getDescription()).isEqualTo("description");
        assertThat(getCompositionById.get().getCategory()).isEqualTo("category");
    }

    @Test
    public void findAllCompositionTest() {
        List<CompositionModel> compositionModelList = new ArrayList<>();
        compositionModelList.add(createComposition());

        when(compositionRepository.findAll()).thenReturn(compositionModelList);

        List<CompositionModel> findallComposition = compositionService.findAll();

        assertThat(findallComposition.get(0).getId()).isEqualTo(UUID.fromString(COMPOSITION_UUID));
        assertThat(findallComposition.get(0).getDescription()).isEqualTo("description");
        assertThat(findallComposition.get(0).getCategory()).isEqualTo("category");
    }

    @Test
    public void deleteCompositionTest() {
        CompositionModel compositionModel = createComposition();

        compositionService.delete(compositionModel);

        verify(compositionRepository, times(1)).delete(compositionModel);
    }
}