package com.personal.saurabhp.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personal.saurabhp.order.entity.Order;

public interface ReorderRepository extends JpaRepository<Order, String>{
	Order findByOrderid(String orderid);
}
