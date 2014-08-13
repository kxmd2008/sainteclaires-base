package org.luis.sainteclaires.base.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.luis.basic.domain.BaseEntity;

/**
 * 尺码
 * 
 * @author guoliang.li
 * 
 */
@Entity
@Table(name = "size")
public class Size extends BaseEntity {
	private static final long serialVersionUID = -6084029923513319887L;
	@Column(length=20)
	private String size;
//	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY, mappedBy = "sizes")
//	@JoinTable(name = "product_size", joinColumns = { @JoinColumn(name = "size_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "id") })
//	private List<Product> products;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}

}
