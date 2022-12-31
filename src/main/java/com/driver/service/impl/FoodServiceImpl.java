package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.driver.io.entity.FoodEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;

public class FoodServiceImpl implements FoodService{
	

	@Autowired
	FoodRepository repo;

	@Override
	public FoodDto createFood(FoodDto food) {
		// TODO Auto-generated method stub
		FoodEntity f=new FoodEntity();
		f.setFoodCategory(food.getFoodCategory());
		f.setFoodName(food.getFoodName());
		f.setFoodPrice(food.getFoodPrice());
		f.setFoodId(food.getFoodId());
		repo.save(f);
		food.setId(f.getId());
		return food;
	}

	@Override
	public FoodDto getFoodById(String foodId) throws Exception {
		// TODO Auto-generated method stub
		FoodEntity f=repo.findByFoodId(foodId);
		if(f==null) {
			throw new Exception("Food is not present");
			
		}
		else {
			FoodDto d=new FoodDto();
			d.setFoodCategory(f.getFoodCategory());
			d.setFoodName(f.getFoodName());
			d.setFoodPrice(f.getFoodPrice());
			d.setFoodId(f.getFoodName());
			d.setId(f.getId());
		return d;
		}
	}

	@Override
	public FoodDto updateFoodDetails(String foodId, FoodDto food) throws Exception {
		// TODO Auto-generated method stub
		FoodEntity f=repo.findByFoodId(foodId);
		if(f==null) {
			throw new Exception("Food is not present");
			
		}
		else {
			f.setFoodCategory(food.getFoodCategory());
			f.setFoodName(food.getFoodName());
			f.setFoodId(foodId);
			f.setFoodPrice(food.getFoodPrice());
			repo.save(f);
		}
		FoodDto d=new FoodDto();
		d.setFoodCategory(f.getFoodCategory());
		d.setFoodName(f.getFoodName());
		d.setFoodPrice(f.getFoodPrice());
		d.setFoodId(f.getFoodName());
		d.setId(f.getId());
	return d;
	}

	@Override
	public void deleteFoodItem(String id) throws Exception {
		FoodEntity f=repo.findByFoodId(id);
		// TODO Auto-generated method stub
		if(f==null) {
			throw new Exception("Food is not present");
			
		}
		else {
			repo.delete(f);
		}
		
	}

	@Override
	public List<FoodDto> getFoods() {
		// TODO Auto-generated method stub
		
		List<FoodEntity>foods=(List<FoodEntity>) repo.findAll();
		 List<FoodDto>dtos=new ArrayList<>();
		 for(FoodEntity f :foods) {
			 FoodDto d=new FoodDto();
				d.setFoodCategory(f.getFoodCategory());
				d.setFoodName(f.getFoodName());
				d.setFoodPrice(f.getFoodPrice());
				d.setFoodId(f.getFoodName());
				d.setId(f.getId());
				dtos.add(d);
		 }
		return d;
	}
	
}