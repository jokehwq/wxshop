package com.weixin.entity;

public class WxSetting {
    private Integer id;

    private String appid;

    private String appsecret;

    private String partner;

    private String partnerkey;
    private String p12name;
    private String link;
    private String netnum;
    public String getP12name() {
		return p12name;
	}

	public void setP12name(String p12name) {
		this.p12name = p12name;
	}

	

	public String getNetnum() {
		return netnum;
	}

	public void setNetnum(String netnum) {
		this.netnum = netnum;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner == null ? null : partner.trim();
    }

    public String getPartnerkey() {
        return partnerkey;
    }

    public void setPartnerkey(String partnerkey) {
        this.partnerkey = partnerkey == null ? null : partnerkey.trim();
    }

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
    
}