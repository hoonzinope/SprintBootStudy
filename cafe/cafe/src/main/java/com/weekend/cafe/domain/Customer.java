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
@Table(name="customer")
@DynamicInsert
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Customer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    private String name;
    private String number;
    private String info;
    private int point;

    @Column
    private LocalDateTime reg_date;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderInfo> orderInfoList;
}
