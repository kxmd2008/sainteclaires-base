package org.luis.sainteclaires.base.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.luis.sainteclaires.base.bean.Account;
import org.luis.sainteclaires.base.bean.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("customUserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String loginName)
			throws UsernameNotFoundException {
		UserDetails user = null;  
        try {  
            // 搜索数据库以匹配用户登录名.  
//            Account dbUser = accountService.getAccount(loginName);  
            Account dbUser = new Account();
            dbUser.setLoginName("admin");
            dbUser.setPassword("admin");
            dbUser.setType("ADMIN");
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
            authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));  
        } else if(access.equalsIgnoreCase(Account.TYPE_CUSTOMER)) {
        	  // 默认拥有ROLE_USER权限  
        	authList.add(new GrantedAuthorityImpl("ROLE_USER")); 
        } else {
        	//匿名用户,未登录
        	authList.add(new GrantedAuthorityImpl("ROLE_ANONYMITY")); 
        }
        return authList;  
    }
    
    @Autowired
    private AccountService accountService;

}
