package com.example.EmbeddableDemo.service;

import com.example.EmbeddableDemo.domain.OrderItem;
import com.example.EmbeddableDemo.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Transactional
    public Long saverOrderItem(OrderItem orderItem) {
        return orderItemRepository.save(orderItem).getItem_seq();
    }

    @Transactional
    public void saveOrderItems(List<OrderItem> orderItemList) {
        orderItemList.forEach(orderItem -> {
            orderItemRepository.save(orderItem);
        });
    }

}
