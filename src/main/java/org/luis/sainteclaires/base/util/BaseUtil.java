package org.luis.sainteclaires.base.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.luis.basic.domain.FilterAttributes;
import org.luis.basic.util.BasicUtil;
import org.luis.basic.util.SpringContextFactory;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.service.AccountService;
import org.luis.sainteclaires.base.bean.service.ServiceFactory;

public class BaseUtil {
	private static final Map<Long, List<Category>> cates = new ConcurrentHashMap<Long, List<Category>>();
	private static List<Category> parents;

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
			if(subcatMap.get(cat.getParentId()) == null){
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
	
	private static AccountService accountService;
	
	public static AccountService getAccountService(){
		if(accountService == null){
			accountService = SpringContextFactory.getSpringBean(AccountService.class);
		}
		return accountService;
	}
	
	public static String getProductPath(){
		return BasicUtil.getWebAppPath() + PRODUCT_PATH;
	}
	
	public static final String PRODUCT_PATH = "product/imgs/";

}
