package com.bilgeadam.onlinefoodapp.dao;

import com.bilgeadam.onlinefoodapp.domain.Order;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

//burada aynı kardeşim @repository olanlar @Service olanları söyledim zaten
@Repository
public class OrderDaoImpl implements OrderDao {

    private final EntityManager entityManager;

    public OrderDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private Session getSession() {
        return entityManager.unwrap(Session.class);
    }

    @Override
    public List<Order> getCustomerOrderHistory(Long customerId) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Order> cr = cb.createQuery(Order.class);
        Root<Order> root = cr.from(Order.class);
        cr.orderBy(cb.desc(root.get("orderedDate")));
        Query<Order> query = getSession().createQuery(cr.select(root).where(cb.equal(root.get("customer"), customerId)));
        return query.getResultList();
    }
}