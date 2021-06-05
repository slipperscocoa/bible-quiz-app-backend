package com.biblequizappbackend.login;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MySQLUserDetailsService implements UserDetailsService {
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
			
	@Override
	public UserDetails loadUserByUsername(String username) {
		Login userLogin = loginRepository.findByUsername(username);
		if (userLogin == null) {
		throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(userLogin.getUsername(), userLogin.getPassword(), getAuthorities());
	}
	public UserDetails Save(Login newUser) {
	    newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
	    Login savedUser = loginRepository.save(newUser);
	    return new org.springframework.security.core.userdetails.User(savedUser.getUsername(), savedUser.getPassword(), getAuthorities());
	  }

	private List<SimpleGrantedAuthority> getAuthorities() {
	    List<SimpleGrantedAuthority> authList = new ArrayList<>();
	    authList.add(new SimpleGrantedAuthority("ROLE_USER"));
	    return authList;
	  }
}
