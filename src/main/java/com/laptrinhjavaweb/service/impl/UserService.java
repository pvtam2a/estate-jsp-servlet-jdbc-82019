package com.laptrinhjavaweb.service.impl;

import java.util.Date;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.IUserRepository;
import com.laptrinhjavaweb.repository.impl.UserRepository;
import com.laptrinhjavaweb.service.IUserService;

public class UserService implements IUserService{

	
	private UserConverter userConverter;	
	private IUserRepository userRepository;
	
	public UserService() {
		userConverter = new UserConverter();
		userRepository = new UserRepository();
	}
	@Override
	public UserDTO save(UserDTO userDTO) {
		UserEntity userEntity = userConverter.convertToEntity(userDTO);
		userEntity.setCreatedDate(new Date());
		userEntity.setCreatedBy("system");
		return userConverter.convertToDTO(userRepository.save(userEntity));
	}
	@Override
	public UserDTO findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {		
		return userConverter.convertToDTO(userRepository.findByUserNameAndPasswordAndStatus(userName, password, status));
	}

}
