package com.yq.entity;

public class Goods extends Page{
	Integer goods_id;
	String goods_name;
	String goods_img;
	float goods_price;
	String goods_detail;
	String add_time;
	Integer ctg_id;
	Integer status;
	Integer is_recommend;
	String goods_spe;
	Integer type;
	Integer goods_num;
	String netnum;
	String thePrice;
	public String getNetnum() {
		return netnum;
	}

	public void setNetnum(String netnum) {
		this.netnum = netnum;
	}

	public Integer getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getGoods_img() {
		return goods_img;
	}

	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}


	public float getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(float goods_price) {
		this.goods_price = goods_price;
	}

	public String getGoods_detail() {
		return goods_detail;
	}

	public void setGoods_detail(String goods_detail) {
		this.goods_detail = goods_detail;
	}

	public Integer getCtg_id() {
		return ctg_id;
	}

	public void setCtg_id(Integer ctg_id) {
		this.ctg_id = ctg_id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public Integer getIs_recommend() {
		return is_recommend;
	}

	public void setIs_recommend(Integer is_recommend) {
		this.is_recommend = is_recommend;
	}

	public String getGoods_spe() {
		return goods_spe;
	}

	public void setGoods_spe(String goods_spe) {
		this.goods_spe = goods_spe;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGoods_num() {
		return goods_num;
	}

	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}

	public String getThePrice() {
		return thePrice;
	}

	public void setThePrice(String thePrice) {
		this.thePrice = thePrice;
	}

	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", goods_name=" + goods_name
				+ ", goods_img=" + goods_img + ", goods_price=" + goods_price
				+ ", goods_detail=" + goods_detail + ", add_time=" + add_time
				+ ", ctg_id=" + ctg_id + ", status=" + status
				+ ", is_recommend=" + is_recommend + ", goods_spe=" + goods_spe
				+ ", type=" + type + ", goods_num=" + goods_num + ", netnum="
				+ netnum + ", thePrice=" + thePrice + "]";
	}
                      
}
