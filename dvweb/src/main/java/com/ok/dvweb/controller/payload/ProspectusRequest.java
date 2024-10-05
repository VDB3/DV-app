package com.ok.dvweb.controller.payload;

import lombok.Data;

@Data
public class ProspectusRequest {

    private String username;
    private String mail;
    private String phone;
    private String message;
}
