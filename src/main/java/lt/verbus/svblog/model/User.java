package lt.verbus.svblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lt.verbus.svblog.annotation.PasswordValueMatch;
import lt.verbus.svblog.annotation.Unique;
import lt.verbus.svblog.annotation.ValidPassword;
import org.hibernate.validator.constraints.URL;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;


@PasswordValueMatch.List({
        @PasswordValueMatch(
                field = "password",
                fieldMatch = "confirmPassword"
        )
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{user.register.username.not.blank}")
    @Email(message = "{user.register.username.email}")
    @Unique
    private String username;

    @ValidPassword
    @NotNull
    @NotBlank(message = "{user.register.password.not.blank}")
    private String password;

    @ValidPassword
    @NotNull
    @NotBlank(message = "{user.register.confirm-password.not.blank}")
    @Transient
    private String confirmPassword;

    @NotBlank(message = "{user.register.nickname.not.blank}")
    private String nickname;

    @Column(name="avatar_url")
    @URL(message = "{user.register.avatar-url.url}")
    @NotBlank(message = "{user.register.avatar-url.not.blank}")
    private String avatarUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="role_users",
            joinColumns = {@JoinColumn(name = "users_id")},
            inverseJoinColumns = {@JoinColumn(name ="role_id")}
    )
    private List<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
