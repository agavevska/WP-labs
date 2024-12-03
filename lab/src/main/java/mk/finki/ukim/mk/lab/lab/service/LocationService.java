package mk.finki.ukim.mk.lab.lab.service;


import mk.finki.ukim.mk.lab.lab.model.Location;
import org.springframework.stereotype.Service;

import java.util.List;

public interface LocationService {
    List<Location> getAll();
}
