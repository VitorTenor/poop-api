package com.vitortenorio.poop_api.api.v1.model;

import com.vitortenorio.poop_api.enums.DayPeriod;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "shit_event")
@SequenceGenerator(name = "shit_event_id_seq", sequenceName = "shit_event_id_seq", allocationSize = 1)
public class ShitEventModel implements Serializable {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shit_event_id_seq")
    private Long id;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "shitter_id")
    private ShitterModel shitter;

    @Enumerated(EnumType.STRING)
    private DayPeriod period;
}
