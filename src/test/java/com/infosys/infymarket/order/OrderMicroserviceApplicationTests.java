package com.infosys.infymarket.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.personal.saurabhp.order.dto.OrderDTO;
import com.personal.saurabhp.order.entity.Order;
import com.personal.saurabhp.order.exception.InfyMarketException;
import com.personal.saurabhp.order.repository.OrderRepository;
import com.personal.saurabhp.order.repository.ProductsOrderRepo;
import com.personal.saurabhp.order.service.OrderService;
import com.personal.saurabhp.order.service.ProductOrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMicroserviceApplicationTests {

//	@Test
//	public void contextLoads() {
//	}

	@Mock
	OrderRepository orderRepository;

	@InjectMocks
	OrderService orderService = new OrderService();

	@Mock
	ProductsOrderRepo productsOrderedRepo;

	@InjectMocks
	ProductOrderService productOrderService = new ProductOrderService();

	@Test
	public void orderValidTest() throws InfyMarketException {
		List<Order> orderList = new ArrayList<Order>();

		Order orderEntity = new Order();
		orderEntity.setOrderId("1");
		orderEntity.setBuyerid("B101");
		orderEntity.setAmount(1000.0);
		orderEntity.setAddress("KUPT");
		orderEntity.setOrderdate(new Date(2020-9-12));
		orderEntity.setStatus("ORDERPLACED");

		orderList.add(orderEntity);

		Mockito.when(orderRepository.findAll()).thenReturn(orderList);

		List<OrderDTO> reProduct = orderService.getAllOrder();

		Assertions.assertEquals(reProduct.isEmpty(), orderList.isEmpty());

	}

	@Test
	public void orderInvalidTest() throws InfyMarketException {
		List<Order> orderList = new ArrayList<Order>();

		Order orderEntity = new Order();
		
		orderEntity.setOrderId("20");
		orderEntity.setBuyerid("B101");
		orderEntity.setAmount(1000.0);
		orderEntity.setAddress("KUPT");
		orderEntity.setOrderdate(new Date(2020-9-12));
		orderEntity.setStatus("ORDERPLACED");

		
		Optional opt = Optional.of(orderEntity);// Valid

		Optional opt1 = Optional.empty();// Invalid
		

		Mockito.when(orderRepository.findById(Mockito.anyString())).thenReturn(opt1);

//	     Mockito.when(productrepo.findAll()).thenReturn(productList);

		List<OrderDTO> reProduct = orderService.getAllOrder();
//	          System.out.println(reProduct.get(0));
		Assertions.assertEquals(reProduct.isEmpty(), orderList.isEmpty());
	}
}
