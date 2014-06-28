package com.luis.sainteclaires.base.security;

import org.luis.basic.rest.model.SimpleMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 管理员controller
 * @author guoliang.li
 *
 */
//@Controller
//@RequestMapping("auth")
public class LoginController {
	
	/**
	 * 转到管理员登陆页面
	 * @return
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminPage() {
		return "admin/login";
	}

//	@RequestMapping(value = "login", method = RequestMethod.POST)
//	@ResponseBody
//	public SimpleMessage login(String loginName, String password) {
//		SimpleMessage sm = new SimpleMessage();
//
//		return sm;
//	}
//	
//	@RequestMapping(value = "logout", method = RequestMethod.POST)
//	@ResponseBody
//	public SimpleMessage logout(String loginName) {
//		SimpleMessage sm = new SimpleMessage();
//
//		return sm;
//	}
	
	@RequestMapping(value = "add/category", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage addCategory() {
		SimpleMessage sm = new SimpleMessage();

		return sm;
	}
	
	@RequestMapping(value = "edit/category", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage editCategory() {
		SimpleMessage sm = new SimpleMessage();

		return sm;
	}
	
	@RequestMapping(value = "delete/category", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage deleteCategory() {
		SimpleMessage sm = new SimpleMessage();

		return sm;
	}
	
	@RequestMapping(value = "add/product", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage addProduct() {
		SimpleMessage sm = new SimpleMessage();

		return sm;
	}
	
	@RequestMapping(value = "edit/product", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage editProduct() {
		SimpleMessage sm = new SimpleMessage();

		return sm;
	}
	
	@RequestMapping(value = "delete/product", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage deleteProduct() {
		SimpleMessage sm = new SimpleMessage();

		return sm;
	}
}
