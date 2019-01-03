package com.myAPI.APIproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.myAPI.APIproject.db.TaskDao;

@Component
public class SecurityService implements UserDetailsService {
	private TaskDao taskDao;

	@Autowired
	public SecurityService(TaskDao taskDaoIn) {
		super();
		this.taskDao = taskDaoIn;
	}
/**method used to authorize user*/
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return taskDao.getUserByName(username);
	}
}
