package com.luis.sainteclaires.base.bean.service;

import org.apache.log4j.Logger;
import org.luis.basic.domain.FilterAttributes;
import org.luis.basic.rest.model.SimpleMessage;
import org.luis.basic.rest.model.SimpleMessageHead;
import org.springframework.stereotype.Service;

import com.luis.sainteclaires.base.bean.Account;

@Service
public class AccountService {
	private static final Logger log = Logger.getLogger(AccountService.class);

	/**
	 * 注册用户
	 * @param account
	 * @return
	 */
	public SimpleMessage registion(Account account) {
		SimpleMessage sm = new SimpleMessage();
		try {
			boolean b = ServiceFactory.getAccountService().save(account);
			if(!b){
				sm.setHead(SimpleMessageHead.OK);
			}
		} catch (Exception e) {
			log.error("注册用户出错", e);
			SimpleMessageHead head = new SimpleMessageHead("101",
					"error.msg.account.registion");
			sm.setHead(head);
		}
		
		return sm;
	}

	/**
	 * 检查登陆名是否存在
	 * 
	 * @param loginName
	 * @return
	 */
	public SimpleMessage checkLoginName(String loginName) {
		SimpleMessage sm = new SimpleMessage();
		FilterAttributes fa = FilterAttributes.blank().add("loginName",
				loginName);
		Account account = ServiceFactory.getAccountService()
				.findOneByFilter(fa);
		if (account != null) {
			SimpleMessageHead head = new SimpleMessageHead("1001",
					"error.msg.account.exist");
			sm.setHead(head);
		} else {
			sm.setHead(SimpleMessageHead.OK);
		}
		return sm;
	}

	/**
	 * 登陆
	 * @param loginName
	 * @param password
	 * @return
	 */
	public SimpleMessage login(String loginName, String password) {
		SimpleMessage sm = new SimpleMessage();
		FilterAttributes fa = FilterAttributes.blank()
				.add("loginName", loginName).add("password", password);
		Account account = ServiceFactory.getAccountService()
				.findOneByFilter(fa);
		if (account != null) {
			SimpleMessageHead head = new SimpleMessageHead("1001",
					"error.msg.account.pwd.wrong");
			sm.setHead(head);
		} else {
			sm.setHead(SimpleMessageHead.OK);
		}
		return sm;
	}
	
	/**
	 * 获取登陆人帐户
	 * @param loginName
	 * @param password
	 * @return
	 */
	public Account getAccount(String loginName) {
		FilterAttributes fa = FilterAttributes.blank()
				.add("loginName", loginName);
		Account account = ServiceFactory.getAccountService()
				.findOneByFilter(fa);
		return account;
	}
}
