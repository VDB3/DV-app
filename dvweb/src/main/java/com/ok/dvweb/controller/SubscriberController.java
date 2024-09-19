package com.ok.dvweb.controller;

import com.ok.dvweb.component.MailDataProcessor;
import com.ok.dvweb.component.sub.EmailValidationResult;
import com.ok.dvweb.controller.payload.SubscriberRequest;
import com.ok.dvweb.controller.payload.sub.ErrorResponse;
import com.ok.dvweb.service.SubscriberService;
import com.ok.dvweb.dto.SubscriberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SubscriberController {

    protected final SubscriberService service;
    protected final MailDataProcessor dataProcessor;

    @PostMapping("/subscriber")
    public ResponseEntity<?> subscriberAdd(@RequestBody SubscriberRequest sub) {
        EmailValidationResult validationResult = dataProcessor.validateEmail(sub.getMail());
        if (validationResult.isValid()) {
            final SubscriberDTO dto = new SubscriberDTO(sub.getMail());
            service.addSubscriber(dto);
            log.info(validationResult.getMessage());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse.builder()
                            .description(validationResult.getMessage())
                            .userFriendlyMessage("Please check data")
                            .build());
        }
    }

}
