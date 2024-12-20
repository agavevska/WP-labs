package mk.finki.ukim.mk.lab.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.lab.model.Event;
import mk.finki.ukim.mk.lab.lab.model.Location;
import mk.finki.ukim.mk.lab.lab.model.User;
import mk.finki.ukim.mk.lab.lab.model.enumerations.Role;
import mk.finki.ukim.mk.lab.lab.repository.inmemory.InMemoryUserRepository;
import mk.finki.ukim.mk.lab.lab.repository.jpa.EventRepositoryI;
import mk.finki.ukim.mk.lab.lab.repository.jpa.LocationRepositoryI;
import mk.finki.ukim.mk.lab.lab.repository.jpa.UserRepositoryI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Event> events = new ArrayList<>();
    public static List<Location> locations = new ArrayList<>();
    public static List<User> users = null;
    private final EventRepositoryI eventRepository;
    private final LocationRepositoryI locationRepository;
    private final UserRepositoryI userRepository;


    @Autowired
    public DataHolder(EventRepositoryI eventRepository, LocationRepositoryI locationRepository, InMemoryUserRepository inMemoryUserRepository, UserRepositoryI userRepository) {
        this.eventRepository = eventRepository;
        this.locationRepository = locationRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init(){
        events = new ArrayList<>();
        events.add(new Event("Concert", "Live concert by famous band", 4.5, new Location("Skopje", "Partizanski Odredi 23", "500", "A central location")));
        events.add(new Event("Theater", "A drama performance", 4.2, new Location("Bitola", "Shirok Sokak 12", "300", "Popular street")));
        events.add(new Event("Festival", "Annual cultural festival", 4.8, new Location("Ohrid", "Ohridska 45", "150", "Near the lake")));
        events.add(new Event("Conference", "Tech conference", 4.6, new Location("Tetovo", "Ilindenska 10", "200", "A mountain area")));
        events.add(new Event("Art Exhibition", "Exhibition of modern art", 4.3, new Location("Gostivar", "Goce Delchev 77", "400", "City center")));
        events.add(new Event("Workshop", "Hands-on workshop", 4.7, new Location("Bitola", "Shirok Sokak 12", "300", "Popular street")));
        events.add(new Event("Movie Night", "Screening of classic movies", 4.1, new Location("Gostivar", "Goce Delchev 77", "400", "City center")));
        events.add(new Event("Sport Event", "Local sports competition", 4.4, new Location("Tetovo", "Ilindenska 10", "200", "A mountain area")));
        events.add(new Event("Charity Gala", "Fundraising event", 4.9, new Location("Skopje", "Partizanski Odredi 23", "500", "A central location")));
        events.add(new Event("Food Festival", "Culinary festival", 4.0, new Location("Ohrid", "Ohridska 45", "150", "Near the lake")));

        users = new ArrayList<>();
        users.add(new User("elena.atanasoska", "ea", "Elena", "Atanasoska", Role.ROLE_USER));
        users.add(new User("darko.sasanski", "ds", "Darko", "Sasanski", Role.ROLE_USER));
        users.add(new User("ana.todorovska", "at", "Ana", "Todorovska", Role.ROLE_USER));
        users.add(new User("admin", "admin", "admin", "admin", Role.ROLE_ADMIN));


        locations = new ArrayList<>();
            locations.add(new Location("Skopje", "Partizanski Odredi 23", "500", "A central location"));
            locations.add(new Location("Bitola", "Shirok Sokak 12", "300", "Popular street"));
            locations.add(new Location("Ohrid", "Ohridska 45", "150", "Near the lake"));
            locations.add(new Location("Tetovo", "Ilindenska 10", "200", "A mountain area"));
            locations.add(new Location("Gostivar", "Goce Delchev 77", "400", "City center"));
            this.locationRepository.saveAll(locations);
    }
}
