package com.example.EmbeddableDemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private String orderDesc;
    private List<String> menuList;
}
