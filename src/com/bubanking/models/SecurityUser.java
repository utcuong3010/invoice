package com.bubanking.models;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class SecurityUser extends org.springframework.security.core.userdetails.User{

	private User user;
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SecurityUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
	}
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
}
