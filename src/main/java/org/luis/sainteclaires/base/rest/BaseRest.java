package org.luis.sainteclaires.base.rest;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.luis.basic.rest.model.SimpleMessage;
import org.luis.basic.util.SpringContextFactory;
import org.luis.sainteclaires.base.INameSpace;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.ProductShot;
import org.luis.sainteclaires.base.bean.ProductVo;
import org.luis.sainteclaires.base.bean.ShoppingBag;
import org.luis.sainteclaires.base.bean.service.ProductVoService;
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
	
	@RequestMapping(value = "shop", method = RequestMethod.GET)
	public String shop(HttpServletRequest req , ModelMap map) {
		List<Category> parents = BaseUtil.getParentCates();
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		map.put("parents", parents);
		map.put("subcatMap", subcatMap);
		return "common/shop";
	}
	
	@RequestMapping(value = "products", method = RequestMethod.GET)
	public String products(HttpServletRequest req , ModelMap map) {
		Long subCateId = Long.valueOf(req.getParameter("subCateId"));
		List<ProductVo> list = productVoService.getByCateId(subCateId);
		map.put("products", list);
		map.put("subCateId", subCateId);
		
		setModel(map);
		setParentCateId(map, subCateId);
		return "common/products";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest req, ModelMap map) {
		Long id = Long.valueOf(req.getParameter("id"));
		ProductVo pv = productVoService.get(id);
		map.put("product", pv);
		setModel(map);
		setParentCateId(map, pv.getCategorys().get(0).getId());
		return "common/detail";
	}
	
	@RequestMapping(value = "contacto", method = RequestMethod.GET)
	public String contacto(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/contacto";
	}
	
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public String cart(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/shoppingbag";
	}
	
	@RequestMapping(value = "changes", method = RequestMethod.GET)
	public String changes(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/changes";
	}
	
	@RequestMapping(value = "add2cart", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage<ShoppingBag> addToCart(ProductShot ps, HttpServletRequest req, ModelMap map) {
		ShoppingBag bag = (ShoppingBag) req.getSession().getAttribute(INameSpace.KEY_SESSION_CART);
		if(bag == null){
			bag = new ShoppingBag();
			bag.setCustNo(BaseUtil.getSessionAccount(req).getLoginName());
			bag.setTotalAmount(bag.getTotalAmount().add(ps.getPrice()));
			BaseUtil.setSessionAttr(req, INameSpace.KEY_SESSION_CART, bag);
		}
		SimpleMessage<ShoppingBag> sm = new SimpleMessage<ShoppingBag>();
		sm.setItem(bag);
		setModel(map);
		return sm;
	}
	
	private void setModel(ModelMap map){
		List<Category> parents = BaseUtil.getParentCates();
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		map.put("parents", parents);
		map.put("subcatMap", subcatMap);
	}
	
	private void setParentCateId(ModelMap map, Long subCateId){
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		Iterator<Entry<Long, List<Category>>> it = subcatMap.entrySet().iterator();
		boolean isbreak = false;
		Long parentCatId = null;
		while(it.hasNext()){
			Entry<Long, List<Category>> e = it.next();
			for (Category category : e.getValue()) {
				if(category.getId().equals(subCateId)){
					isbreak = true;
					parentCatId = e.getKey();
					break;
				}
			}
			if(isbreak){
				break;
			}
		}
		map.put("parentCatId", parentCatId);
	}
	
	private ProductVoService productVoService = SpringContextFactory
			.getSpringBean(ProductVoService.class);
}
