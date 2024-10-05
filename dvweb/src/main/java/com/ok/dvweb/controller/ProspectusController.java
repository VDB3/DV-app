package com.ok.dvweb.controller;

import com.ok.dvweb.controller.payload.ProspectusRequest;
import com.ok.dvweb.controller.payload.sub.ErrorResponse;
import com.ok.dvweb.dto.ProspectusDTO;
import com.ok.dvweb.service.MessagingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class ProspectusController {

    protected final MessagingService service;

    @PostMapping("/contact")
    public ResponseEntity<?> contact(@RequestBody ProspectusRequest request) throws IOException {
        try {
            ProspectusDTO dto = new ProspectusDTO(request);
            return ResponseEntity.ok(service.notifyProspectus(dto));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.builder()
                            .error(e.getMessage())
                            .userFriendlyMessage("/")
                            .build());
        }
    }
}
