package com.sean.taller.services.intfcs;

import java.util.Optional;

import com.sean.taller.user.UserEntity;
import com.sean.taller.user.UserType;

public interface UserService {
	public <S extends UserEntity> S save(S user);

	public Optional<UserEntity> findById(long id);

	public void delete(UserEntity us);
	
	public void editPerson(long id, String username, String password, UserType type);
}
