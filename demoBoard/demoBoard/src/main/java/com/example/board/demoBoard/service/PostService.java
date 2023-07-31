package com.example.board.demoBoard.service;

import com.example.board.demoBoard.entity.Post;
import com.example.board.demoBoard.repository.PostRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public JSONObject getPost(Long seq) {
        Optional<Post> optionalPost = postRepository.findById(seq);
        JSONObject result = new JSONObject();
        if(optionalPost.isPresent()) {
            Post post = optionalPost.get();
            result.put("seq", post.getSeq());
            result.put("title", post.getTitle());
            result.put("content", post.getContent());
            result.put("user", post.getUser());
            result.put("createAt", post.getCreateAt());
        }
        return result;
    }

    public JSONArray getPostAll() {
        JSONArray result = new JSONArray();
        postRepository.findAll().forEach(post -> {
            JSONObject postJson = new JSONObject();
            postJson.put("seq", post.getSeq());
            postJson.put("title", post.getTitle());
            postJson.put("content", post.getContent());
            postJson.put("createAt", post.getCreateAt());
            postJson.put("user", post.getUser());
            result.put(postJson);
        });
        return result;
    }

    @Transactional
    public void savePost(Post post) {
        postRepository.save(post);
    }

}
