package mk.finki.ukim.mk.lab.lab.service.impl;

import mk.finki.ukim.mk.lab.lab.model.Event;
import mk.finki.ukim.mk.lab.lab.model.Location;
import mk.finki.ukim.mk.lab.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {
//    private final EventRepositoryI eventRepository;
//    private final LocationRepositoryI locationRepository;
    private final EventRepository eventRepository;
    private final LocationRepository locationRepository;

    public EventServiceImpl(EventRepository eventRepository, LocationRepository locationRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
    }
//    public EventServiceImpl(EventRepositoryI eventRepository, LocationRepositoryI locationRepository){
//        this.eventRepository = eventRepository;
//        this.locationRepository = locationRepository;
//    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> searchEvents(String text) {
//        return eventRepository.searchEventsByName(text); so postgres
        return eventRepository.searchEvents(text);
    }


    public Optional<Event> save(String name, String description, double popularityScore, Long id){
       return eventRepository.save(name, description, popularityScore, id);
    }

    @Override
    public Event update(Long eventId, String name, String description, double popularityScore, Long id) {
//       Event event = eventRepository.findById(eventId);
//       event.setName(name);
//       event.setDescription(description);
//       event.setPopularityScore(popularityScore);
//       Location location = locationRepository.findByIdLocation(id);
//       event.setLocation(location);
       //eventRepository.delete(event);
       return eventRepository.update(eventId, name, description, popularityScore, id);
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }
    public void updateRating(Long eventId, int value){
        eventRepository.updateRating(eventId, value);
    }

    @Override
    public List<Event> findAllEventsByLocation_Id(Long locationId) {
        return eventRepository.findAllEventsByLocation_Id(locationId);
    }

}
