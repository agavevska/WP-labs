package mk.finki.ukim.mk.lab.lab.repository;

import mk.finki.ukim.mk.lab.lab.bootstrap.DataHolder;

import mk.finki.ukim.mk.lab.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LocationRepository {
    private final List<Location> locationList = new ArrayList<>();
    public List<Location> findAll() {
        return DataHolder.locations;
    }
}