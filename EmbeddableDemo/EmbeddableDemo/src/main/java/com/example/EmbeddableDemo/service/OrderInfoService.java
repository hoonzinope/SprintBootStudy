package com.example.EmbeddableDemo.service;

import com.example.EmbeddableDemo.domain.OrderInfo;
import com.example.EmbeddableDemo.repository.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OrderInfoService {

    @Autowired
    OrderInfoRepository orderInfoRepository;

    public OrderInfo getOrderInfo(Long seq) {
        Optional<OrderInfo> optionalOrderInfo = orderInfoRepository.findById(seq);
        return optionalOrderInfo.orElse(null);
    }

    @Transactional
    public Long saveOrderInfo(OrderInfo orderInfo) {
        OrderInfo info = orderInfoRepository.save(orderInfo);
        return info.getSeq();
    }
}
