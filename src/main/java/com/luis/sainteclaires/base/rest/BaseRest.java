package com.luis.sainteclaires.base.rest;

import org.luis.basic.rest.model.SimpleMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class BaseRest {
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage login(String loginName, String password) {
		SimpleMessage sm = new SimpleMessage();

		return sm;
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage logout(String loginName) {
		SimpleMessage sm = new SimpleMessage();

		return sm;
	}
}
