package lt.verbus.svblog.service;

import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.model.User;
import lt.verbus.svblog.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }

}
