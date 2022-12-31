package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.entity.OrderEntity;
import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.OrderDetailsResponse;
import com.driver.service.impl.OrderServiceImpl;
import com.driver.shared.dto.OrderDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	
	@Autowired
	OrderServiceImpl service;
	
	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{
    OrderDto dto=service.getOrderById(id);
    OrderDetailsResponse response=new OrderDetailsResponse();
	response.setCost(dto.getCost());
	response.setItems(dto.getItems());
	response.setOrderId(dto.getOrderId());
	response.setStatus(true);
	response.setUserId(dto.getOrderId());
	return response;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order) {
		OrderEntity entity=new OrderEntity();
		OrderDto dto=new OrderDto();
		dto.setCost(order.getCost());
		dto.setItems(order.getItems());
		dto.setStatus(true);
		dto.setId(entity.getId());
		dto.setUserId(order.getUserId());
		dto.setOrderId(order.getUserId());
		service.createOrder(dto);
		OrderDetailsResponse response=new OrderDetailsResponse();
		response.setCost(dto.getCost());
		response.setItems(dto.getItems());
		response.setOrderId(dto.getOrderId());
		response.setStatus(true);
		response.setUserId(dto.getOrderId());
		return response;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{
		
		OrderDto dto=new OrderDto();
		dto.setCost(order.getCost());
		dto.setItems(order.getItems());
		dto.setStatus(true);
		dto.setUserId(order.getUserId());
		dto.setOrderId(order.getUserId());
		OrderDto dto2=service.updateOrderDetails(id, dto);
		OrderDetailsResponse response=new OrderDetailsResponse();
		response.setCost(dto2.getCost());
		response.setItems(dto2.getItems());
		response.setOrderId(dto2.getOrderId());
		response.setStatus(true);
		response.setUserId(dto2.getOrderId());
		return response;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		
		service.deleteOrder(id);
		OperationStatusModel model=new OperationStatusModel();
		model.setOperationName("delete order");
		model.setOperationResult("success");
		return model;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDto> dtos=service.getOrders();
		List<OrderDetailsResponse>responses=new ArrayList<>();
		for(OrderDto dto:dtos) {
			OrderDetailsResponse response=new OrderDetailsResponse();
			response.setCost(dto.getCost());
			response.setItems(dto.getItems());
			response.setOrderId(dto.getOrderId());
			response.setStatus(true);
			response.setUserId(dto.getOrderId());
			responses.add(response);
		}
		return responses;
	}
}
