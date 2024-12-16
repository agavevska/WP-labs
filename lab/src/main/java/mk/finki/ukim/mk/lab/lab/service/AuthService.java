package mk.finki.ukim.mk.lab.lab.service;

import mk.finki.ukim.mk.lab.lab.model.User;

public interface AuthService {
    User login(String username, String password);

}
