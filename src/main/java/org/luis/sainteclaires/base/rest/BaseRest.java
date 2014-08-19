package org.luis.sainteclaires.base.rest;

import org.luis.basic.rest.model.SimpleMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class BaseRest {
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(String loginName, String password) {
		return "common/index";
	}
	
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(String loginName, String password) {
		SimpleMessage sm = new SimpleMessage();

		return "";
	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public String logout(String loginName) {
		SimpleMessage sm = new SimpleMessage();

		return "";
	}
}
