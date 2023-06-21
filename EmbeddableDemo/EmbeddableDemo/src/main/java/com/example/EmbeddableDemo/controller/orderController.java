package com.example.EmbeddableDemo.controller;

import com.example.EmbeddableDemo.domain.Item;
import com.example.EmbeddableDemo.domain.OrderInfo;
import com.example.EmbeddableDemo.domain.OrderItem;
import com.example.EmbeddableDemo.dto.Order;
import com.example.EmbeddableDemo.repository.OrderInfoRepository;
import com.example.EmbeddableDemo.repository.OrderItemRepository;
import com.example.EmbeddableDemo.service.ItemService;
import com.example.EmbeddableDemo.service.OrderInfoService;
import com.example.EmbeddableDemo.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class orderController {

    @Autowired
    ItemService itemService;

    @Autowired
    OrderInfoService orderInfoService;

    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/getOrder")
    public Order getOrder(@RequestParam(name = "orderNum") Long orderNum) {
        // 1. order 가져오기
        OrderInfo orderInfo = orderInfoService.getOrderInfo(orderNum);

        // 2. orderItem 가져오기
        List<OrderItem> orderItemList = orderInfo.getOrderItem();

        // 3. item 가져오기 & menu 반환
        List<String> menuItems = new ArrayList<>();
        orderItemList.forEach(orderItem -> {
            Item item = itemService.itemReturn(orderItem.getItem_seq());
            if(item != null)
                menuItems.add(item.getName());
        });

        Order order = new Order();
        order.setOrderDesc(orderInfo.getOrderDesc());
        order.setMenuList(menuItems);

        return order;
    }


    @PostMapping("/insertOrder")
    public String order(@RequestBody Order order) {
        String orderDesc = order.getOrderDesc();
        List<String> menuList = order.getMenuList();

        // 1. item 반환
        List<Item> itemList = itemService.itemListReturn(menuList);

        // 2. 주문 정보 생성 -> empty orderItems
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderDesc(orderDesc);
        Long saveOrderInfo = orderInfoService.saveOrderInfo(orderInfo);
        
        // 3. 주문 아이템 리스트 생성
        List<OrderItem> orderItemList = new ArrayList<>();
        OrderInfo serviceOrderInfo = orderInfoService.getOrderInfo(saveOrderInfo);
        itemList.forEach(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderInfo_seq(serviceOrderInfo.getSeq());
            orderItem.setItem_seq(item.getSeq());
            orderItemList.add(orderItem);
        });
        
        // 4. 주문 아이템 저장
        orderItemService.saveOrderItems(orderItemList);
        return saveOrderInfo.toString(); // 저장된 orderInfo 의 pk반환
    }

}
