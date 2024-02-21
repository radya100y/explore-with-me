package ru.practicum.model;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Getter
public class EventIn {
    @Size(max = 255)
    @NotBlank
    private String app;

    @Size(max = 255)
    @NotBlank
    private String uri;

    @Size(max = 255)
    @NotBlank
    private String ip;
}
