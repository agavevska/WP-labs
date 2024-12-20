package mk.finki.ukim.mk.lab.lab.repository.jpa;

import mk.finki.ukim.mk.lab.lab.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryI extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameAndPassword(String username, String password);
}
