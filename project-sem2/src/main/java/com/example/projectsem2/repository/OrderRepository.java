package com.example.projectsem2.repository;

import com.example.projectsem2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order,Long>, PagingAndSortingRepository<Order, Long> {
    @Query("select o from Order o order by o.id desc")
    List<Order> findAllByOrderByIdDesc();

    @Query("select o from Order o where o.statusByStatusId.name = 'Done'")
    List<Order> findAllOrderWhereStatusDone();

    @Query("SELECT count(id) from Order")
    Long countAllOrder();

    @Query("select count(o.id) from Order o where o.statusByStatusId.name = 'Done'")
    Long countAllOrderStatusDone();

    @Query("select count(o.id) from Order o where o.statusByStatusId.name = 'Pending'")
    Long countAllOrderStatusPending();

    @Query("select count(o.id) from Order o where o.statusByStatusId.name = 'Cancelled'")
    Long countAllOrderStatusCancelled();
}