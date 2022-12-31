package com.driver.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;

public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository repo;
	
	@Override
	public UserDto createUser(UserDto user) throws Exception {
		UserEntity entity=new UserEntity();
		entity.setEmail(user.getEmail());
		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		entity.setUserId(user.getUserId());
		repo.save(entity);
		user.setId(entity.getId());
		
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public UserDto getUser(String email) throws Exception {
		UserEntity entity=repo.findByEmail(email);
		if(entity==null) {
			return null;
		}
		else {
			UserDto dto=new UserDto();
			dto.setEmail(entity.getEmail());
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setUserId(entity.getUserId());
			dto.setId(entity.getId());
			return dto;
		}
	}

	@Override
	public UserDto getUserByUserId(String userId) throws Exception {
		// TODO Auto-generated method stub
		UserEntity entity=repo.findByUserId(userId);
		if(entity==null) {
			return null;
		}
		else {
			UserDto dto=new UserDto();
			dto.setEmail(entity.getEmail());
			dto.setFirstName(entity.getFirstName());
			dto.setLastName(entity.getLastName());
			dto.setUserId(entity.getUserId());
			dto.setId(entity.getId());
			return dto;
		}
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) throws Exception {
		// TODO Auto-generated method stub
		UserEntity entity=repo.findByUserId(userId);
		if(entity==null) {
			throw new Exception("user not present");
		}else {
			entity.setEmail(user.getEmail());
		entity.setFirstName(user.getFirstName());
		entity.setLastName(user.getLastName());
		entity.setUserId(userId);
		entity.setId(user.getId());
		
		repo.save(entity);
		}
	//	UserEntity entity=new UserEntity();
		UserDto dto=new UserDto();
		dto.setEmail(entity.getEmail());
		dto.setFirstName(entity.getFirstName());
		dto.setLastName(entity.getLastName());
		dto.setUserId(entity.getUserId());
		dto.setId(entity.getId());
		return dto;
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		UserEntity entity=repo.findByUserId(userId);
			
		if(entity==null) {
			throw new Exception("user not present");
		}else {
			repo.deleteById(entity.getId());
		}
	}

	@Override
	public List<UserDto> getUsers() {
		// TODO Auto-generated method stub
		 List<UserEntity>entity=(List<UserEntity>) repo.findAll();
		 List<UserDto>dtos=new ArrayList<>();
		 for(UserEntity user:entity) {
			 UserDto dto=new UserDto();
				dto.setEmail(user.getEmail());
				dto.setFirstName(user.getFirstName());
				dto.setLastName(user.getLastName());
				dto.setUserId(user.getUserId());
				dto.setId(user.getId());
				dtos.add(dto);
		 }
		return dtos;
	}
	
}