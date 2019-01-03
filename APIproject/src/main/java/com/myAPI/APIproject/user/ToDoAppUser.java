package com.myAPI.APIproject.user;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class ToDoAppUser extends User {

	private String userId;
	private static final long serialVersionUID = 1L;

	public ToDoAppUser(String userId, String username, String password, Collection<String> authorities) {
		super(username, password, mapAuthorities(authorities));
		this.userId = userId;
	}

	private static Collection<? extends GrantedAuthority> mapAuthorities(Collection<String> authorities) {
		List<GrantedAuthority> resultList = new ArrayList<>();
		for (String authority : authorities) {
			resultList.add(new SimpleGrantedAuthority(authority));
		}
		return resultList;
	}
}
