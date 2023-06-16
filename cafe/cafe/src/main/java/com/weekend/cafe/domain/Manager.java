package com.weekend.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="manager")
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Manager {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name="id")
    private String managerId;
    private String pw;
    private String name;

    private Integer auth;

    @Column
    private LocalDateTime reg_date;
}
