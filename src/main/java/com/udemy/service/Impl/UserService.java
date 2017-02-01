package com.udemy.service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.udemy.entity.UserRole;
import com.udemy.repository.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService{

	@Autowired
	@Qualifier("userRepository")
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.udemy.entity.User user = userRepository.findByUsername(username);
		List<GrantedAuthority> auths = buildAuthorities(user.getUserRole());
		return buildUser(user, auths);
	}

	private User buildUser(com.udemy.entity.User user, List<GrantedAuthority> autorities){
		return new User(
				user.getUsername(), 
				user.getPassword(),
				user.isEnabled(), 
				true, 
				true, 
				true, 
				autorities);
	}
	
	private List<GrantedAuthority> buildAuthorities ( Set<UserRole> userRole){
		Set<GrantedAuthority> auths = new HashSet<>();
		for (UserRole ur : userRole){
			auths.add(new SimpleGrantedAuthority(ur.getRole()));
		}
		return new ArrayList<>(auths);
	}
}
