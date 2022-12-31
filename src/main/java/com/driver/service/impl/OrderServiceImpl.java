package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;

public class OrderServiceImpl implements OrderService{
	
	@Autowired
	OrderRepository repo;

	@Override
	public OrderDto createOrder(OrderDto order) {
		// TODO Auto-generated method stub
		OrderEntity entity=new OrderEntity();
		entity.setId(order.getId());
		entity.setCost(order.getCost());
		entity.setItems(order.getItems());
		entity.setOrderId(order.getOrderId());
		entity.setStatus(true);
		entity.setUserId(order.getUserId());
		repo.save(entity);
		return order;
	}

	@Override
	public OrderDto getOrderById(String orderId) throws Exception {
		// TODO Auto-generated method stub
		OrderEntity entity=repo.findByOrderId(orderId);
		if(entity==null) {
			throw new Exception("order not present");
		}
		else {
			OrderDto dto=new OrderDto();
			dto.setCost(entity.getCost());
			dto.setId(entity.getId());
			dto.setItems(entity.getItems());
			dto.setOrderId(entity.getOrderId());
			dto.setStatus(true);
			dto.setUserId(entity.getUserId());
			return dto;
		}
	}

	@Override
	public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
		// TODO Auto-generated method stub
		OrderEntity entity=repo.findByOrderId(orderId);
		if(entity==null) {
			throw new Exception("order not present");
		}
		else {
			entity.setCost(order.getCost());
			entity.setItems(order.getItems());
			entity.setOrderId(order.getOrderId());
			entity.setStatus(true);
			entity.setUserId(order.getUserId());
			repo.save(entity);
		}
		OrderDto dto=new OrderDto();
		dto.setCost(entity.getCost());
		dto.setId(entity.getId());
		dto.setItems(entity.getItems());
		dto.setOrderId(entity.getOrderId());
		dto.setStatus(true);
		dto.setUserId(entity.getUserId());
		return dto;
	}

	@Override
	public void deleteOrder(String orderId) throws Exception {
		// TODO Auto-generated method stub
	  OrderEntity entity=repo.findByOrderId(orderId);
	  if(entity==null) {
		  throw new Exception("order not present");
	  }else {
		  repo.deleteById(entity.getId());
	  }
	}

	@Override
	public List<OrderDto> getOrders() {
		// TODO Auto-generated method stub
		
		List<OrderEntity>entities=(List<OrderEntity>) repo.findAll();
		List<OrderDto> dtos=new ArrayList<>();
		for(OrderEntity entity:entities) {
			OrderDto dto=new OrderDto();
			dto.setCost(entity.getCost());
			dto.setId(entity.getId());
			dto.setItems(entity.getItems());
			dto.setOrderId(entity.getOrderId());
			dto.setStatus(true);
			dto.setUserId(entity.getUserId());
			dtos.add(dto);
		}
		return dtos;
	}
	
}