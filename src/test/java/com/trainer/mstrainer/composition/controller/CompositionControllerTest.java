package com.trainer.mstrainer.composition.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainer.mstrainer.composition.model.CompositionModel;
import com.trainer.mstrainer.composition.service.CompositionService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static com.trainer.mstrainer.composition.mother.CompositionMother.COMPOSITION_UUID;
import static com.trainer.mstrainer.composition.mother.CompositionMother.createComposition;
import static com.trainer.mstrainer.composition.mother.CompositionMother.createCompositionOptional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = CompositionController.class)
@AutoConfigureMockMvc
public class CompositionControllerTest {
    static String COMPOSITION_API = "/v1/composition";

    @Autowired
    MockMvc mvc;

    @MockBean
    CompositionService service;

    @Test
    public void findAllCompositionTest() throws Exception {
        List<CompositionModel> compositionModelList = new ArrayList<>();
        compositionModelList.add(createComposition());

        when(service.findAll()).thenReturn(compositionModelList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(COMPOSITION_API)
                .accept(MediaType.APPLICATION_JSON);

        System.out.println("\n\n\n UID: " + UUID.randomUUID() + "\n\n");

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(COMPOSITION_UUID))
                .andExpect(jsonPath("$[0].description").value(createComposition().getDescription()))
                .andExpect(jsonPath("$[0].category").value(createComposition().getCategory()));
    }

    @Test
    public void findByIdCompositionTest() throws Exception {
        Optional<CompositionModel> compositionModelOptional = createCompositionOptional();

        when(service.findById(createCompositionOptional().get().getId())).thenReturn(compositionModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(COMPOSITION_API.concat("/" + createCompositionOptional().get().getId()))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(COMPOSITION_UUID))
                .andExpect(jsonPath("description").value(createComposition().getDescription()))
                .andExpect(jsonPath("category").value(createComposition().getCategory()));
    }

    @Test
    @DisplayName("Deve retornar resource not found quando o livro procurado não existir")
    public void bookNotFoundTest() throws Exception {

        when(service.findById(createCompositionOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(COMPOSITION_API.concat("/error"))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createCompositionTest() throws Exception {
        CompositionModel composition = createComposition();

        when(service.update(any(CompositionModel.class))).thenReturn(composition);
        String json = new ObjectMapper().writeValueAsString(composition);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(COMPOSITION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(COMPOSITION_UUID))
                .andExpect(jsonPath("description").value(createComposition().getDescription()))
                .andExpect(jsonPath("category").value(createComposition().getCategory()));
    }

    @Test
    public void createInvalidBookTest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(COMPOSITION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateCompositionTest() throws Exception {
        CompositionModel composition = createComposition();

        when(service.update(any(CompositionModel.class))).thenReturn(composition);
        String json = new ObjectMapper().writeValueAsString(composition);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(COMPOSITION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(COMPOSITION_UUID))
                .andExpect(jsonPath("description").value(createComposition().getDescription()))
                .andExpect(jsonPath("category").value(createComposition().getCategory()));
    }

    @Test
    public void deleteCompositionTest() throws Exception {
        Optional<CompositionModel> compositionModelOptional = createCompositionOptional();

        when(service.findById(createCompositionOptional().get().getId())).thenReturn(compositionModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(COMPOSITION_API.concat("/" + COMPOSITION_UUID));

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteInexistentCompositionTest() throws Exception {

        when(service.findById(createCompositionOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(COMPOSITION_API.concat("testeError"));

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }
}