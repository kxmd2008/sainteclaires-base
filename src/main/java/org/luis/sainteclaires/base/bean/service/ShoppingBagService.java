package org.luis.sainteclaires.base.bean.service;

import org.luis.sainteclaires.base.bean.ShoppingBag;

/**
 * 购物车
 * @author Guoliang.Li
 *
 */
public class ShoppingBagService {
	
	public void save(ShoppingBag bag){
		ServiceFactory.getShoppingBagService().save(bag);
		
	}
}
