package com.ok.dvweb.controller.payload;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NewsRequest {

    private String tittle;
    private String Description;
    private MultipartFile image;
}
