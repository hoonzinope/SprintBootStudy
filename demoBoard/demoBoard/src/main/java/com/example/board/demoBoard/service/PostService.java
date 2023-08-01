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

    public Post getPostEntity(Long seq) {
        Optional<Post> optionalPost = postRepository.findById(seq);
        return optionalPost.orElse(null);
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
