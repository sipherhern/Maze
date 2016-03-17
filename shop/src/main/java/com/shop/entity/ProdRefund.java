package com.shop.entity;

// Generated 2015-9-29 21:11:50 by Hibernate Tools 4.0.0

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ProdRefund generated by hbm2java
 */
@Entity
@Table(name = "prod_refund", catalog = "shop")
public class ProdRefund implements java.io.Serializable {

	private Integer refundId;
	private String refundUuid;
	private String refundContent;
	private boolean refundEnabled;
	private String refundType;
	private Date refundDate;
	private BigDecimal refundPaied;
	private Set<Order> orders = new HashSet<Order>(0);
	private Set<PictureRelate> pictureRelates = new HashSet<PictureRelate>(0);

	public ProdRefund() {
	}

	public ProdRefund(String refundUuid, String refundContent,
			boolean refundEnabled, String refundType, Date refundDate,
			BigDecimal refundPaied) {
		this.refundUuid = refundUuid;
		this.refundContent = refundContent;
		this.refundEnabled = refundEnabled;
		this.refundType = refundType;
		this.refundDate = refundDate;
		this.refundPaied = refundPaied;
	}

	public ProdRefund(String refundUuid, String refundContent,
			boolean refundEnabled, String refundType, Date refundDate,
			BigDecimal refundPaied, Set<Order> orders,
			Set<PictureRelate> pictureRelates) {
		this.refundUuid = refundUuid;
		this.refundContent = refundContent;
		this.refundEnabled = refundEnabled;
		this.refundType = refundType;
		this.refundDate = refundDate;
		this.refundPaied = refundPaied;
		this.orders = orders;
		this.pictureRelates = pictureRelates;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "refund_id", unique = true, nullable = false)
	public Integer getRefundId() {
		return this.refundId;
	}

	public void setRefundId(Integer refundId) {
		this.refundId = refundId;
	}

	@Column(name = "refund_uuid", nullable = false, length = 32)
	public String getRefundUuid() {
		return this.refundUuid;
	}

	public void setRefundUuid(String refundUuid) {
		this.refundUuid = refundUuid;
	}

	@Column(name = "refund_content", nullable = false, length = 200)
	public String getRefundContent() {
		return this.refundContent;
	}

	public void setRefundContent(String refundContent) {
		this.refundContent = refundContent;
	}

	@Column(name = "refund_enabled", nullable = false)
	public boolean isRefundEnabled() {
		return this.refundEnabled;
	}

	public void setRefundEnabled(boolean refundEnabled) {
		this.refundEnabled = refundEnabled;
	}

	@Column(name = "refund_type", nullable = false, length = 15)
	public String getRefundType() {
		return this.refundType;
	}

	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "refund_date", nullable = false, length = 19)
	public Date getRefundDate() {
		return this.refundDate;
	}

	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}

	@Column(name = "refund_paied", nullable = false, precision = 11)
	public BigDecimal getRefundPaied() {
		return this.refundPaied;
	}

	public void setRefundPaied(BigDecimal refundPaied) {
		this.refundPaied = refundPaied;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prodRefund")
	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prodRefund")
	public Set<PictureRelate> getPictureRelates() {
		return this.pictureRelates;
	}

	public void setPictureRelates(Set<PictureRelate> pictureRelates) {
		this.pictureRelates = pictureRelates;
	}

}
