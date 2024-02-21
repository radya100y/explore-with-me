package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.model.EventIn;
import ru.practicum.model.EventOut;
import ru.practicum.service.EventService;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/stat")
@RequiredArgsConstructor
@Validated
public class StatController {

    @Autowired
    private final EventService eventService;

    @GetMapping
    public String getHello() {
        return "Hello!";
    }

    @PostMapping
    public EventOut save(@Valid EventIn eventIn) {
        return eventService.save(eventIn);
    }
}
