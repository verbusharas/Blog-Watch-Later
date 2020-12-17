package lt.verbus.svblog.service;

import lt.verbus.svblog.exception.PostNotFoundException;
import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.repository.PostRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll(){
        return postRepository.findAll();
    }

    public List<Post> getAllExcept(Long id){
        return postRepository.findByIdIsNot(id);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

}
