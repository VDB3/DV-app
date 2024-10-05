package com.ok.dvweb.controller;

import com.ok.dvweb.controller.payload.sub.Empty;
import com.ok.dvweb.controller.payload.sub.ErrorResponse;
import com.ok.dvweb.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsController {

    protected final NewsService service;

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateNews(@RequestParam MultipartFile image) {
        try {
            String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
            service.notifySubs(imageBase64);
            return ResponseEntity.ok(new Empty());
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.builder()
                            .description(ex.getMessage())
                            .build());
        }
    }

}
