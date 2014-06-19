package com.sainteclaires.base.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

/**
 * 尺码
 * 
 * @author guoliang.li
 * 
 */
@Entity
@Table(name = "product_size")
public class ProductSize extends BaseEntity {
	private static final long serialVersionUID = -6084029923513319887L;
	private String size;
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "prodSizes")
	@JoinTable(name = "product_productsize", joinColumns = { @JoinColumn(name = "size_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id") })
	private List<Product> products;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
