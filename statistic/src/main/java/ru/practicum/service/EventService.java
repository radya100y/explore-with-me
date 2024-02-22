package ru.practicum.service;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.error.NotFoundException;
import ru.practicum.model.*;
import ru.practicum.model.QEvent;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class EventService {

    @Autowired
    private final EventRepository eventRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public EventOut save(EventIn eventIn) {
        return EventMapper.toEventOut(eventRepository.save(EventMapper.toEvent(eventIn)));
    }

    public EventOut getById(long id) {
        return EventMapper.toEventOut(eventRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Событие с идентификатором " + id + " не найдено")));
    }

    public List<EventsOut> getEvents(LocalDateTime start, LocalDateTime end, List<String> uris, Boolean unique) {

        List<EventsOut> returnedEvents = new ArrayList<>();
/*        BooleanExpression byStart = QEvent.event.dt.after(LocalDateTime.from(start));
        BooleanExpression byEnd = QEvent.event.dt.before(LocalDateTime.from(end));
        BooleanExpression byUris = QEvent.event.uri.in(uris);
        Iterable<Event> events = eventRepository.findAll(byStart.and(byEnd).and(byUris));*/

        QEvent event = QEvent.event;
        StringExpression appExpression = event.app;
        StringExpression uriExpression = event.uri;
        NumberExpression<Long> hitsExpression = unique ? event.ip.countDistinct() : event.id.count();


        List<Tuple> tuples = new JPAQueryFactory(entityManager)
                .select(appExpression, uriExpression, hitsExpression)
                .from(event)
                .where(
                        event.dt.after(start),
                        event.dt.before(end),
                        event.uri.in(uris))
                .groupBy(appExpression, uriExpression)
                .orderBy(hitsExpression.desc())
                .fetch();
        for (Tuple row : tuples) {
            returnedEvents.add(new EventsOut(row.toArray()[0].toString(),
                    row.toArray()[1].toString(),
                    row.toArray()[2].toString()));
        }
        return returnedEvents;
    }
}
