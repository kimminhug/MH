package com.core.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.switchuser.SwitchUserGrantedAuthority;

public class UserDetailsImpl implements UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Collection<GrantedAuthority> authorities;
	private Map<String, String> attr;
	
	private List<?> authInfo;
	private List<?> sttsMenuInfo;
	private Map<String, String> authDeptCd;
	
	private String username;
	private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

	public UserDetailsImpl(String username, String password) {
		this.username = username;
		this.password = password;
		this.authorities = new ArrayList<GrantedAuthority>();
		this.attr = new HashMap<String, String>();
	}
	
	public void addAuthority(String authority, Authentication authentication) {
		GrantedAuthority grant = new SwitchUserGrantedAuthority(authority, authentication);
		if(this.authorities.contains(grant) == false) {
			this.authorities.add(grant);
		}
	}
	
	public void addAuthority(String role) {
		GrantedAuthority auth = new SimpleGrantedAuthority(role);
		if(this.authorities.contains(auth) == false) {
			this.authorities.add(auth);
		}
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonExpired() {
		return this.accountNonExpired;
	}
	
	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isAccountNonLocked() {
		return this.accountNonLocked;
	}
	
	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public boolean isCredentialsNonExpired() {
		return this.credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public boolean isEnabled() {
		return this.enabled;
	}
	
	public void setAttr(String name, String value) {
		this.attr.put(name, value);
	}
	
	public void setAll(Map<String, String> attr) {
		this.attr.putAll(attr);
	}
	
	public Map<String, String> getAuthDeptCd() {
		return authDeptCd;
	}

	public void setAuthDeptCd(Map<String, String> authDeptCd) {
		this.authDeptCd = authDeptCd;
	}

	public List<?> getAuthInfo() {
		return this.authInfo;
	}
	
	public void setAuthInfo(List<?> authInfo) {
		this.authInfo = authInfo;
	}
	
	public List<?> getSttsMenuInfo() {
		return this.sttsMenuInfo;
	}
	
	public void setSttsMenuInfo(List<?> sttsMenuInfo) {
		this.sttsMenuInfo = sttsMenuInfo;
	}
	
	public Map<String, String> getAll() {
		return this.attr;
	}
	
	public String getAttr(String name) {				
		return this.attr.get(name);
	}
	
	public String getAttrNumber(String name) {				
		return String.valueOf(this.attr.get(name));
	}
	
	public Collection<String> getAttrNames() {
		return this.attr.keySet();
	}

}
