package com.sean.taller.repository;
import org.springframework.data.repository.CrudRepository;

import com.sean.taller.user.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity,Long>{
	UserEntity findByUsername(String userName);
	
}
