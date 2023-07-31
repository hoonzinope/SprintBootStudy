package com.example.board.demoBoard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="post")
@Entity
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String title;
    private String content;
    private String user;
    private String ip;

    @Column(name = "createAt")
    private LocalDateTime createAt;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
