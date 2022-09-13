package com.example.projectsem2.repository;

import com.example.projectsem2.model.Order;
import com.example.projectsem2.model.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface OrderRepository extends JpaRepository<Order,Long>, PagingAndSortingRepository<Order, Long> {
    @Query("select o from Order o where o.statusByStatusId.name in ('Cancelled', 'Pending') order by o.createdAt desc")
    List<Order> findAllByOrderByIdDesc();

    @Query("select o from Order o where o.statusByStatusId.name = 'Done'")
    List<Order> findAllOrderWhereStatusDone();

    @Query("SELECT count(id) from Order")
    Long countAllOrder();

    @Query("select count(o.id) from Order o where o.statusByStatusId.name = 'Done'")
    Long countAllOrderStatusDone();

<<<<<<< Updated upstream
    @Query("select count(o.id) from Order o where o.statusByStatusId.name in ('Cancelled', 'Pending')")
    Long countAllOrderStatusNotDone();
=======
    @Query("select count(o.id) from Order o where o.statusByStatusId.name = 'Pending'")
    Long countAllOrderStatusPending();

    @Query("select count(o.id) from Order o where o.statusByStatusId.name = 'Cancelled'")
    Long countAllOrderStatusCancelled();

    @Query("select o from Order o inner join User u on u.id = o.userByUserId.id " +
            "inner join Status s on s.id = o.statusByStatusId.id where s.id < 7 and u.id = ?1 order by o.createdAt desc ")
    List<Order> getAllByUserByUserId(Long userId);

    @Query("select o from Order o inner join User u on u.id = o.userByUserId.id " +
            "inner join Status s on s.id = o.statusByStatusId.id where s.id = 7 and u.id = ?1 order by o.createdAt desc")
    List<Order> getOrdersDone(Long userId);
>>>>>>> Stashed changes
}