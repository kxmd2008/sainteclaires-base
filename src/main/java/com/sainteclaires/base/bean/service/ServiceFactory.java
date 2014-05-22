package com.sainteclaires.base.bean.service;

import org.luis.basic.domain.GenericServiceBuilder;
import org.luis.basic.domain.IGenericService;
import com.sainteclaires.base.bean.Account;
import com.sainteclaires.base.bean.Category;
import com.sainteclaires.base.bean.Order;
import com.sainteclaires.base.bean.OrderDetail;
import com.sainteclaires.base.bean.Picture;
import com.sainteclaires.base.bean.Product;
import com.sainteclaires.base.bean.ProductSize;

public class ServiceFactory {

	private static IGenericService<Account> accountService;

	public static IGenericService<Account> getAccountService() {
		if (accountService == null) {
			accountService = GenericServiceBuilder.build(Account.class);
		}
		return accountService;
	}

	private static IGenericService<Category> categoryService;

	public static IGenericService<Category> getCategoryService() {
		if (categoryService == null) {
			categoryService = GenericServiceBuilder.build(Category.class);
		}
		return categoryService;
	}

	private static IGenericService<Order> orderService;

	public static IGenericService<Order> getOrderService() {
		if (orderService == null) {
			orderService = GenericServiceBuilder.build(Order.class);
		}
		return orderService;
	}

	private static IGenericService<OrderDetail> orderDetailService;

	public static IGenericService<OrderDetail> getOrderDetailService() {
		if (orderDetailService == null) {
			orderDetailService = GenericServiceBuilder.build(OrderDetail.class);
		}
		return orderDetailService;
	}

	private static IGenericService<Picture> pictureService;

	public static IGenericService<Picture> getPictureService() {
		if (pictureService == null) {
			pictureService = GenericServiceBuilder.build(Picture.class);
		}
		return pictureService;
	}

	private static IGenericService<Product> productService;

	public static IGenericService<Product> getProductService() {
		if (productService == null) {
			productService = GenericServiceBuilder.build(Product.class);
		}
		return productService;
	}

	private static IGenericService<ProductSize> productSizeService;

	public static IGenericService<ProductSize> getProductSizeService() {
		if (productSizeService == null) {
			productSizeService = GenericServiceBuilder.build(ProductSize.class);
		}
		return productSizeService;
	}

}
