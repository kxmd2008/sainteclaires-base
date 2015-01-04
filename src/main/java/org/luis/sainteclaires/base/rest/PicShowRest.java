package org.luis.sainteclaires.base.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class PicShowRest {
	
	@RequestMapping(value = "show/{id}", method = RequestMethod.GET)
	public String toDetail(){
		
		
		return "";
	}
	
}
