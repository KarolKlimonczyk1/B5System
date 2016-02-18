package com.karol.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.core.SimpleAliasRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.karol.dao.UserDAO;
import com.karol.model.User;
import com.karol.wk.SimpleGrantedAuthority;

@Service

public class UserServiceImpl implements UserDetailsService, UserService  {

	@Inject
	UserDAO userDAO;
	
	@Override
	@Transactional
	public boolean addUser(int indexNumber, String password) {
		return userDAO.addUser(indexNumber, password);
	}

	@Override
	public UserDetails loadUserByUsername(String indexNumber)
			throws UsernameNotFoundException {
		
			User user=userDAO.loadByIndex(Integer.parseInt(indexNumber));
			
			SimpleGrantedAuthority sga=new SimpleGrantedAuthority("USER");
			
			List<SimpleGrantedAuthority> authorities =new ArrayList<SimpleGrantedAuthority>();
			
			authorities.add(sga);
			
			String index=Integer.toString(user.getIndexNumber());
			
			return new org.springframework.security.core.userdetails
	                .User(index, user.getPassword(), authorities);
	}

	
}
