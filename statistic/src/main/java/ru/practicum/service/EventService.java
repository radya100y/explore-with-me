package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.error.NotFoundException;
import ru.practicum.model.EventIn;
import ru.practicum.model.EventMapper;
import ru.practicum.model.EventOut;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    @Transactional
    public EventOut save(EventIn eventIn) {
        return EventMapper.toEventOut(eventRepository.save(EventMapper.toEvent(eventIn)));
    }

    public EventOut getById(long id) {
        return EventMapper.toEventOut(eventRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Событие с идентификатором " + id + " не найдено")));
    }
}
