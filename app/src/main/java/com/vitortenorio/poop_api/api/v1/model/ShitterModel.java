package com.vitortenorio.poop_api.api.v1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shitter")
@SequenceGenerator(name = "shitter_id_seq", sequenceName = "shitter_id_seq",  allocationSize = 1)
public class ShitterModel implements Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shitter_id_seq")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "image_base64")
    private String imageBase64;
}
