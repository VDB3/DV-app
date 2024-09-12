package com.ok.dvweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ok.dvweb.component.MailDataProcessor;
import com.ok.dvweb.controller.payload.ProspectusRequest;
import com.ok.dvweb.dto.ProspectusDTO;
import com.ok.dvweb.service.MessagingService;
import com.ok.dvweb.service.SubscriberService;
import com.sendgrid.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(ProspectusController.class)
public class ProspectusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessagingService service;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void whenValidRequest_thenReturns200() throws Exception {
        ProspectusRequest request = new ProspectusRequest();
        ProspectusDTO dto = new ProspectusDTO(request);
        Mockito.when(service.notifyProspectus(Mockito.any(ProspectusDTO.class)))
                .thenReturn(new Response());

        mockMvc.perform(post("/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenIOException_thenReturns500() throws Exception {
        ProspectusRequest request = new ProspectusRequest();
        Mockito.when(service.notifyProspectus(Mockito.any(ProspectusDTO.class)))
                .thenThrow(new IOException("Simulated IO Exception"));

        mockMvc.perform(post("/contact")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isInternalServerError());
    }
}
