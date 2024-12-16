package mk.finki.ukim.mk.lab.lab.repository;

import mk.finki.ukim.mk.lab.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.lab.model.Event;
import mk.finki.ukim.mk.lab.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.lab.model.Location;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
@Repository
public class EventRepository{
    private final List<EventBooking> eventBookings = new ArrayList<>();
    public List<Event> findAll() {
        return DataHolder.events;
    }

    public List<Event> searchEvents(String text) {
        return DataHolder.events.stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase())
                        || event.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(toList());
    }
    public List<EventBooking> getEventBookings() {
        return eventBookings;
    }
    public void addEventBooking(EventBooking eventBooking){
        eventBookings.add(eventBooking);
    }

    public Optional<Event> save(String name, String description, double popularityScore, Long locationId){
        Location location = DataHolder.locations.stream().filter(l -> l.getId().equals(locationId)).findFirst().orElse(null);
        Event event = new Event(name, description, popularityScore,location);
        DataHolder.events.add(event);
        return Optional.of(event);
    }
    public Event findById(Long id){
        return DataHolder.events.stream().filter(i -> i.getId().equals(id)).findFirst().orElse(null);
    }
    public void deleteById(Long id) {
        DataHolder.events.removeIf(i -> i.getId().equals(id));
    }

    public Event update(Long eventId, String name, String description, double popularityScore, Long id) {
        Event event = DataHolder.events.stream().filter(i -> i.getId().equals(eventId)).findFirst().orElse(null);
        Location location = DataHolder.locations.stream().filter(l->l.getId().equals(id)).findFirst().orElse(null);
        event.setName(name);
        event.setDescription(description);
        event.setLocation(location);
        event.setPopularityScore(popularityScore);
        DataHolder.events.remove(event);
        DataHolder.events.add(event);
        return event;
    }

    public void updateRating(Long eventId, int value){
        Event event = findById(eventId);
        if (event != null && !event.isRatingUpdated()) {
            double newPopularityScore = event.getPopularityScore() + value;

            if (newPopularityScore >= 0 && newPopularityScore <= 10) {
                event.setPopularityScore(newPopularityScore);
                event.setRatingUpdated(true);
                DataHolder.events.remove(event);
                DataHolder.events.add(event);
            }
        }
    }

    public List<Event> findAllEventsByLocation_Id(Long locationId) {
         return DataHolder.events.stream().filter(event -> event.getLocation().getId().equals(locationId)).toList();
    }
}
