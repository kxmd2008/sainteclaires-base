package org.luis.sainteclaires.base.rest;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.luis.basic.domain.FilterAttributes;
import org.luis.basic.rest.model.SimpleMessage;
import org.luis.basic.util.SpringContextFactory;
import org.luis.basic.util.SpringMvcAware;
import org.luis.sainteclaires.base.INameSpace;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.Config;
import org.luis.sainteclaires.base.bean.Order;
import org.luis.sainteclaires.base.bean.OrderItem;
import org.luis.sainteclaires.base.bean.Picture;
import org.luis.sainteclaires.base.bean.ProductShot;
import org.luis.sainteclaires.base.bean.ProductVo;
import org.luis.sainteclaires.base.bean.ShoppingBag;
import org.luis.sainteclaires.base.bean.service.ProductVoService;
import org.luis.sainteclaires.base.bean.service.ServiceFactory;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@RequestMapping
public class BaseRest {

	/**
	 * 首页
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String index(ModelMap map) {
		List<Category> parents = BaseUtil.getParentCates();
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		FilterAttributes fa = FilterAttributes.blank().add("key", "首页")
				.add("type", INameSpace.TYPE_BGPIC);
		Config config = ServiceFactory.getConfigService().findOneByFilter(fa);
		if(config != null){
			map.put("bgs", config.getValue());
		}
		map.put("parents", parents);
		map.put("subcatMap", subcatMap);
		return "common/index";
	}
	
	@RequestMapping(value="changeLocale", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage<Object> changeLocale(HttpServletRequest req, HttpServletResponse resp, String localeStr){
		String[] temp = localeStr.split("_");
		LocaleResolver localResolver = (LocaleResolver) SpringMvcAware
				.getBean("localeResolver");
		Locale locale = new Locale(temp[0], temp[1]);
		localResolver.setLocale(req, resp, locale);
		SimpleMessage<Object> sm = new SimpleMessage<Object>();
		BaseUtil.setSessionAttr(req, INameSpace.KEY_SESSION_LOCALE, localeStr);
		return sm;
	}

	/**
	 * 产品购买
	 * 
	 * @return
	 */
	@RequestMapping(value = "shop", method = RequestMethod.GET)
	public String shop(HttpServletRequest req, HttpServletResponse resp,
			ModelMap map) {
		List<Category> parents = BaseUtil.getParentCates();
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		// FilterAttributes fa = FilterAttributes.blank().add("key", "商店")
		// .add("type", INameSpace.TYPE_BGPIC);
		// Config config =
		// ServiceFactory.getConfigService().findOneByFilter(fa);
		Picture pic = BaseUtil.getBgPic("商店");
		map.put("bgs", pic.getPics());
		map.put("parents", parents);
		map.put("subcatMap", subcatMap);
		return "common/shop";
	}
	
	/**
	 * 获取category 背景图片
	 * 
	 * @return
	 */
	@RequestMapping(value = "catebg", method = RequestMethod.POST)
	@ResponseBody
	public SimpleMessage<Picture> getBgPic(String categoryName) {
		SimpleMessage<Picture> sm = new SimpleMessage<Picture>();
		Picture pic = BaseUtil.getBgPic(categoryName);
		sm.setItem(pic);
		return sm;
	}

	/**
	 * 产品查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "products", method = RequestMethod.GET)
	public String products(HttpServletRequest req, ModelMap map) {
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail(HttpServletRequest req, ModelMap map) {
		Long id = Long.valueOf(req.getParameter("id"));
		ProductVo pv = productVoService.get(id);
		Order bag = (Order) req.getSession().getAttribute(
				INameSpace.KEY_SESSION_CART);
		if (bag != null && !bag.getItems().isEmpty()) {
			OrderItem shot = bag.getItems().get(bag.getItems().size() - 1);
			pv.setSize(shot.getSize());
			pv.setNum(shot.getNum());
			if (id.equals(shot.getProductId())) {
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String contacto(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/contacto";
	}

	/**
	 * 购物车
	 * 
	 * @return
	 */
	@RequestMapping(value = "cart", method = RequestMethod.GET)
	public String cart(HttpServletRequest req, ModelMap map) {
		// Account account = BaseUtil.getSessionAccount(req);
		// if (account != null) {
		// OrderService orderService = SpringContextFactory
		// .getSpringBean(OrderService.class);
		// Order order = orderService.findUnpayOrder(account.getLoginName());
		// if(order != null){//有未支付订单
		// Order bag = (Order) BaseUtil.getSessionAttr(req,
		// INameSpace.KEY_SESSION_ORDER);
		// if(bag != null){
		// order
		// }
		// BaseUtil.setSessionAttr(req, INameSpace.KEY_SESSION_ORDER, order);
		// } else {
		// Order bag = (Order) BaseUtil.getSessionAttr(req,
		// INameSpace.KEY_SESSION_ORDER);
		// if(bag != null){
		//
		// }
		// }
		// // map.put("shopingbag", order);
		// }
		setModel(map);
		return "common/shoppingbag";
	}

	/**
	 * 退换货申请
	 * 
	 * @return
	 */
	@RequestMapping(value = "changes", method = RequestMethod.GET)
	public String changes(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/changes";
	}

	/**
	 * 如何购买
	 * 
	 * @return
	 */
	@RequestMapping(value = "howtobuy", method = RequestMethod.GET)
	public String howtobuy(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/howtobuy";
	}

	/**
	 * 关于我们
	 * 
	 * @return
	 */
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public String about(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/about";
	}

	/**
	 * 法律声明
	 * 
	 * @return
	 */
	@RequestMapping(value = "legal", method = RequestMethod.GET)
	public String legal(HttpServletRequest req, ModelMap map) {
		setModel(map);
		return "common/legal";
	}

	// @RequestMapping(value = "add2cart", method = RequestMethod.POST)
	// @ResponseBody
	public SimpleMessage<ShoppingBag> addToCart(ProductShot ps,
			HttpServletRequest req, ModelMap map) {
		ShoppingBag bag = (ShoppingBag) req.getSession().getAttribute(
				INameSpace.KEY_SESSION_CART);
		if (bag == null) {
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

	private void setModel(ModelMap map) {
		List<Category> parents = BaseUtil.getParentCates();
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		map.put("parents", parents);
		map.put("subcatMap", subcatMap);
	}

	private void setParentCateId(ModelMap map, Long subCateId) {
		Map<Long, List<Category>> subcatMap = BaseUtil.getSubCatsMap();
		Iterator<Entry<Long, List<Category>>> it = subcatMap.entrySet()
				.iterator();
		boolean isbreak = false;
		Long parentCatId = null;
		while (it.hasNext()) {
			Entry<Long, List<Category>> e = it.next();
			for (Category category : e.getValue()) {
				if (category.getId().equals(subCateId)) {
					isbreak = true;
					parentCatId = e.getKey();
					break;
				}
			}
			if (isbreak) {
				break;
			}
		}
		map.put("parentCatId", parentCatId);
	}

	private ProductVoService productVoService = SpringContextFactory
			.getSpringBean(ProductVoService.class);
}
