package org.luis.sainteclaires.base.bean.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.luis.basic.domain.FilterAttributes;
import org.luis.basic.util.IbatisBuilder;
import org.luis.basic.util.StringUtils;
import org.luis.sainteclaires.base.bean.Category;
import org.luis.sainteclaires.base.bean.Product;
import org.luis.sainteclaires.base.bean.ProductSize;
import org.luis.sainteclaires.base.bean.ProductVo;
import org.luis.sainteclaires.base.bean.Size;
import org.luis.sainteclaires.base.util.BaseUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class ProductVoService {
	
	/**
	 * 根据ID查询产品
	 * @param id
	 * @return
	 */
	public ProductVo get(Long id){
		ProductVo vo = null;
		try {
			vo = (ProductVo)IbatisBuilder.queryForObject("product.findById", id);
			parsePic(vo);
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
		List<Long> cateIds = new ArrayList<Long>();
		for (Category cate : vo.getCategorys()) {
			cateIds.add(cate.getId());
		}
//		temp.substring(0, temp.length() - 1);
		vo.setCateIds(cateIds);
		return vo;
	}
	
	/**
	 * 根据类别查询产品
	 * @param cateId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ProductVo> getByCateId(Long cateId){
//		FilterAttributes fa = FilterAttributes.blank().add("categoryId",cateId);
//		List<Product> products = ServiceFactory.getProductService().findByAttributes(fa);
		List<ProductVo> list = null;
		try {
//			for (Product product : products) {
//				ProductVo vo = new ProductVo();
//				BeanUtils.copyProperties(vo, product);
//				parsePic(vo, product);
//				list.add(vo);
//			}
			list = (List<ProductVo>) IbatisBuilder.queryForList("product.findProductByCateId", cateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean save(ProductVo vo) {
		Product product = new Product();
		try {
			BeanUtils.copyProperties(product, vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		boolean b = ServiceFactory.getProductService().save(product);
		vo.setId(product.getId());
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
	
	private void parsePic(ProductVo vo){
		if(!StringUtils.isNullOrBlank(vo.getPics())){
			String[] picArray = vo.getPics().split(",");
			List<String> pics = new ArrayList<String>();
			for (String pic : picArray) {
				pics.add(BaseUtil.PRODUCT_PATH + pic);
			}
			vo.setPicList(pics);
		}
	}
	
	public static void main(String[] args) {
		System.out.println("MESES06".equals(Size.MESES06.name()));
		String s= "1234567";
		System.out.println(s.substring(0, s.length() - 1));
	}

}
