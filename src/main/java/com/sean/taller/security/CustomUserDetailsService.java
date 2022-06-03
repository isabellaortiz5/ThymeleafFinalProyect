package com.sean.taller.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sean.taller.repository.UserRepository;
import com.sean.taller.user.UserEntity;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity ue = userRepository.findByUsername(username);

		if (ue != null) {
			User.UserBuilder builder = User.withUsername(username).password(ue.getPassword()).roles(ue.getType().toString());
			return builder.build();
			
		} else {
			throw new UsernameNotFoundException("Username not found.");
		}
	}
}