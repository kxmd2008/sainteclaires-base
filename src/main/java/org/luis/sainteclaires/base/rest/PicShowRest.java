package org.luis.sainteclaires.base.rest;

import java.util.List;

import org.luis.basic.util.SpringContextFactory;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.PicShow;
import org.luis.sainteclaires.base.bean.service.PicShowService;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class PicShowRest {
	
	@RequestMapping(value = "show/pic/{id}", method = RequestMethod.GET)
	public String toDetail(ModelMap map, @PathVariable Long id){
		PicShow ps = picShowService.findDetail(id);
		map.put("ps", ps);
		return "show/pic";
	}
	
	@RequestMapping(value = "show", method = RequestMethod.GET)
	public String toShow(ModelMap map){
		List<PicShow> list = picShowService.findAll();
		map.put("showpics", list);
		setCate(map);
		return "show/showPics";
	}
	
	@RequestMapping(value = "show/{cateId}", method = RequestMethod.GET)
	public String toShowCate(ModelMap map, @PathVariable Long cateId){
		List<PicShow> list = picShowService.findByCate(cateId);
		map.put("showpics", list);
		setCate(map);
		return "show/showPics";
	}
	
	private void setCate(ModelMap map){
		List<Category> parents = BaseUtil.getParentCates();
		map.put("parents", parents);
	}
	
	private PicShowService picShowService = SpringContextFactory
			.getSpringBean(PicShowService.class);
}
