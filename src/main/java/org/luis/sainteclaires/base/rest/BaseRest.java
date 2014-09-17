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
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap map) {
		List<Category> parents = BaseUtil.getParentCates();
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		map.put("parents", parents);
		map.put("subcatMap", subcatMap);
		return "common/index";
	}
	
	/**
	 * 产品购买
	 * @return
	 */
	@RequestMapping(value = "shop", method = RequestMethod.GET)
	public String shop(HttpServletRequest req , ModelMap map) {
		List<Category> parents = BaseUtil.getParentCates();
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		map.put("parents", parents);
		map.put("subcatMap", subcatMap);
		return "common/shop";
	}
	
	/**
	 * 产品查询
	 * @return
	 */
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
	
	/**
	 * 产品详情
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest req, ModelMap map) {
		Long id = Long.valueOf(req.getParameter("id"));
		ProductVo pv = productVoService.get(id);
		ShoppingBag bag = (ShoppingBag) req.getSession().getAttribute(INameSpace.KEY_SESSION_CART);
		if(bag != null && !bag.getProductShots().isEmpty()){
			ProductShot shot = bag.getProductShots().get(bag.getProductShots().size() - 1);
			pv.setSize(shot.getSize());
			pv.setNum(shot.getNumber());
			if(id.equals(shot.getProductId())){
				map.put("addSucc", Boolean.TRUE);
			}
		} 
		map.put("product", pv);
		setModel(map);
		setParentCateId(map, pv.getCategorys().get(0).getId());
		return "common/detail";
	}
	
	/**
	 * 联系我们
	 * @return
	 */
	@RequestMapping(value = "contacto", method = RequestMethod.GET)
	public String contacto(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/contacto";
	}
	
	/**
	 * 购物车
	 * @return
	 */
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public String cart(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/shoppingbag";
	}
	
	/**
	 * 退换货申请
	 * @return
	 */
	@RequestMapping(value = "changes", method = RequestMethod.GET)
	public String changes(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/changes";
	}
	
	/**
	 * 如何购买
	 * @return
	 */
	@RequestMapping(value = "howtobuy", method = RequestMethod.GET)
	public String howtobuy(HttpServletRequest req, ModelMap map){
		setModel(map);
		return "common/howtobuy";
	}
	
	/**
	 * 关于我们
	 * @return
	 */
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about(HttpServletRequest req, ModelMap map){
		setModel(map);
		return "common/about";
	}
	
	/**
	 * 法律声明
	 * @return
	 */
	@RequestMapping(value = "legal", method = RequestMethod.GET)
	public String legal(HttpServletRequest req, ModelMap map){
		setModel(map);
		return "common/legal";
	}
	
	@RequestMapping(value = "add2cart", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage<ShoppingBag> addToCart(ProductShot ps, HttpServletRequest req, ModelMap map) {
		ShoppingBag bag = (ShoppingBag) req.getSession().getAttribute(INameSpace.KEY_SESSION_CART);
		if(bag == null){
			bag = new ShoppingBag();
			bag.setCustNo(BaseUtil.getLoginName(req));
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
