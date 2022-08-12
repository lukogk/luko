package com.luko.javaspringapi.controllers;

import com.luko.javaspringapi.dto.PatientDto;
import com.luko.javaspringapi.services.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService service;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        UUID uid = UUID.randomUUID();
        PatientDto response =  new PatientDto();
        response.setId(uid);
        response.setFirstName("testName");

        when(service.getPatientById(uid)).thenReturn(response);
        this.mockMvc.perform(get("/api/patients/"+uid)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("testName")));
    }
}