package com.trainer.mstrainer.realization.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainer.mstrainer.realization.model.RealizationModel;
import com.trainer.mstrainer.realization.service.RealizationService;
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

import static com.trainer.mstrainer.realization.mother.RealizationMother.createRealization;
import static com.trainer.mstrainer.realization.mother.RealizationMother.createRealizationOptional;
import static com.trainer.mstrainer.realization.mother.RealizationMother.REALIZATION_UUID;
import static com.trainer.mstrainer.training.mother.TrainingMother.CLIENT_UUID;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = RealizationController.class)
@AutoConfigureMockMvc
public class RealizationControllerTest {
    static String REALIZATION_API = "/v1/realization";
    private static final String DATE_EXPEXTED = "2022-04-12T03:00:00.000+00:00";

    @Autowired
    MockMvc mvc;

    @MockBean
    RealizationService service;

    @Test
    public void findAllRealizationTest() throws Exception {
        List<RealizationModel> realizationModelList = new ArrayList<>();
        realizationModelList.add(createRealization());

        when(service.findAll()).thenReturn(realizationModelList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(REALIZATION_API)
                .accept(MediaType.APPLICATION_JSON);


        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(REALIZATION_UUID))
                .andExpect(jsonPath("$[0].clientId").value(CLIENT_UUID))
                .andExpect(jsonPath("$[0].realizationDate").value(DATE_EXPEXTED));
    }

    @Test
    public void findByIdRealizationTest() throws Exception {
        Optional<RealizationModel> realizationModelOptional = createRealizationOptional();

        when(service.findById(createRealizationOptional().get().getId())).thenReturn(realizationModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(REALIZATION_API.concat("/" + createRealizationOptional().get().getId()))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(REALIZATION_UUID))
                .andExpect(jsonPath("clientId").value(CLIENT_UUID))
                .andExpect(jsonPath("realizationDate").value(DATE_EXPEXTED));
    }

    @Test
    @DisplayName("Deve retornar resource not found quando o livro procurado não existir")
    public void bookNotFoundTest() throws Exception {

        when(service.findById(createRealizationOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(REALIZATION_API.concat("/error"))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createRealizationTest() throws Exception {
        RealizationModel realization = createRealization();

        when(service.update(any(RealizationModel.class))).thenReturn(realization);
        String json = new ObjectMapper().writeValueAsString(realization);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(REALIZATION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(REALIZATION_UUID))
                .andExpect(jsonPath("clientId").value(CLIENT_UUID))
                .andExpect(jsonPath("realizationDate").value(DATE_EXPEXTED));
    }

    @Test
    public void createInvalidBookTest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(REALIZATION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateRealizationTest() throws Exception {
        RealizationModel realization = createRealization();

        when(service.update(any(RealizationModel.class))).thenReturn(realization);
        String json = new ObjectMapper().writeValueAsString(realization);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(REALIZATION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(REALIZATION_UUID))
                .andExpect(jsonPath("clientId").value(CLIENT_UUID))
                .andExpect(jsonPath("realizationDate").value(DATE_EXPEXTED));
    }

    @Test
    public void deleteRealizationTest() throws Exception {
        Optional<RealizationModel> realizationModelOptional = createRealizationOptional();

        when(service.findById(createRealizationOptional().get().getId())).thenReturn(realizationModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(REALIZATION_API.concat("/" + REALIZATION_UUID));

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteInexistentRealizationTest() throws Exception {

        when(service.findById(createRealizationOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(REALIZATION_API.concat("testeError"));

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }
}