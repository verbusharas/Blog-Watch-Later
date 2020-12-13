package lt.verbus.svblog.service;

import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.repository.PostRepository;
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

    public Post save(Post post){
        return postRepository.save(post);
    }

}
