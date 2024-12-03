package mk.finki.ukim.mk.lab.lab.repository.jpa;

import mk.finki.ukim.mk.lab.lab.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepositoryI extends JpaRepository<Event, Long> {
    List<Event> findAllByLocation_Id(Long locationId);

    List<Event> searchEventsByName(String text);

}
