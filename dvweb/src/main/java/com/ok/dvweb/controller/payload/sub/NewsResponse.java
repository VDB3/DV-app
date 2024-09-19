package com.ok.dvweb.controller.payload.sub;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewsResponse {

    private String tittle;
    private String description;
    private String imageBase64;

}
