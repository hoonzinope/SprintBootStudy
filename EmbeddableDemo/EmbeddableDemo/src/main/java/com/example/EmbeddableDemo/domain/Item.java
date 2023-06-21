package com.example.EmbeddableDemo.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="item")
@DynamicInsert
@Data
public class Item {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @OneToMany(mappedBy = "item_seq")
    private List<OrderItem> orderItem;

    private String name;
    private int price;

    @Column
    private LocalDateTime regDate;

}
