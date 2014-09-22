package org.luis.sainteclaires.base.bean.service;

import java.sql.SQLException;
import java.util.List;

import org.luis.basic.util.IbatisBuilder;
import org.luis.sainteclaires.base.INameSpace;
import org.luis.sainteclaires.base.bean.Order;
import org.luis.sainteclaires.base.bean.OrderItem;
import org.luis.sainteclaires.base.bean.ProductShot;
import org.luis.sainteclaires.base.bean.ShoppingBag;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.luis.sainteclaires.base.util.SequenceProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class OrderService {

	public Order createOrder(ShoppingBag bag, String userName){
		Order order = new Order();
		order.setAccount(userName);
		order.setAmount(bag.getTotalAmount());
		order.setCustNo(userName);
		order.setOrderDate(BaseUtil.getCurrDate());
		order.setOrderTime(System.currentTimeMillis());
		order.setOrderNo(SequenceProvider.getInstance().genericSequence(INameSpace.KEY_ORDER_NO));
		boolean b = ServiceFactory.getOrderService().save(order);
		if (!b) {
			throw new RuntimeException("save Order error");
		}
		for (ProductShot shot : bag.getProductShots()) {
			OrderItem item = new OrderItem();
			item.setNum(shot.getNumber());
			item.setPrice(shot.getPrice());
			item.setProductId(shot.getProductId());
			item.setOrderId(order.getId());
			order.getItems().add(item);
			ServiceFactory.getOrderDetailService().save(item);
		}
		if (!b) {
			throw new RuntimeException("save OrderItem error");
		}
		return order;
	}
	
	@SuppressWarnings("unchecked")
	public List<Order> findUnpayOrders(String userName){
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
		return list;
	}
}
