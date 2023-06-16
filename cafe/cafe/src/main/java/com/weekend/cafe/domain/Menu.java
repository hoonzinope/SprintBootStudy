package com.weekend.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="menu")
@DynamicInsert
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Menu {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String name;
    private int price;
    private String info;

    @Column
    private LocalDateTime reg_date;
}
