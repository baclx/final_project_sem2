package com.example.projectsem2.repository;

import com.example.projectsem2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order,Long> {
    @Query("select o from Order o where o.statusByStatusId.name in ('Cancelled', 'Pending')")
    List<Order> findAllByOrderByIdDesc();

    @Query("select o from Order o where o.statusByStatusId.name = 'Done'")
    List<Order> findAllOrderWhereStatusDone();
}