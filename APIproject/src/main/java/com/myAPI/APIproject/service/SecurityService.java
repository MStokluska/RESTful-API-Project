package com.myAPI.APIproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.myAPI.APIproject.db.TaskDao;
import com.myAPI.APIproject.db.UserDao;


@Component
public class SecurityService implements UserDetailsService {
	private UserDao userDao;

	@Autowired
	public SecurityService(UserDao userDaoIn) {
		super();
		this.userDao = userDaoIn;
	}

	/** method used to authorize user */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return userDao.getUserByName(username);
	}
}
