package com.example.board.demoBoard.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="comment")
@Entity
@DynamicInsert
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne(targetEntity = Post.class, cascade = CascadeType.ALL)
    @JoinColumn(name="postSeq")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent", referencedColumnName = "seq")
    private Comment parent;

    public String parentName(){ return this.parent != null ? this.parent.getUser() : ""; }
    public Long parentSeq() { return this.parent != null ? this.parent.getSeq() : -1L; }
}
