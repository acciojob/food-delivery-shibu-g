package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.service.impl.FoodServiceImpl;
import com.driver.shared.dto.FoodDto;
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
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	FoodServiceImpl service;
	
	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{

		return null;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {

		FoodDto d=new FoodDto();
		d.setFoodCategory(foodDetails.getFoodCategory());
		d.setFoodName(foodDetails.getFoodName());
		d.setFoodPrice(foodDetails.getFoodPrice());
		d.setFoodId(foodDetails.getFoodName());
		FoodDto d1=service.createFood(d);
		FoodDetailsResponse response=new FoodDetailsResponse();
		response.setFoodCategory(d1.getFoodCategory());
		response.setFoodId(d1.getFoodId());
		response.setFoodName(d1.getFoodName());
		response.setFoodPrice(d1.getFoodPrice());
		return response;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{
		
		FoodDto d=new FoodDto();
		d.setFoodCategory(foodDetails.getFoodCategory());
		d.setFoodName(foodDetails.getFoodName());
		d.setFoodPrice(foodDetails.getFoodPrice());
		d.setFoodId(foodDetails.getFoodName());
		FoodDto d1=service.updateFoodDetails(id, d);
		FoodDetailsResponse response=new FoodDetailsResponse();
		response.setFoodCategory(d1.getFoodCategory());
		response.setFoodId(d1.getFoodId());
		response.setFoodName(d1.getFoodName());
		response.setFoodPrice(d1.getFoodPrice());
		return response;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{
       service.deleteFoodItem(id);
       OperationStatusModel model=new OperationStatusModel();
       model.setOperationName("delete order");
       model.setOperationResult("success");
		return model;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {
     List<FoodDto>dtos=service.getFoods();
     List<FoodDetailsResponse>responses=new ArrayList<>();
     for(FoodDto d1:dtos) {
    	 FoodDetailsResponse response=new FoodDetailsResponse();
 		response.setFoodCategory(d1.getFoodCategory());
 		response.setFoodId(d1.getFoodId());
 		response.setFoodName(d1.getFoodName());
 		response.setFoodPrice(d1.getFoodPrice());
 		responses.add(response);
     }
		return responses;
	}
}
