package com.weekend.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orderInfo")
@Getter
@DynamicInsert
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderInfo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @ManyToOne
    @JoinColumn(name="customer_seq")
    private Customer customer;

    @Column
    private LocalDateTime reg_date;

    @OneToMany(mappedBy = "orderInfo", fetch = FetchType.LAZY)
    private List<OrderItem> orderItemList;
}
