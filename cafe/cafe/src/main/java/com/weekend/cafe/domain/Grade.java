package com.weekend.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Getter
@Table(name="grade")
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Grade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    private String name;
    @Column(name = "needs_count")
    private int needsCount;
}
