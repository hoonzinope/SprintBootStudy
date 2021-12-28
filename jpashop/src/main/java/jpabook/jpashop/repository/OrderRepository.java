package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long orderId) {
        return em.find(Order.class, orderId);
    }

    public List<Order> findAll(OrderSearch orderSearch) {
        List<Order> orderList = em.createQuery("SELECT o FROM Order o join o.member m " +
                        "WHERE o.status = :status " +
                        "AND m.name LIKE :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
//                .setFirstResult(0) paging
//                .setMaxResults(1000) 개수 제한
                .getResultList();
        return orderList;

        // 하지만 선택된 status, name 이 없다면 전부 가져오게끔 구현 되어야 함 -> 동적쿼리 필요성
        // queryDSL로 해결 깔끔하게 가능

    }


}
