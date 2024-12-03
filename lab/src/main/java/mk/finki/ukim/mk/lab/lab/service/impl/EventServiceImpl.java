package mk.finki.ukim.mk.lab.lab.service.impl;

import mk.finki.ukim.mk.lab.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.lab.model.Event;
import mk.finki.ukim.mk.lab.lab.model.Location;
import mk.finki.ukim.mk.lab.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.lab.repository.jpa.LocationRepositoryI;
import mk.finki.ukim.mk.lab.lab.service.EventService;
import org.springframework.stereotype.Service;
import mk.finki.ukim.mk.lab.lab.repository.jpa.EventRepositoryI;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    private final EventRepositoryI eventRepository;
    private final LocationRepositoryI locationRepository;

    public EventServiceImpl(EventRepositoryI eventRepository, LocationRepositoryI locationRepository){
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }
    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
        return eventRepository.searchEventsByName(text);
    }


    public Event save(String name, String description, double popularityScore, Long id){
        Location location = locationRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return eventRepository.save(new Event(name, description, popularityScore, location));
    }

    @Override
    public Event update(Long eventId, String name, String description, double popularityScore, Long id) {
       Event event = eventRepository.findById(eventId).orElseThrow(IllegalArgumentException::new);
       event.setName(name);
       event.setDescription(description);
       event.setPopularityScore(popularityScore);
       Location location = locationRepository.findById(id).orElseThrow(IllegalAccessError::new);
       event.setLocation(location);
       //eventRepository.delete(event);
       return eventRepository.save(event);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.delete(eventRepository.findById(id).get());
    }
    public void updateRating(Long eventId, int value){
        Event event = eventRepository.findById(eventId).orElseThrow(IllegalAccessError::new);
        if (event != null && !event.isRatingUpdated()) {
            double newPopularityScore = event.getPopularityScore() + value;

            if (newPopularityScore >= 0 && newPopularityScore <= 10) {
                event.setPopularityScore(newPopularityScore);
                event.setRatingUpdated(true);
                eventRepository.delete(event);
                eventRepository.save(event);
            }
        }
    }

    @Override
    public List<Event> findAllByLocation_Id(Long locationId) {
        return eventRepository.findAllByLocation_Id(locationId);
    }

}
