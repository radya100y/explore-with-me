package ru.practicum.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "events", schema = "public")
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(max = 255)
    @Column(name = "app", nullable = false)
    private String app;

    @Size(max = 255)
    @Column(name = "uri", nullable = false)
    private String uri;

    @Size(max = 255)
    @Column(name = "ip", nullable = false)
    private String ip;

    @Column(name = "dt", nullable = false)
    private LocalDateTime dt;
}
