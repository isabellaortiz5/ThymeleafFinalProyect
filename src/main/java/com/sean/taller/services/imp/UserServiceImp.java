package com.sean.taller.services.imp;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.sean.taller.repository.UserRepository;
import com.sean.taller.services.intfcs.UserService;
import com.sean.taller.user.UserEntity;
import com.sean.taller.user.UserType;

@Service
public class UserServiceImp implements UserService{
	private UserRepository userRepo;

	public UserServiceImp(UserRepository userRepo) {

		this.userRepo = userRepo;
	}
	
	public <S extends UserEntity> S save(S user) {
		if (user.getId() == 0 || user.getPassword() == null) {
			if(user.getId() == 0) {
				throw new IllegalArgumentException("The id user is empty.");
			} else if(user.getPassword() == null) {
				throw new IllegalArgumentException("The password is empty.");
			} 
		}
		userRepo.save(user);
		return user;
	}

	public Optional<UserEntity> findById(long id) {
		return userRepo.findById(id);
	}

	public void delete(UserEntity u) {
		userRepo.delete(u);
	}

	public void editPerson(long id, String userName, String password, UserType type) {
		if (id == 0 || password == null) {
			throw new IllegalArgumentException("Verify one of the arguments is not valid.");
		}
		UserEntity ur = userRepo.findById(id).get();
		ur.setUsername(userName);
		ur.setPassword(password);
		ur.setType(type);
		
		save(ur);
	}
}
