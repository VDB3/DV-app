package com.ok.dvweb.dto;

import com.ok.dvweb.controller.payload.ProspectusRequest;
import lombok.Data;

@Data
public class ProspectusDTO {

    private String username;
    private String mail;
    private int phone;
    private String message;

    public ProspectusDTO(ProspectusRequest request) {
        this.username = request.getUsername();
        this.mail = request.getMail();
        this.phone = request.getPhone();
        this.message = request.getMessage();
    }
}
