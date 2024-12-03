package mk.finki.ukim.mk.lab.lab.service.impl;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.lab.model.Location;
import mk.finki.ukim.mk.lab.lab.repository.LocationRepository;
import mk.finki.ukim.mk.lab.lab.repository.jpa.LocationRepositoryI;
import mk.finki.ukim.mk.lab.lab.service.LocationService;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    //private final LocationRepository locationRepository;
    private final LocationRepositoryI locationRepository;

    public LocationServiceImpl(LocationRepositoryI locationRepository) {
        this.locationRepository = locationRepository;
    }
    @Override
    public List<Location> getAll() {
        return locationRepository.findAll();
    }
}
