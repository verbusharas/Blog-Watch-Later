package lt.verbus.svblog.model;

import lombok.Data;
import lt.verbus.svblog.enums.Role;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private Role role;

    @OneToMany
    private List<Post> posts;

    @OneToMany
    private List<Comment> comments;

}
