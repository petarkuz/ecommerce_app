package mk.finki.ukim.wp.model;

import javax.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreated;

    private LocalDateTime dateExpired;

    @ManyToMany
    private List<User> users;

    public Discount() {
    }

    public Discount(LocalDateTime dateExpired) {
        this.dateCreated = LocalDateTime.now();
        this.dateExpired = dateExpired;
        this.users = new ArrayList<>();
    }
}
