package mk.finki.ukim.mk.lab.lab.service;

import mk.finki.ukim.mk.lab.lab.model.EventBooking;

import java.util.List;

public interface EventBookingService{
    EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets);
    List<EventBooking> getEventBookings();
}