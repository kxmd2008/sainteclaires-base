package com.sainteclaires.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sainteclaires.base.bean.Account;

public class UserDetailServiceImpl implements UserDetailsService {
	

	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		UserDetails user = null;  
        try {  
            // 搜索数据库以匹配用户登录名.  
            Account dbUser = new Account();  
            // Populate the Spring User object with details from the dbUser  
            // Here we just pass the username, password, and access level  
            // getAuthorities() will translate the access level to the correct  
            // role type  
            user = new User(dbUser.getLoginName(), dbUser.getPassword()  
                    .toLowerCase(), true, true, true, true,  
                    getAuthorities(dbUser.getType()));  
  
        } catch (Exception e) {  
            throw new UsernameNotFoundException("Error in retrieving user");  
        }  
  
        return user; 
	}
	
	  /** 
     * 获得访问角色权限 
     *  
     * @param access 
     * @return 
     */  
    public Collection<GrantedAuthority> getAuthorities(String access) {  
  
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);  
  
        // 如果参数access为1.则拥有ROLE_ADMIN权限  
        if (access.equalsIgnoreCase("ADMIN")) {  
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));  
        } else {
        	  // 默认拥有ROLE_USER权限  
        	authList.add(new SimpleGrantedAuthority("ROLE_USER")); 
        }
  
        return authList;  
    }  

}
