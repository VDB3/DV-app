package com.ok.dvweb.controller;

import com.ok.dvweb.controller.payload.sub.Empty;
import com.ok.dvweb.controller.payload.sub.ErrorResponse;
import com.ok.dvweb.controller.payload.sub.NewsResponse;
import com.ok.dvweb.dto.NewsDTO;
import com.ok.dvweb.entity.News;
import com.ok.dvweb.service.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class NewsController {

    protected final NewsService service;

    @PostMapping("/update")
    public ResponseEntity<?> updateNews(@RequestParam String tittle, @RequestParam String description,
                                        @RequestParam MultipartFile image) {
        try {
            service.saveNews(mapToDTO(tittle, description, image));
            return ResponseEntity.ok(new Empty());
        } catch (Exception ex) {
            log.info(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ErrorResponse.builder()
                            .description(ex.getMessage())
                            .build());
        }
    }

    public NewsDTO mapToDTO(String tittle, String description, MultipartFile image) throws IOException {
        NewsDTO dto = new NewsDTO();
        dto.setTittle(tittle);
        dto.setDescription(description);
        dto.setImage(image.getBytes());
        return dto;
    }

    @GetMapping("/news")
    public ResponseEntity<?> listNews() {
        List<News> listNews = service.findAllNews();
        return ResponseEntity.ok(mapList(listNews));
    }

    private List<NewsResponse> mapList(List<News> list) {
        return list.stream()
                .map(item -> NewsResponse.builder()
                        .tittle(item.getTittle())
                        .description(item.getDescription())
                        .imageBase64(Base64.getEncoder().encodeToString(item.getImage()))
                        .build())
                .collect(Collectors.toList());
    }

}
