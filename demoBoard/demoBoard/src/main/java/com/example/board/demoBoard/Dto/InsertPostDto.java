package com.example.board.demoBoard.Dto;

import com.example.board.demoBoard.entity.Post;
import lombok.Data;

@Data
public class InsertPostDto {

    private String title;
    private String content;
    private String user;
    private String ip;

    public Post toEntity(){
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .user(this.user)
                .ip(this.ip)
                .build();
    }
}
