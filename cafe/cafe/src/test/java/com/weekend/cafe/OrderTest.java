package com.weekend.cafe;

import com.weekend.cafe.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class OrderTest {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Test
    @Transactional
    void customerOrderTest() {
        // given
        EntityManager em = entityManagerFactory.createEntityManager();

        Customer customer = new Customer();
        customer.setName("jisoo");
        customer.setNumber("7878");
        em.persist(customer);

        Menu menu = new Menu();
        menu.setName("iced americano");
        menu.setPrice(4000);
        em.persist(menu);

        // when
        // 1. 주문(order) 생성
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCustomer(customer);

        // 2. 주문 메뉴 orderitem 생성
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderInfo(orderInfo);
        orderItem.setMenu(menu);

        if(orderInfo.getOrderItemList() != null) {
            orderInfo.getOrderItemList().add(orderItem);
        }else{
            orderInfo.setOrderItemList(new ArrayList<>(Arrays.asList(orderItem)));
        }
        em.persist(orderInfo);


        // then
        List<OrderInfo> resultList = em.createQuery("select O from OrderInfo O where O.seq > 0", OrderInfo.class).getResultList();
        System.out.println(resultList);
    }

}
