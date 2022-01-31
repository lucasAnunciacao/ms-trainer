package com.trainer.mstrainer.execution.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainer.mstrainer.execution.model.ExecutionModel;
import com.trainer.mstrainer.execution.service.ExecutionService;
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

import static com.trainer.mstrainer.execution.mother.ExecutionMother.createExecution;
import static com.trainer.mstrainer.execution.mother.ExecutionMother.EXECUTION_UUID;
import static com.trainer.mstrainer.execution.mother.ExecutionMother.createExecutionOptional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(controllers = ExecutionController.class)
@AutoConfigureMockMvc
public class ExecutionControllerTest {
    static String EXECUTION_API = "/v1/execution";

    @Autowired
    MockMvc mvc;

    @MockBean
    ExecutionService service;

    @Test
    public void findAllExecutionTest() throws Exception {
        List<ExecutionModel> executionModelList = new ArrayList<>();
        executionModelList.add(createExecution());

        when(service.findAll()).thenReturn(executionModelList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EXECUTION_API)
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(EXECUTION_UUID))
                .andExpect(jsonPath("$[0].intensity").value(createExecution().getIntensity()))
                .andExpect(jsonPath("$[0].repetion").value(createExecution().getRepetion()));
    }

    @Test
    public void findByIdExecutionTest() throws Exception {
        Optional<ExecutionModel> executionModelOptional = createExecutionOptional();

        when(service.findById(createExecutionOptional().get().getId())).thenReturn(executionModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EXECUTION_API.concat("/" + createExecutionOptional().get().getId()))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(EXECUTION_UUID))
                .andExpect(jsonPath("intensity").value(createExecution().getIntensity()))
                .andExpect(jsonPath("repetion").value(createExecution().getRepetion()));
    }

    @Test
    @DisplayName("Deve retornar resource not found quando o livro procurado não existir")
    public void bookNotFoundTest() throws Exception {

        when(service.findById(createExecutionOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(EXECUTION_API.concat("/error"))
                .accept(MediaType.APPLICATION_JSON);

        mvc
                .perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createExecutionTest() throws Exception {
        ExecutionModel execution = createExecution();

        when(service.update(any(ExecutionModel.class))).thenReturn(execution);
        String json = new ObjectMapper().writeValueAsString(execution);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EXECUTION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(EXECUTION_UUID))
                .andExpect(jsonPath("intensity").value(createExecution().getIntensity()))
                .andExpect(jsonPath("repetion").value(createExecution().getRepetion()));
    }

    @Test
    public void createInvalidBookTest() throws Exception {
        String json = new ObjectMapper().writeValueAsString(null);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(EXECUTION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateExecutionTest() throws Exception {
        ExecutionModel execution = createExecution();

        when(service.update(any(ExecutionModel.class))).thenReturn(execution);
        String json = new ObjectMapper().writeValueAsString(execution);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(EXECUTION_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(EXECUTION_UUID))
                .andExpect(jsonPath("intensity").value(createExecution().getIntensity()))
                .andExpect(jsonPath("repetion").value(createExecution().getRepetion()));
    }

    @Test
    public void deleteExecutionTest() throws Exception {
        Optional<ExecutionModel> executionModelOptional = createExecutionOptional();

        when(service.findById(createExecutionOptional().get().getId())).thenReturn(executionModelOptional);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(EXECUTION_API.concat("/" + EXECUTION_UUID));

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteInexistentExecutionTest() throws Exception {

        when(service.findById(createExecutionOptional().get().getId())).thenReturn(Optional.empty());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(EXECUTION_API.concat("testeError"));

        mvc.perform(request)
                .andExpect(status().isNotFound());
    }
}