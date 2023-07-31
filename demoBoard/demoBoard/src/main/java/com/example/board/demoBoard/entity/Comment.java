package com.example.board.demoBoard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="comment")
@Entity
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(targetEntity = Post.class, cascade = CascadeType.ALL)
    private Post post;

    @Column(name="REF_")
    private int REF;
    @Column(name="REF_ORDER")
    private int REFORDER;
    @Column(name="REF_LEVEL")
    private int REFLEVEL;

    private String user;
    private String ip;
    private String content;
    @Column(name="createAt")
    private LocalDateTime createAt;
}
