package ru.practicum.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
