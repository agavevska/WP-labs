package mk.finki.ukim.mk.lab.lab.repository.inmemory;

import mk.finki.ukim.mk.lab.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryUserRepository {
    public Optional<User> findByUsername(String username){
        return DataHolder.users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
    public Optional<User> findByUsernameAndPassword(String username, String password){
        return DataHolder.users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }
}
