package com.example.EmbeddableDemo.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

public class OrderItemId implements Serializable{
    Long orderInfo_seq;
    Long item_seq;
}
