package org.luis.sainteclaires.base.bean.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.luis.basic.util.IbatisBuilder;
import org.luis.sainteclaires.base.bean.Order;
import org.luis.sainteclaires.base.bean.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class OrderService {

	public Order createOrder(Order bag, String userName) {
		//查询客户未付款订单
//		if(bag.getId() == null){
//			Order order = findUnpayOrder(userName);
//			if(order != null){
//				bag.setAmount(order.getAmount().add(bag.getAmount()));
//			}
//		}
		
		boolean b = ServiceFactory.getOrderService().save(bag);
		if (!b) {
			throw new RuntimeException("save Order error");
		}
		for (OrderItem item : bag.getItems()) {
//			OrderItem item = new OrderItem();
//			item.setNum(shot.getNum());
//			item.setPrice(shot.getPrice());
//			item.setProductId(shot.getProductId());
			if(item.getId() != null){
				continue;
			}
			item.setOrderId(bag.getId());
//			item.setSize(shot.getSize());
			item.setSum(item.getPrice().multiply(
					BigDecimal.valueOf(item.getNum())));
//			order.getItems().add(item);
			ServiceFactory.getOrderDetailService().save(item);
		}
		if (!b) {
			throw new RuntimeException("save OrderItem error");
		}
		return bag;
	}

	@SuppressWarnings("unchecked")
	public Order findUnpayOrder(String userName) {
		List<Order> list = null;
		try {
			list = (List<Order>) IbatisBuilder.queryForList(
					"order.findUnpayOrders", userName);
			for (Order order : list) {
				for (OrderItem item : order.getItems()) {
					String[] pics = item.getPic().split(",");
					item.setPic(pics[0]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Order order = null;
		if(list == null || list.isEmpty()){
			return null;
		} else {
			order = list.get(0);
		}
		return order;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> findOrders(String userName) {
		List<Order> list = null;
		try {
			list = (List<Order>) IbatisBuilder.queryForList(
					"order.findOrders", userName);
			for (Order order : list) {
				for (OrderItem item : order.getItems()) {
					String[] pics = item.getPic().split(",");
					item.setPic(pics[0]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
