package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/stat")
@RequiredArgsConstructor
@Validated
public class StatController {

    @GetMapping
    public String getHello() {
        return "Hello!";
    }
}
