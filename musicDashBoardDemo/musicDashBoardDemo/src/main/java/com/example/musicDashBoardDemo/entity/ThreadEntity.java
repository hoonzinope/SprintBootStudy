package com.example.musicDashBoardDemo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name="thread")
public class ThreadEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String name;
    private String content;

    @Column(name="result_seq")
    private Long resultSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent", referencedColumnName = "seq")
    private ThreadEntity parent;
    public String parentName(){ return this.parent != null ? this.parent.getName() : ""; }
    public Long parentSeq() { return this.parent != null ? this.parent.getSeq() : -1L; }

    private int group;

    @Column
    private LocalDateTime createAt;
}
