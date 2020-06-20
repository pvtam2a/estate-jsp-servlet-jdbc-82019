package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.UserDTO;

public interface IUserService {
	UserDTO save(UserDTO userDTO);
	UserDTO findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
