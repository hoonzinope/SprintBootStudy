package com.example.EmbeddableDemo.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orderinfo")
@DynamicInsert
@Data
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(name="orderDesc")
    private String orderDesc;

    @OneToMany(mappedBy = "orderInfo_seq", cascade = CascadeType.ALL)
    private List<OrderItem> orderItem;

    @Column
    private LocalDateTime regDate;
}
