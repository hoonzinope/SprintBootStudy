package com.example.musicDashBoardDemo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Table(name="result")
public class Result {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String name;
    @Column(name="result_id")
    private String resultId;
    private String type;

    @Column
    private LocalDateTime createAt;

}
