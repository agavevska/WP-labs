package mk.finki.ukim.mk.lab.lab.service;

import mk.finki.ukim.mk.lab.lab.model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> listAll();
    List<Event> searchEvents(String text);
    void deleteById(Long id);
    Optional<Event> save(String name, String description, double popularityScore, Long id);
    Event update(Long eventId, String name, String description, double popularityScore, Long id);
    void updateRating(Long eventId, int value);

    List<Event> findAllEventsByLocation_Id(Long locationId);
}
