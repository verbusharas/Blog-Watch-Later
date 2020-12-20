package lt.verbus.svblog.service;

import lt.verbus.svblog.exception.PostNotFoundException;
import lt.verbus.svblog.model.Post;
import lt.verbus.svblog.repository.PostRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAll(){
        List<Post> posts = postRepository.findAll();
        Collections.reverse(posts);
        return posts;
    }

    public List<Post> getAllExcept(Long id){
        List<Post> posts = postRepository.findByIdIsNot(id);
        Collections.reverse(posts);
        return posts;
    }

    public List<Post> getAllContainingType(String type){
        return postRepository.findAllByTypeContains(type);
    }

    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(PostNotFoundException::new);
    }

    public Post save(Post post){
        return postRepository.save(post);
    }

    public Post update(Post editedPost) {
        Post originalPost = getPostById(editedPost.getId());
        originalPost.setTitle(editedPost.getTitle());
        originalPost.setType(editedPost.getType());
        originalPost.setBody(editedPost.getBody());
        originalPost.setImageUrl(editedPost.getImageUrl());
        return postRepository.save(originalPost);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

}
