package com.sainteclaires.base.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sainteclaires.base.bean.Category;

public class BaseUtil {
	private static final Map<Long, List<Category>> cates = new ConcurrentHashMap<Long, List<Category>>();
	private static final List<Category> parents = new ArrayList<Category>();

	public static List<Category> getParentCates() {
		if (parents.isEmpty()) {

		}
		return parents;
	}

	public static List<Category> getSubCates(Long pid) {
		if (cates.get(pid) == null) {

		}
		return cates.get(pid);
	}
}
