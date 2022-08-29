package com.example.projectsem2.repository;

import com.example.projectsem2.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    @Query(value = "SELECT od.id FROM OrderDetail od inner join Order o " +
            "ON od.orderByOrderId.id = o.id INNER JOIN User u ON " +
            "o.userByUserId.id = u.id WHERE od.createdAt = o.createdAt AND u.id=?1 ")
    List<Long> getOderDetailIdByUserId(Long userId);

}
