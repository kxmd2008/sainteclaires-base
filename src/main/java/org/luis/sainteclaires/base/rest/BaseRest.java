package org.luis.sainteclaires.base.rest;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.luis.basic.rest.model.SimpleMessage;
import org.luis.basic.util.SpringContextFactory;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.service.ProductVoService;
import org.luis.sainteclaires.base.bean.vo.ProductVo;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping
public class BaseRest {
	
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap map) {
		List<Category> parents = BaseUtil.getParentCates();
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		map.put("parents", parents);
		map.put("subcatMap", subcatMap);
		return "common/index";
	}
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
	public String products(HttpServletRequest req , ModelMap map) {
		Long subCateId = Long.valueOf(req.getParameter("subCateId"));
		List<ProductVo> list = productVoService.getByCateId(subCateId);
		map.put("products", list);
		map.put("subCateId", subCateId);
		
		List<Category> parents = BaseUtil.getParentCates();
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		map.put("parents", parents);
		map.put("subcatMap", subcatMap);
		return "common/products";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail() {
		return "common/detail";
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
	
	private ProductVoService productVoService = SpringContextFactory
			.getSpringBean(ProductVoService.class);
}
