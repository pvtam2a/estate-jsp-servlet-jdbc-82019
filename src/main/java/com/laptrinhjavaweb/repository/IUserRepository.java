package com.laptrinhjavaweb.repository;

import com.laptrinhjavaweb.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity>{
	UserEntity findById(Long id);
	UserEntity save(UserEntity userEntity);
	UserEntity findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
