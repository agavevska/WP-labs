package mk.finki.ukim.mk.lab.lab.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Random;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String capacity;
    private String description;

    @OneToMany(mappedBy = "location")
    List<Event>events;

    public Location() {

    }

    public Location(String name, String address, String capacity, String description) {
        this.id = new Random().nextLong();
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.description = description;
    }
}
