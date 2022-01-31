package com.trainer.mstrainer.training.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainer.mstrainer.training.model.TrainingModel;
import com.trainer.mstrainer.training.service.TrainingService;
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

import static com.trainer.mstrainer.training.mother.TrainingMother.PROFESSOR_UUID;
import static com.trainer.mstrainer.training.mother.TrainingMother.createTraining;
import static com.trainer.mstrainer.training.mother.TrainingMother.TRAINING_UUID;
import static com.trainer.mstrainer.training.mother.TrainingMother.CLIENT_UUID;
import static com.trainer.mstrainer.training.mother.TrainingMother.createTrainingOptional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = TrainingController.class)
@AutoConfigureMockMvc
public class TrainingControllerTest {
    static String TRAINING_API = "/v1/training";
    private static final String DATE_EXPEXTED = "2022-04-12T03:00:00.000+00:00";

    @Autowired
    MockMvc mvc;

    @MockBean
    TrainingService service;

    @Test
    public void findAllTrainingTest() throws Exception {
        List<TrainingModel> trainingModelList = new ArrayList<>();
        trainingModelList.add(createTraining());

        when(service.findAll()).thenReturn(trainingModelList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(TRAINING_API)
                .accept(MediaType.APPLICATION_JSON);

        System.out.println("\n\n\n UID: " + UUID.randomUUID() + "\n\n");

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(TRAINING_UUID))
                .andExpect(jsonPath("$[0].description").value(createTraining().getDescription()))
                .andExpect(jsonPath("$[0].createDate").value(DATE_EXPEXTED))
                .andExpect(jsonPath("$[0].client").value(CLIENT_UUID))
                .andExpect(jsonPath("$[0].frequency").value(createTraining().getFrequency()))
                .andExpect(jsonPath("$[0].professor").value(PROFESSOR_UUID));
    }

    @Test
    public void findByIdTrainingTest() throws Exception {
        Optional<TrainingModel> trainingModelOptional = createTrainingOptional();

        when(service.findById(createTrainingOptional().get().getId())).thenReturn(trainingModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(TRAINING_API.concat("/" + createTrainingOptional().get().getId()))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(TRAINING_UUID))
                .andExpect(jsonPath("description").value(createTraining().getDescription()))
                .andExpect(jsonPath("createDate").value(DATE_EXPEXTED))
                .andExpect(jsonPath("client").value(CLIENT_UUID))
                .andExpect(jsonPath("frequency").value(createTraining().getFrequency()))
                .andExpect(jsonPath("professor").value(PROFESSOR_UUID));
    }

    @Test
    @DisplayName("Deve retornar resource not found quando o livro procurado não existir")
    public void bookNotFoundTest() throws Exception {

        when(service.findById(createTrainingOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(TRAINING_API.concat("/error"))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createTrainingTest() throws Exception {
        TrainingModel training = createTraining();

        when(service.update(any(TrainingModel.class))).thenReturn(training);
        String json = new ObjectMapper().writeValueAsString(training);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(TRAINING_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(TRAINING_UUID))
                .andExpect(jsonPath("description").value(createTraining().getDescription()))
                .andExpect(jsonPath("createDate").value(DATE_EXPEXTED))
                .andExpect(jsonPath("client").value(CLIENT_UUID))
                .andExpect(jsonPath("frequency").value(createTraining().getFrequency()))
                .andExpect(jsonPath("professor").value(PROFESSOR_UUID));
    }

    @Test
    public void createInvalidBookTest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(TRAINING_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateTrainingTest() throws Exception {
        TrainingModel training = createTraining();

        when(service.update(any(TrainingModel.class))).thenReturn(training);
        String json = new ObjectMapper().writeValueAsString(training);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(TRAINING_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(TRAINING_UUID))
                .andExpect(jsonPath("description").value(createTraining().getDescription()))
                .andExpect(jsonPath("createDate").value(DATE_EXPEXTED))
                .andExpect(jsonPath("client").value(CLIENT_UUID))
                .andExpect(jsonPath("frequency").value(createTraining().getFrequency()))
                .andExpect(jsonPath("professor").value(PROFESSOR_UUID));
    }

    @Test
    public void deleteTrainingTest() throws Exception {
        Optional<TrainingModel> trainingModelOptional = createTrainingOptional();

        when(service.findById(createTrainingOptional().get().getId())).thenReturn(trainingModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(TRAINING_API.concat("/" + TRAINING_UUID));

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteInexistentTrainingTest() throws Exception {

        when(service.findById(createTrainingOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(TRAINING_API.concat("testeError"));

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }
}