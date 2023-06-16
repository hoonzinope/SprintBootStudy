package com.weekend.cafe.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="orderItem")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrderItem implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name="orderInfo_seq")
    private OrderInfo orderInfo;

    @Id
    @ManyToOne
    @JoinColumn(name="menu_seq")
    private Menu menu;

}
