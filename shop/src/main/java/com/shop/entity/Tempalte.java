package com.shop.entity;

// Generated 2015-9-29 21:11:50 by Hibernate Tools 4.0.0

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tempalte generated by hbm2java
 */
@Entity
@Table(name = "tempalte", catalog = "shop")
public class Tempalte implements java.io.Serializable {

	private Integer orderbyId;
	private Shipping shipping;
	private Shop shop;
	private int tempalteId;

	public Tempalte() {
	}

	public Tempalte(Shipping shipping, Shop shop, int tempalteId) {
		this.shipping = shipping;
		this.shop = shop;
		this.tempalteId = tempalteId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "orderby_id", unique = true, nullable = false)
	public Integer getOrderbyId() {
		return this.orderbyId;
	}

	public void setOrderbyId(Integer orderbyId) {
		this.orderbyId = orderbyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ship_id", nullable = false)
	public Shipping getShipping() {
		return this.shipping;
	}

	public void setShipping(Shipping shipping) {
		this.shipping = shipping;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shop_id", nullable = false)
	public Shop getShop() {
		return this.shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	@Column(name = "tempalte_id", nullable = false)
	public int getTempalteId() {
		return this.tempalteId;
	}

	public void setTempalteId(int tempalteId) {
		this.tempalteId = tempalteId;
	}

}
