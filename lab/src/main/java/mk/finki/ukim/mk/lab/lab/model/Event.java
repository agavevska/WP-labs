package mk.finki.ukim.mk.lab.lab.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Random;

@NoArgsConstructor
@Data
@Entity

public class Event {
    String name;
    String description;
    double popularityScore;

    @ManyToOne
    private Location location;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;


    public Event(String name, String description, double popularityScore, Location location) {
        this.id = new Random().nextLong();
        this.name = name;
        this.description = description;
        this.popularityScore = popularityScore;
        this.location = location;
    }

    private boolean ratingUpdated = false;
    public void setRatingUpdated(boolean ratingUpdated) {
        this.ratingUpdated = ratingUpdated;
    }

}
