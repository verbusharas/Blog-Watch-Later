package lt.verbus.svblog.service;

import lt.verbus.svblog.exception.UserNotFoundException;
import lt.verbus.svblog.model.Role;
import lt.verbus.svblog.model.User;
import lt.verbus.svblog.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setConfirmPassword(encodedPassword);
        user.setRoles(List.of(new Role(2L, "USER")));
        return userRepository.save(user);
    }

    public User promoteUserById(Long id){
        User user = getUserById(id);
        user.getRoles().clear();
        user.getRoles().add(new Role(1L, "ADMIN"));
        user.setConfirmPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User demoteUserById(Long id){
        User user = getUserById(id);
        user.getRoles().clear();
        user.getRoles().add(new Role(2L, "USER"));
        user.setConfirmPassword(user.getPassword());
        return userRepository.save(user);
    }

    public void deleteUserById(Long id) {
        userRepository.delete(getUserById(id));
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public boolean isUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }
}
