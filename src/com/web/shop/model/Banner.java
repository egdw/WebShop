package com.web.shop.model;

public class Banner {
	private Integer bannderId;
	private String bannerImage;

	public Integer getBannderId() {
		return bannderId;
	}

	public void setBannderId(Integer bannderId) {
		this.bannderId = bannderId;
	}

	public String getBannerImage() {
		return bannerImage;
	}

	public void setBannerImage(String bannerImage) {
		this.bannerImage = bannerImage;
	}

	@Override
	public String toString() {
		return "Banner [bannderId=" + bannderId + ", bannerImage=" + bannerImage + "]";
	}

	public Banner() {
		super();
	}

	public Banner(Integer bannderId, String bannerImage) {
		super();
		this.bannderId = bannderId;
		this.bannerImage = bannerImage;
	}

}
