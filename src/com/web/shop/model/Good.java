package com.web.shop.model;

import java.sql.Date;

public class Good {
	private Integer goodId;
	private String goodName;
	private Integer goodNum;
	private Double formerPrice;
	private Double salePrice;
	private boolean isSale;
	private Date createDate;
	private Integer goodType;
	private Integer goodFrom;
	private String goodDescription;
	private User goodUser;
	private String phoneNumber;
	private String images;
	private int goodWatchNum;
	private String firstImage;
	private boolean isGet;
	
	public Integer getGoodId() {
		return goodId;
	}

	public void setGoodId(Integer goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public Integer getGoodNum() {
		return goodNum;
	}

	public void setGoodNum(Integer goodNum) {
		this.goodNum = goodNum;
	}

	public Double getFormerPrice() {
		return formerPrice;
	}

	public void setFormerPrice(Double formerPrice) {
		this.formerPrice = formerPrice;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public boolean isSale() {
		return isSale;
	}

	public void setSale(boolean isSale) {
		this.isSale = isSale;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getGoodType() {
		return goodType;
	}

	public void setGoodType(Integer goodType) {
		this.goodType = goodType;
	}

	public Integer getGoodFrom() {
		return goodFrom;
	}

	public void setGoodFrom(Integer goodFrom) {
		this.goodFrom = goodFrom;
	}

	public String getGoodDescription() {
		return goodDescription;
	}

	public void setGoodDescription(String goodDescription) {
		this.goodDescription = goodDescription;
	}

	public User getGoodUser() {
		return goodUser;
	}

	public void setGoodUser(User goodUser) {
		this.goodUser = goodUser;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public int getGoodWatchNum() {
		return goodWatchNum;
	}

	public void setGoodWatchNum(int goodWatchNum) {
		this.goodWatchNum = goodWatchNum;
	}

	public String getFirstImage() {
		return firstImage;
	}

	public void setFirstImage(String firstImage) {
		this.firstImage = firstImage;
	}

	@Override
	public String toString() {
		return "Good [goodId=" + goodId + ", goodName=" + goodName + ", goodNum=" + goodNum + ", formerPrice="
				+ formerPrice + ", salePrice=" + salePrice + ", isSale=" + isSale + ", createDate=" + createDate
				+ ", goodType=" + goodType + ", goodFrom=" + goodFrom + ", goodDescription=" + goodDescription
				+ ", goodUser=" + goodUser + ", phoneNumber=" + phoneNumber + ", images=" + images + ", goodWatchNum="
				+ goodWatchNum + ", firstImage=" + firstImage + "]";
	}

	public Good(Integer goodId, String goodName, Integer goodNum, Double formerPrice, Double salePrice, boolean isSale,
			Date createDate, Integer goodType, Integer goodFrom, String goodDescription, User goodUser,
			String phoneNumber, String images, int goodWatchNum, String firstImage) {
		super();
		this.goodId = goodId;
		this.goodName = goodName;
		this.goodNum = goodNum;
		this.formerPrice = formerPrice;
		this.salePrice = salePrice;
		this.isSale = isSale;
		this.createDate = createDate;
		this.goodType = goodType;
		this.goodFrom = goodFrom;
		this.goodDescription = goodDescription;
		this.goodUser = goodUser;
		this.phoneNumber = phoneNumber;
		this.images = images;
		this.goodWatchNum = goodWatchNum;
		this.firstImage = firstImage;
	}

	public Good() {
		super();
	}

	public boolean isGet() {
		return isGet;
	}

	public void setGet(boolean isGet) {
		this.isGet = isGet;
	}
}
