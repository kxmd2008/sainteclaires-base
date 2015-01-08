package org.luis.sainteclaires.base.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.http.HttpServletRequest;

import org.luis.basic.domain.FilterAttributes;
import org.luis.basic.util.BasicUtil;
import org.luis.basic.util.DateUtil;
import org.luis.basic.util.SpringContextFactory;
import org.luis.basic.util.StringUtils;
import org.luis.sainteclaires.base.INameSpace;
import org.luis.sainteclaires.base.bean.Account;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.Config;
import org.luis.sainteclaires.base.bean.Picture;
import org.luis.sainteclaires.base.bean.service.AccountService;
import org.luis.sainteclaires.base.bean.service.ProductVoService;
import org.luis.sainteclaires.base.bean.service.ServiceFactory;

public class BaseUtil {
	private static final Map<Long, List<Category>> cates = new ConcurrentHashMap<Long, List<Category>>();
	private static List<Category> parents;
	private static final Map<String, Picture> picMap = new ConcurrentHashMap<String, Picture>();
	private static Config currQuarter;
	private static final Lock picLock = new ReentrantLock();

	public static Picture getBgPic(String cateName) {
		picLock.lock();
		try {
			if (picMap.get(cateName) == null) {
				FilterAttributes fa = FilterAttributes.blank().add("key",
						cateName);
				Config config = ServiceFactory.getConfigService()
						.findOneByFilter(fa);
				Picture picture = config2Pic(config);
				picMap.put(cateName, picture);
			}
		} finally {
			picLock.unlock();
		}
		return picMap.get(cateName);
	}

	public static void storeBgPic(String cateName, Picture pic) {
		picMap.remove(cateName);
		picMap.put(cateName, pic);
	}

	public static void removeBgPic(Long id) {
		Iterator<Entry<String, Picture>> it = picMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Picture> e = it.next();
			if (e.getValue().getId().equals(id)) {
				picMap.remove(e.getValue().getName());
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Category> getParentCates() {
		if (parents == null) {
			parents = (List<Category>) ServiceFactory.getCategoryService()
					.findByHql("from Category where parentId is null");
		}
		return parents;
	}

	public static List<Category> getSubCates(Long pid) {
		if (cates.get(pid) == null) {
			FilterAttributes fa = FilterAttributes.blank().add("parentId", pid);
			List<Category> list = ServiceFactory.getCategoryService()
					.findByAttributes(fa);
			cates.put(pid, list);
		}
		return cates.get(pid);
	}

	@SuppressWarnings("unchecked")
	public static Map<Long, List<Category>> getSubCatsMap() {
		Map<Long, List<Category>> subcatMap = new HashMap<Long, List<Category>>();
		List<Category> list = (List<Category>) ServiceFactory
				.getCategoryService().findByHql(
						"from Category where parentId is not null");
		for (Category cat : list) {
			if (subcatMap.get(cat.getParentId()) == null) {
				subcatMap.put(cat.getParentId(), new ArrayList<Category>());
			}
			subcatMap.get(cat.getParentId()).add(cat);
		}
		return subcatMap;
	}

	public static void clearParentCats() {
		parents = null;
	}

	public static void clearSubCats(Long pid) {
		cates.remove(pid);
	}

	public static void clearAllSubCats() {
		cates.clear();
	}

	public static void setSessionAttr(HttpServletRequest req, String key,
			Object value) {
		req.getSession().setAttribute(key, value);
	}

	public static Object getSessionAttr(HttpServletRequest req, String key) {
		return req.getSession().getAttribute(key);
	}

	public static void removeSessionAttr(HttpServletRequest req, String key) {
		req.getSession().removeAttribute(key);
	}

	public static Account getSessionAccount(HttpServletRequest req) {
		Account account = (Account) req.getSession().getAttribute(
				INameSpace.KEY_SESSION_CUSTOMER);
		return account;
	}

	public static String getLoginName(HttpServletRequest req) {
		return (String) req.getSession().getAttribute(
				INameSpace.KEY_SESSION_USERNAME);
	}

	private static AccountService accountService;

	public static AccountService getAccountService() {
		if (accountService == null) {
			accountService = SpringContextFactory
					.getSpringBean(AccountService.class);
		}
		return accountService;
	}

	private static ProductVoService productVoService;

	public static ProductVoService getProductVoService() {
		if (productVoService == null) {
			productVoService = SpringContextFactory
					.getSpringBean(ProductVoService.class);
		}
		return productVoService;
	}

	public static String getProductPath() {
		return BasicUtil.getWebAppPath() + PRODUCT_PATH + getDatePath();
	}

	public static String getBgPath() {
		return BasicUtil.getWebAppPath() + BG_PATH + getDatePath();
	}
	
	public static String getSHowPath() {
		return BasicUtil.getWebAppPath() + SHOW_PATH;// + getDatePath();
	}
	
	public static String getSHowPath2() {
		return SHOW_PATH + getDatePath();
	}

	public static final String PRODUCT_PATH = "product/";
	public static final String BG_PATH = "images/";
	public static final String SHOW_PATH = "shows/";

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/");
	private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMdd");

	public static String getDatePath() {
		return sdf.format(new Date());
	}

	public static String getProductPath2() {
		return PRODUCT_PATH + getDatePath();
	}

	public static String getBgPath2() {
		return BG_PATH + getDatePath();
	}

	public static String getCurrDate() {
		return sdf2.format(new Date());
	}
	
	public static String getCurrDate(Date date) {
		return sdf2.format(date);
	}

	public static String getPre30() {
		return sdf2.format(DateUtil.preDay(new Date(), 30));
	}

	public static Config getCurrQuarter() {
		return currQuarter;
	}

	public static void setCurrQuarter(Config currQuarter) {
		BaseUtil.currQuarter = currQuarter;
	}

	public static Picture config2Pic(Config config) {
		Picture picture = new Picture();
		if (config != null) {
			picture.setId(config.getId());
			picture.setName(config.getKey());
			picture.setPicStr(config.getValue());
			picture.setPics(StringUtils.parseStr(config.getValue(), ","));
		}
		return picture;
	}

	/**
	 * 
	 * @return
	 */
	public static String genOrderNo() {
		String no = SequenceProvider.getInstance().genericSequence("SEQUENCE");
		String date = sdf3.format(new Date());
		StringBuilder sb = new StringBuilder();
		for (int i = 5 - no.length(); i > 0; i--) {
			sb.append("0");
		}
		return date + sb.toString() + no;
	}
	
	public static void main(String[] args) {
//		System.out.println(genOrderNo("1"));
//		System.out.println(genOrderNo("11"));
//		System.out.println(genOrderNo("111"));
	}

}
