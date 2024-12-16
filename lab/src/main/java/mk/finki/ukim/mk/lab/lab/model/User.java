package mk.finki.ukim.mk.lab.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.mk.lab.lab.model.enumerations.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event_users")
public class User implements UserDetails {

    @Id
    private String username;
    private String password;
    private String name;
    private String surname;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Event> events;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String username, String password, String name, String surname, Role role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }
}
