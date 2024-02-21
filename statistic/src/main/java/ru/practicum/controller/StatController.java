package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/hello")
    public String getHello() {
        return "Hello!";
    }

    @PostMapping
    public EventOut save(@Valid @RequestBody EventIn eventIn) {
        return eventService.save(eventIn);
    }

    @GetMapping("/{id}")
    public EventOut getById(@PathVariable("id") long id) {
        return eventService.getById(id);
    }
}
