package mk.finki.ukim.mk.lab.lab.web.controller;

import mk.finki.ukim.mk.lab.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.lab.model.Event;
import mk.finki.ukim.mk.lab.lab.model.Location;
import mk.finki.ukim.mk.lab.lab.service.EventService;
import mk.finki.ukim.mk.lab.lab.service.LocationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping({"/events", "/"})
public class EventController {
    private final EventService eventService;
    private final LocationService locationService;


    //@Autowired
    public EventController(EventService eventService, LocationService locationService) {
        this.eventService = eventService;
        this.locationService = locationService;
    }

    @GetMapping
    public String getEventsPage(@RequestParam(required = false) String searchName,
                                @RequestParam(required = false, defaultValue = "0") Integer minRating,
                                @RequestParam(required = false) Long locationId,
                                Model model) {
        List<Event> events = eventService.listAll().stream()
                .filter(event -> searchName == null || event.getName().toLowerCase().contains(searchName.toLowerCase()))
                .filter(event -> minRating == null || minRating == 0 || event.getPopularityScore() >= minRating)
                .filter(event -> locationId == null || (event.getLocation() != null && event.getLocation().getId().equals(locationId)))
                .toList();

        model.addAttribute("events", events);
        model.addAttribute("locations", this.locationService.getAll());
        return "listEvents";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteEvent(@PathVariable Long id){

        this.eventService.deleteById(id);
        return "redirect:/events";
    }
    @GetMapping("/add-form")
    public String getAddEvent(Model model){
        List<Location> locationList = locationService.getAll();
        model.addAttribute("locations", locationList);
        model.addAttribute("bodyContent", "add-event");
        return "master-template";
    }

    @PostMapping("/add")
    public String addEvent(@RequestParam(required = false) Long eventId,
                           @RequestParam String name,
                           @RequestParam String description,
                           @RequestParam double popularityScore,
                           @RequestParam long location)
    {
        if(eventId != null){
            eventService.update(eventId, name, description, popularityScore, location);
        }else{
            eventService.save(name, description, popularityScore, location);
        }
        return "redirect:/events";
    }

    @GetMapping("/edit/{eventId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editEvent(@PathVariable Long eventId, Model model){
        Event event = eventService.listAll().stream().filter(i -> i.getId().equals(eventId)).findFirst().orElse(null);
        List<Location> locationList = locationService.getAll();
        System.out.println(event);
        model.addAttribute("event", event);
        model.addAttribute("locations", locationList);
        System.out.println("edit called");
        model.addAttribute("bodyContent", "add-event");
        return "master-template";
    }

    @GetMapping("/update-rating/{eventId}/{value}")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateRating(@PathVariable Long eventId, @PathVariable int value) {
        try {
            eventService.updateRating(eventId, value);
            return "redirect:/events";
        } catch (Exception e) {
            return "redirect:/events";
        }
    }
}
