package com.example.board.demoBoard.repository;

import com.example.board.demoBoard.entity.Comment;
import com.example.board.demoBoard.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findByPostOrderByREFAsc(Post post);
}
