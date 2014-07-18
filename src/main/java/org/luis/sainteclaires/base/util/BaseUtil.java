package org.luis.sainteclaires.base.util;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.luis.basic.domain.FilterAttributes;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.service.ServiceFactory;

public class BaseUtil {
	private static final Map<Long, List<Category>> cates = new ConcurrentHashMap<Long, List<Category>>();
	private static List<Category> parents;

	public static List<Category> getParentCates() {
		if (parents == null) {
			parents = ServiceFactory.getCategoryService().findBySql(
					"from Category where parentId is null");
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
	
	public static void clearParentCats(){
		parents = null;
	}
	
	public static void clearSubCats(Long pid){
		cates.remove(pid);
	}

}
