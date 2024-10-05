package com.ok.dvweb.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubscriberDTO {
    private String mail;
    private LocalDateTime created;
    private int notify;

    public SubscriberDTO(String mail) {
        this.mail = mail;
        this.created = LocalDateTime.now();
        this.notify = 0;
    }


}
