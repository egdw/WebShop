package com.web.shop.model;

public class GoodComplete {
	private Integer goodCompleteId;
	private User ownerUser;
	private Good goodId;

	public Integer getGoodCompleteId() {
		return goodCompleteId;
	}

	public void setGoodCompleteId(Integer goodCompleteId) {
		this.goodCompleteId = goodCompleteId;
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}

	public Good getGoodId() {
		return goodId;
	}

	public void setGoodId(Good goodId) {
		this.goodId = goodId;
	}

}
