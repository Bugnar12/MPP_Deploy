/*
package org.example.controller;

import org.backendspring_boot.backendspring_boot.entity.Antivirus;
import org.backendspring_boot.backendspring_boot.service.AntivirusServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

import org.backendspring_boot.backendspring_boot.controller.AntivirusController;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class AntivirusControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AntivirusServiceImpl antivirusService;

    @InjectMocks
    private AntivirusController antivirusController;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(antivirusController)
                .build();
    }

    @Test
    public void testGetAllAntiviruses() throws Exception {
        List<Antivirus> antiviruses = Arrays.asList(
                new Antivirus(1, "Test Antivirus 1", "Test Producer 1", "This is a test antivirus.", true, new Date()),
                new Antivirus(2, "Test Antivirus 2", "Test Producer 2", "This is another test antivirus.", false, new Date())
        );

        when(antivirusService.getAllAntivirus()).thenReturn(antiviruses);

        mockMvc.perform(get("/antivirusList")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void testGetAntivirusById() throws Exception {
        Antivirus antivirus = new Antivirus(1, "Test Antivirus 1", "Test Producer 1", "This is a test antivirus.", true, new Date());

        when(antivirusService.getAntivirusById(1)).thenReturn(antivirus);

        mockMvc.perform(get("/antivirusList/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.description").value("This is a test antivirus."));
    }

    @Test
    public void testAddAntivirus() throws Exception {
        Antivirus antivirus = new Antivirus(1, "Test Antivirus 1", "Test Producer 1", "This is a test antivirus.", true, new Date());

        List<Antivirus> antiviruses = new ArrayList<>();
        when(antivirusService.getAllAntivirus()).thenReturn(antiviruses);

        doAnswer(invocation -> {
            antiviruses.add(antivirus);
            return null;
        }).when(antivirusService).addAntivirus(any(Antivirus.class));

        mockMvc.perform(post("/antivirusList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(antivirus)))
                .andExpect(status().isOk());

        verify(antivirusService, times(1)).addAntivirus(any(Antivirus.class));
        assertEquals(1, antiviruses.size());
    }

    @Test
    public void testDeleteAntivirus() throws Exception {
        Antivirus antivirus = new Antivirus(1, "Test Antivirus 1", "Test Producer 1", "This is a test antivirus.", true, new Date());

        when(antivirusService.getAntivirusById(1)).thenReturn(antivirus);

        mockMvc.perform(delete("/antivirusList/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateAntivirus() throws Exception {
        Antivirus antivirus = new Antivirus(1, "Test Antivirus 1", "Test Producer 1", "This is a test antivirus.", true, new Date());

        when(antivirusService.getAntivirusById(1)).thenReturn(antivirus);

        mockMvc.perform(put("/antivirusList/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(antivirus)))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddAntivirusInvalid() throws Exception {
        Antivirus antivirus = new Antivirus(1, "Test Antivirus 1", "Test Producer 1", null, true, new Date(System.currentTimeMillis() + 86400000)); // description is null and releaseDate is in the future

        mockMvc.perform(post("/antivirusList")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(antivirus)))
                .andExpect(status().isBadRequest());
    }
}


*/
