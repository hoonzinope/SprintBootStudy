package com.example.board.demoBoard.service;

import com.example.board.demoBoard.Dto.InsertCommentDto;
import com.example.board.demoBoard.entity.Comment;
import com.example.board.demoBoard.entity.Post;
import com.example.board.demoBoard.repository.CommentRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void saveComment(InsertCommentDto insertCommentDto) {
        commentRepository.save(insertCommentDto.toEntity());
    }

    public JSONArray getComments(Post post) {
        JSONArray comments = new JSONArray();
        commentRepository.findByPostOrderByREFAsc(post).forEach(comment -> {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("seq", comment.getSeq());
            jsonObject.put("REF", comment.getREF());
            jsonObject.put("REFORDER", comment.getREFORDER());
            jsonObject.put("REFLEVEL", comment.getREFLEVEL());
            jsonObject.put("user", comment.getUser());
            jsonObject.put("content", comment.getContent());
            jsonObject.put("createAt", comment.getCreateAt());
//            jsonObject.put("replys", comment.getReply());
            jsonObject.put("parentName", comment.parentName());
            jsonObject.put("parentSeq", comment.parentSeq());
            comments.put(jsonObject);
        });
        return comments;
    }

    public Comment getCommentBySeq(Long seq) {
        return commentRepository.findById(seq).orElse(null);
    }
}
