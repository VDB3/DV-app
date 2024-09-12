package com.ok.dvweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ok.dvweb.component.MailDataProcessor;
import com.ok.dvweb.component.sub.EmailValidationResult;
import com.ok.dvweb.controller.payload.SubscriberRequest;
import com.ok.dvweb.service.SubscriberService;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SubscriberController.class)
public class SubscriberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriberService service;

    @MockBean
    private MailDataProcessor dataProcessor;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void whenValidEmail_thenReturns200() throws Exception {
        SubscriberRequest request = new SubscriberRequest();
        request.setMail("test@test.com");
        EmailValidationResult validationResult = new EmailValidationResult(true, "Valid email");

        Mockito.when(dataProcessor.validateEmail(request.getMail()))
                .thenReturn(validationResult);

        mockMvc.perform(post("/subscriber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    public void whenInvalidEmail_thenReturns400() throws Exception {
        SubscriberRequest request = new SubscriberRequest();
        EmailValidationResult validationResult = new EmailValidationResult(false, "Invalid email");

        Mockito.when(dataProcessor.validateEmail(request.getMail()))
                .thenReturn(validationResult);

        mockMvc.perform(post("/subscriber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.userFriendlyMessage").value("Please check data"));
    }
}
