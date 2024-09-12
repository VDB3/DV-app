package com.ok.dvweb.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "subscriber")
public class Subscriber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Column(name = "mail", nullable = false)
    private String mail;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "notify", nullable = false)
    private int notify;

    @Column(name = "verify", nullable = false)
    private boolean verify;
}
