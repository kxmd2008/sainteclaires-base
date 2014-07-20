package org.luis.sainteclaires.base.bean.service;

import org.apache.log4j.Logger;
import org.luis.basic.domain.FilterAttributes;
import org.luis.basic.rest.model.SimpleMessage;
import org.luis.basic.rest.model.SimpleMessageHead;
import org.luis.basic.util.Encrypt;
import org.luis.basic.util.StringUtils;
import org.luis.sainteclaires.base.bean.Account;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
	private static final Logger log = Logger.getLogger(AccountService.class);

	/**
	 * 注册用户
	 * @param account
	 * @return
	 */
	public SimpleMessage<?> registion(Account account) {
		SimpleMessage<?> sm = new SimpleMessage<Object>();
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
	public SimpleMessage<?> checkLoginName(String loginName) {
		SimpleMessage<?> sm = new SimpleMessage<Object>();
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
	public SimpleMessage<Account> login(String loginName, String password) {
		SimpleMessage<Account> sm = new SimpleMessage<Account>();
		if(StringUtils.isNullOrBlank(loginName) || StringUtils.isNullOrBlank(password)){
			sm.getHead().setRep_code("1002");
			sm.getHead().setRep_message("error.msg.account.pwd.empty");
			return sm;
		}
		password = Encrypt.init(password).md5().genrate();
		FilterAttributes fa = FilterAttributes.blank()
				.add("loginName", loginName).add("password", password);
		Account account = ServiceFactory.getAccountService()
				.findOneByFilter(fa);
		if (account == null) {
			SimpleMessageHead head = new SimpleMessageHead("1001",
					"error.msg.account.pwd.wrong");
			sm.setHead(head);
		} else {
			sm.setHead(SimpleMessageHead.OK);
			sm.setItem(account);
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
