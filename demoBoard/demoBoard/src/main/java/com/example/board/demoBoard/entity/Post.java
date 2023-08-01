package com.example.board.demoBoard.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.json.JSONObject;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public JSONObject getPostJson() {
        JSONObject result = new JSONObject();
        result.put("seq", this.getSeq());
        result.put("title", this.getTitle());
        result.put("content", this.getContent());
        result.put("user", this.getUser());
        result.put("createAt", this.getCreateAt());
        return result;
    }
}
