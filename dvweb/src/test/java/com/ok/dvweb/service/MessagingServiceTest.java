package com.ok.dvweb.service;

import com.ok.dvweb.controller.payload.ProspectusRequest;
import com.ok.dvweb.dto.ProspectusDTO;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MessagingServiceTest {

    @Mock
    private SendGrid sendGrid;

    @InjectMocks
    private MessagingService emailService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSendEmail_Success() throws Exception {
        Response mockResponse = new Response();
        mockResponse.setStatusCode(202);
        mockResponse.setBody("Email sent successfully!");

        when(sendGrid.api(any(Request.class))).thenReturn(mockResponse);
        ProspectusDTO dto = new ProspectusDTO(new ProspectusRequest());
        Response result = emailService.notifyProspectus(dto);
        assertEquals("Email sent successfully!", result.getBody());
        verify(sendGrid, times(1)).api(any(Request.class));
    }

    @Test
    void testSendEmail_Failure() {
        try {
            when(sendGrid.api(any(Request.class))).thenThrow(new IOException("SendGrid failure"));
            assertThrows(Exception.class, () -> {
                emailService.notifyProspectus(new ProspectusDTO(new ProspectusRequest()));
            });
            verify(sendGrid, times(1)).api(any(Request.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void IOException() {
        try {
            when(sendGrid.api(any(Request.class))).thenThrow(new IOException("SendGrid failure"));
            assertThrows(Exception.class, () -> {
                emailService.notifyProspectus(new ProspectusDTO(new ProspectusRequest()));
            });
            verify(sendGrid, times(1)).api(any(Request.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
