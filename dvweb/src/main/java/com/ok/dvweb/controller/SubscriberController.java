package com.ok.dvweb.controller;

import com.ok.dvweb.component.MailDataProcessor;
import com.ok.dvweb.component.sub.EmailValidationResult;
import com.ok.dvweb.controller.payload.SubscriberRequest;
import com.ok.dvweb.controller.payload.sub.ErrorResponse;
import com.ok.dvweb.service.MessagingService;
import com.ok.dvweb.service.SubscriberService;
import com.ok.dvweb.dto.SubscriberDTO;
import com.sendgrid.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SubscriberController {

    protected final SubscriberService service;
    protected final MailDataProcessor dataProcessor;
    protected final MessagingService messagingService;

    @PostMapping("/subscriber")
    public ResponseEntity<?> subscriberAdd(@RequestBody SubscriberRequest sub) throws IOException {
        EmailValidationResult validationResult = dataProcessor.validateEmail(sub.getMail());
        if (validationResult.isValid()) {
            final SubscriberDTO dto = new SubscriberDTO(sub.getMail());
            String token = service.addSubscriber(dto);
            Response response = messagingService.sendMailToConfirm(sub.getMail(), token);
            log.info(validationResult.getMessage());
            return ResponseEntity.status(response.getStatusCode()).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder()
                            .description(validationResult.getMessage())
                            .userFriendlyMessage("Please check data")
                            .build());
        }
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirmUserEmail(@RequestParam String token){
        log.info("Verificando correo electronico");
        boolean confirmed = service.confirmEmail(token);
        if (confirmed) {
            return ResponseEntity.ok("Correo confirmado exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Token de confirmación inválido o expirado.");
        }
    }
}
