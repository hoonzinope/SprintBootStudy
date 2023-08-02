package com.example.board.demoBoard.Dto;

import com.example.board.demoBoard.entity.Comment;
import com.example.board.demoBoard.entity.Post;
import lombok.Data;

@Data
public class InsertCommentDto {

    private String user;
    private String content;
    private String ip;
    private int ref;
    private int refOrder;
    private int refLevel;
    private Long postSeq;
    private Post post;
    private Long parentSeq;
    private Comment parent;

    public Comment toEntity() {
        return Comment.builder()
                .user(this.user)
                .content(this.content)
                .ip(this.ip)
                .REF(this.ref)
                .REFORDER(this.refOrder)
                .REFLEVEL(this.refLevel)
                .post(this.post)
                .parent(this.parent)
                .build();
    }
}
