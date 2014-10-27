package org.luis.sainteclaires.base.bean.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.luis.basic.util.IbatisBuilder;
import org.luis.basic.util.StringUtils;
import org.luis.sainteclaires.base.bean.Order;
import org.luis.sainteclaires.base.bean.OrderItem;
import org.luis.sainteclaires.base.util.BaseUtil;
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
		bag.setAccount(userName);
		bag.setCustNo(userName);
		if(StringUtils.isNullOrBlank(bag.getOrderNo())){
			bag.setOrderNo(BaseUtil.genOrderNo());
		}
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
	public List<Order> findOrders(String userName, String start, String end) {
		List<Order> list = null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("userName", userName);
		map.put("start", start);
		map.put("end", end);
		try {
			list = (List<Order>) IbatisBuilder.queryForList(
					"order.findOrders", map);
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
	
	
	public boolean pay(Order order) throws SQLException{
		order.setStatus(Order.STATUS_UNDEAL);
		Date date = new Date();
		order.setTradeDate(BaseUtil.getCurrDate(date));
		order.setTradeTime(date.getTime());
		ServiceFactory.getOrderService().save(order);
		
		for(OrderItem item : order.getItems()){
			item.setStatus(OrderItem.STATUS_UNDEAL);
			ServiceFactory.getOrderDetailService().save(item);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("productId", item.getProductId());
			map.put("size", item.getSize());
			map.put("num", item.getNum());
			IbatisBuilder.doUpdate("productsize.updateNum", map);
		}
		return true;
	}
	
	public void shouhuo (Long id, Integer status){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("status", status);
		try {
			IbatisBuilder.doUpdate("order.shouhuo", map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
