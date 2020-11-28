package lt.verbus.svblog.service;

import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

}
