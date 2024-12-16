package mk.finki.ukim.mk.lab.lab.service.impl;

import mk.finki.ukim.mk.lab.lab.model.User;
import mk.finki.ukim.mk.lab.lab.model.exceptions.InvalidArgumentsException;
import mk.finki.ukim.mk.lab.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.mk.lab.lab.repository.jpa.UserRepositoryI;
import mk.finki.ukim.mk.lab.lab.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepositoryI userRepository;

    public AuthServiceImpl(UserRepositoryI userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentsException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}
