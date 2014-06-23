package com.sainteclaires.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sainteclaires.base.bean.Account;
import com.sainteclaires.base.bean.service.AccountService;

public class UserDetailServiceImpl implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String loginName)
			throws UsernameNotFoundException {
		UserDetails user = null;  
        try {  
            // 搜索数据库以匹配用户登录名.  
            Account dbUser = accountService.getAccount(loginName);  
            // Populate the Spring User object with details from the dbUser  
            // Here we just pass the username, password, and access level  
            // getAuthorities() will translate the access level to the correct  
            // role type  
            if(dbUser != null){
            	user = new User(dbUser.getLoginName(), dbUser.getPassword()  
                        .toLowerCase(), true, true, true, true,  
                        getAuthorities(dbUser.getType()));  
            } else {
            	user = new User("", "", true, true, true, true, getAuthorities("ANONYMITY"));  
            }
            
  
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
        if (access.equalsIgnoreCase(Account.TYPE_ADMIN)) {  
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));  
        } else if(access.equalsIgnoreCase(Account.TYPE_CUSTOMER)) {
        	  // 默认拥有ROLE_USER权限  
        	authList.add(new SimpleGrantedAuthority("ROLE_USER")); 
        } else {
        	//匿名用户,未登录
        	authList.add(new SimpleGrantedAuthority("ROLE_ANONYMITY")); 
        }
        return authList;  
    }
    
    @Autowired
    private AccountService accountService;

}
