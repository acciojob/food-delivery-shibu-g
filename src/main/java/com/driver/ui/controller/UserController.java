package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.UserResponse;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;

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
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl service;
	
	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{
		UserDto dto=service.getUserByUserId(id);
		if(dto==null) {
			throw new Exception("no user present");
		}
		else {
		UserResponse response=new UserResponse();
		response.setEmail(dto.getEmail());
        response.setFirstName(dto.getFirstName());
        response.setLastName(dto.getLastName());
        response.setUserId(dto.getUserId());
		return response;
		}
	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto dto=new UserDto();
		dto.setEmail(userDetails.getEmail());
        dto.setFirstName(userDetails.getFirstName());
        dto.setLastName(userDetails.getLastName());
        dto.setUserId(userDetails.getFirstName());
        UserDto dto2=service.createUser(dto);
        UserResponse response=new UserResponse();
        response.setEmail(dto2.getEmail());
        response.setFirstName(dto2.getFirstName());
        response.setLastName(dto2.getLastName());
        response.setUserId(dto2.getUserId());
		return response;
	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto dto=new UserDto();
		dto.setEmail(userDetails.getEmail());
        dto.setFirstName(userDetails.getFirstName());
        dto.setLastName(userDetails.getLastName());
        dto.setUserId(id);
        UserDto dto2=service.updateUser(id, dto);
        UserResponse response=new UserResponse();
        response.setEmail(dto2.getEmail());
        response.setFirstName(dto2.getFirstName());
        response.setLastName(dto2.getLastName());
        response.setUserId(dto2.getUserId());
		return response;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{
        service.deleteUser(id);
        OperationStatusModel model=new  OperationStatusModel();
        model.setOperationName("delete order");
        model.setOperationResult("success");
		return model;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){
		List<UserDto>dtos=service.getUsers();
		 List<UserResponse>responses=new ArrayList<>();
		for(UserDto dto2:dtos) {
			 UserResponse response=new UserResponse();
		        response.setEmail(dto2.getEmail());
		        response.setFirstName(dto2.getFirstName());
		        response.setLastName(dto2.getLastName());
		        response.setUserId(dto2.getUserId());
		        responses.add(response);
		}
		return responses;
	}
	
}
