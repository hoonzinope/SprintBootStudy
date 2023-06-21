package com.example.EmbeddableDemo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "orderitem")
@Data
@IdClass(OrderItemId.class)
public class OrderItem implements Serializable {

    @Id
    private Long orderInfo_seq;

    @Id
    private Long item_seq;

}
