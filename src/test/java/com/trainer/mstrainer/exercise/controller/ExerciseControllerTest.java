package com.trainer.mstrainer.exercise.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainer.mstrainer.exercise.model.ExerciseModel;
import com.trainer.mstrainer.exercise.service.ExerciseService;
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

import static com.trainer.mstrainer.exercise.mother.ExerciseMother.createExercise;
import static com.trainer.mstrainer.exercise.mother.ExerciseMother.EXERCISE_UUID;
import static com.trainer.mstrainer.exercise.mother.ExerciseMother.createExerciseOptional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ExerciseController.class)
@AutoConfigureMockMvc
public class ExerciseControllerTest {
    static String EXERCISE_API = "/v1/exercise";

    @Autowired
    MockMvc mvc;

    @MockBean
    ExerciseService service;

    @Test
    public void findAllExerciseTest() throws Exception {
        List<ExerciseModel> exerciseModelList = new ArrayList<>();
        exerciseModelList.add(createExercise());

        when(service.findAll()).thenReturn(exerciseModelList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EXERCISE_API)
                .accept(MediaType.APPLICATION_JSON);

        System.out.println("\n\n\n UID: " + UUID.randomUUID() + "\n\n");

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(EXERCISE_UUID))
                .andExpect(jsonPath("$[0].photoUrl").value(createExercise().getPhotoUrl()))
                .andExpect(jsonPath("$[0].groupExercise").value(createExercise().getGroupExercise()));
    }

    @Test
    public void findByIdExerciseTest() throws Exception {
        Optional<ExerciseModel> exerciseModelOptional = createExerciseOptional();

        when(service.findById(createExerciseOptional().get().getId())).thenReturn(exerciseModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EXERCISE_API.concat("/" + createExerciseOptional().get().getId()))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(EXERCISE_UUID))
                .andExpect(jsonPath("photoUrl").value(createExercise().getPhotoUrl()))
                .andExpect(jsonPath("groupExercise").value(createExercise().getGroupExercise()));
    }

    @Test
    @DisplayName("Deve retornar resource not found quando o livro procurado não existir")
    public void bookNotFoundTest() throws Exception {

        when(service.findById(createExerciseOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EXERCISE_API.concat("/error"))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createExerciseTest() throws Exception {
        ExerciseModel exercise = createExercise();

        when(service.update(any(ExerciseModel.class))).thenReturn(exercise);
        String json = new ObjectMapper().writeValueAsString(exercise);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EXERCISE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(EXERCISE_UUID))
                .andExpect(jsonPath("photoUrl").value(createExercise().getPhotoUrl()))
                .andExpect(jsonPath("groupExercise").value(createExercise().getGroupExercise()));
    }

    @Test
    public void createInvalidBookTest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EXERCISE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateExerciseTest() throws Exception {
        ExerciseModel exercise = createExercise();

        when(service.update(any(ExerciseModel.class))).thenReturn(exercise);
        String json = new ObjectMapper().writeValueAsString(exercise);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(EXERCISE_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(EXERCISE_UUID))
                .andExpect(jsonPath("photoUrl").value(createExercise().getPhotoUrl()))
                .andExpect(jsonPath("groupExercise").value(createExercise().getGroupExercise()));
    }

    @Test
    public void deleteExerciseTest() throws Exception {
        Optional<ExerciseModel> exerciseModelOptional = createExerciseOptional();

        when(service.findById(createExerciseOptional().get().getId())).thenReturn(exerciseModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(EXERCISE_API.concat("/" + EXERCISE_UUID));

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteInexistentExerciseTest() throws Exception {

        when(service.findById(createExerciseOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(EXERCISE_API.concat("testeError"));

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }
}