package org.luis.sainteclaires.base.bean.service;

import org.luis.basic.domain.GenericServiceBuilder;
import org.luis.basic.domain.IGenericService;
import org.luis.sainteclaires.base.bean.Account;
import org.luis.sainteclaires.base.bean.Address;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.CategoryProduct;
import org.luis.sainteclaires.base.bean.Comment;
import org.luis.sainteclaires.base.bean.Commentator;
import org.luis.sainteclaires.base.bean.Config;
import org.luis.sainteclaires.base.bean.Order;
import org.luis.sainteclaires.base.bean.OrderItem;
import org.luis.sainteclaires.base.bean.PicShow;
import org.luis.sainteclaires.base.bean.Product;
import org.luis.sainteclaires.base.bean.ProductShot;
import org.luis.sainteclaires.base.bean.ProductSize;
import org.luis.sainteclaires.base.bean.ShoppingBag;


public class ServiceFactory {

	private static IGenericService<Account> accountService;

	public static IGenericService<Account> getAccountService() {
		if (accountService == null) {
			accountService = GenericServiceBuilder.build(Account.class);
		}
		return accountService;
	}
	
	private static IGenericService<CategoryProduct> cateProductService;

	public static IGenericService<CategoryProduct> getCateProductService() {
		if (cateProductService == null) {
			cateProductService = GenericServiceBuilder.build(CategoryProduct.class);
		}
		return cateProductService;
	}
	
	private static IGenericService<Address> addressService;

	public static IGenericService<Address> getAddressService() {
		if (addressService == null) {
			addressService = GenericServiceBuilder.build(Address.class);
		}
		return addressService;
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

	private static IGenericService<OrderItem> orderDetailService;

	public static IGenericService<OrderItem> getOrderDetailService() {
		if (orderDetailService == null) {
			orderDetailService = GenericServiceBuilder.build(OrderItem.class);
		}
		return orderDetailService;
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
	
	private static IGenericService<ProductShot> productShotService;

	public static IGenericService<ProductShot> getProductShotService() {
		if (productShotService == null) {
			productShotService = GenericServiceBuilder.build(ProductShot.class);
		}
		return productShotService;
	}
	
	private static IGenericService<ShoppingBag> shoppingBagService;

	public static IGenericService<ShoppingBag> getShoppingBagService() {
		if (shoppingBagService == null) {
			shoppingBagService = GenericServiceBuilder.build(ShoppingBag.class);
		}
		return shoppingBagService;
	}
	
	private static IGenericService<CategoryProduct> categoryProductService;

	public static IGenericService<CategoryProduct> getCategoryProductService() {
		if (categoryProductService == null) {
			categoryProductService = GenericServiceBuilder.build(CategoryProduct.class);
		}
		return categoryProductService;
	}
	
	private static IGenericService<Config> configService;

	public static IGenericService<Config> getConfigService() {
		if (configService == null) {
			configService = GenericServiceBuilder.build(Config.class);
		}
		return configService;
	}
	
	private static IGenericService<PicShow> picShowSvr;
	
	public static IGenericService<PicShow> getPicShowSvr() {
		if (picShowSvr == null) {
			picShowSvr = GenericServiceBuilder.build(PicShow.class);
		}
		return picShowSvr;
	}
	
	private static IGenericService<Comment> commGenericService;
	
	public static IGenericService<Comment> getCommGenericService() {
		if (commGenericService == null) {
			commGenericService = GenericServiceBuilder.build(Comment.class);
		}
		return commGenericService;
	}
	
	private static IGenericService<Commentator> commentatorGenericService;
	
	public static IGenericService<Commentator> getCommentatorSvr() {
		if (commentatorGenericService == null) {
			commentatorGenericService = GenericServiceBuilder.build(Commentator.class);
		}
		return commentatorGenericService;
	}

}
