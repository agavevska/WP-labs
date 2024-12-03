package mk.finki.ukim.mk.lab.lab.service.impl;

import mk.finki.ukim.mk.lab.lab.model.EventBooking;
import mk.finki.ukim.mk.lab.lab.repository.EventRepository;
import mk.finki.ukim.mk.lab.lab.service.EventBookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventBookingServiceImpl implements EventBookingService {
    private final EventRepository eventRepository;

    public EventBookingServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        EventBooking eventBooking = new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
        eventRepository.addEventBooking(eventBooking);
        return eventBooking;
    }

    @Override
    public List<EventBooking> getEventBookings() {
        return eventRepository.getEventBookings();
    }
}
