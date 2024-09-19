package com.ok.dvweb.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Column(name = "tittle", nullable = false)
    private String tittle;

    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image; //
}
