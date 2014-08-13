package org.luis.sainteclaires.base.bean.service;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.luis.basic.domain.FilterAttributes;
import org.luis.sainteclaires.base.bean.Product;
import org.luis.sainteclaires.base.bean.ProductSize;
import org.luis.sainteclaires.base.bean.Size;
import org.luis.sainteclaires.base.bean.vo.ProductVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
public class ProductVoService {
	
	public ProductVo get(Long id){
		
		
		return null;
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
		if(vo.getId()  == null){
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
