package org.luis.sainteclaires.base.bean.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.luis.basic.domain.FilterAttributes;
import org.luis.basic.util.StringUtils;
import org.luis.sainteclaires.base.bean.Product;
import org.luis.sainteclaires.base.bean.ProductSize;
import org.luis.sainteclaires.base.bean.Size;
import org.luis.sainteclaires.base.bean.vo.ProductVo;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class ProductVoService {
	
	public ProductVo get(Long id){
		Product product = ServiceFactory.getProductService().get(id);
		ProductVo vo = new ProductVo();
		try {
			BeanUtils.copyProperties(vo, product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FilterAttributes fa = FilterAttributes.blank().add("productId",id);
		List<ProductSize> pz = ServiceFactory.getProductSizeService()
				.findByAttributes(fa);
		for (ProductSize productSize : pz) {
			if (productSize.getSize().equals(Size.MESES06.name())) {
				vo.setMeses06(productSize.getNum());
			} else if (productSize.getSize().equals(Size.MESES09.name())) {
				vo.setMeses09(productSize.getNum());
			} else if (productSize.getSize().equals(Size.MESES12.name())) {
				vo.setMeses12(productSize.getNum());
			} else if (productSize.getSize().equals(Size.MESES18.name())) {
				vo.setMeses18(productSize.getNum());
			} else if (productSize.getSize().equals(Size.MESES24.name())) {
				vo.setMeses24(productSize.getNum());
			}
		}
		if(!StringUtils.isNullOrBlank(product.getPics())){
			String[] pics = StringUtils.split(product.getPics(), ",");
			List<String> list = new ArrayList<String>();
			for (String pic : pics) {
				list.add(BaseUtil.PRODUCT_PATH + pic);
			}
			vo.setPicList(list);
		}
		return vo;
	}

	public boolean save(ProductVo vo) {
		Product product = new Product();
		try {
			BeanUtils.copyProperties(product, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean b = ServiceFactory.getProductService().save(product);
		FilterAttributes fa = FilterAttributes.blank().add("productId",
				product.getId());
		List<ProductSize> pz = ServiceFactory.getProductSizeService()
				.findByAttributes(fa);
		if (!b) {
			throw new RuntimeException("save product error");
		}
		
		//save
		if(vo.getId()  == null || pz.isEmpty()){
			Size[] sizes = Size.values();
			for (Size size : sizes) {
				ProductSize ps = new ProductSize();
				ps.setNum(getSizeNum(size.name(), vo));
				ps.setProductId(product.getId());
				ps.setSize(size.name());
			}
		} else {//update
			for (ProductSize productSize : pz) {
				Integer num = getSizeNum(productSize.getSize(), vo);
				if(num.intValue() == productSize.getNum().intValue()){
					continue;
				}
				b = ServiceFactory.getProductSizeService().save(productSize);
				if(!b){
					break;
				}
			}
		}
		if (!b) {
			throw new RuntimeException("save productSize error");
		}
		return true;
	}
	
	private Integer getSizeNum(String size, ProductVo vo){
		if (size.equals(Size.MESES06.name())) {
			return vo.getMeses06();
		} else if (size.equals(Size.MESES09.name())) {
			return vo.getMeses09();
		} else if (size.equals(Size.MESES12.name())) {
			return vo.getMeses12();
		} else if (size.equals(Size.MESES18.name())) {
			return vo.getMeses18();
		} else if (size.equals(Size.MESES24.name())) {
			return vo.getMeses24();
		}
		return 0;
	}
	
	public static void main(String[] args) {
		System.out.println("MESES06".equals(Size.MESES06.name()));
	}

}
