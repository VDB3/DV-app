package com.ok.dvweb.controller.payload;

import lombok.Data;

@Data
public class ProspectusRequest {

    private String username;
    private String mail;
    private int phone;
    private String message;
}
