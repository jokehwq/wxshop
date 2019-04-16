package com.yq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.weixin.service.WxSettingService;
import com.yq.entity.Admin;
import com.yq.entity.Banner;
import com.yq.service.AdminService;
import com.yq.service.BannerService;
import com.yq.util.MD5Util;

@Controller
@RequestMapping("/")
public class BannerCtrl {
	@Autowired
	private BannerService bannerService;
	private Banner banner = new Banner();
	@Autowired
	private AdminService adminService;
	private Admin admin = new Admin();
	@Autowired
	private WxSettingService wxSettingService;
	
	Map<String, Object> map = new HashMap<String, Object>();

	@RequestMapping(value = "/main/banAddjsp.html")
	public ModelAndView addjsp() {
		return new ModelAndView("main/banner/add");
	}
	@ResponseBody
	@RequestMapping(value = "/main/addAdmin.html")
	public String addAdmin(String username, String password, String appid,
			String appsecret, String partner, String partnerkey, String netnum, String remark, String p12name) {
		map.clear();
		map.put("username", username);
		List<Admin> listAdmin = adminService.listByUsername(map);
		if(listAdmin.size() > 0){
			return "账户已存在";
		}
		map.clear();
		map.put("username", username);
		map.put("password", MD5Util.MD5Encode(password, ""));
		map.put("netnum", netnum);
		map.put("remark", remark);
		map.put("pwd", password);
		int sum = adminService.insert(map);
//		List<Admin> listAdminOn = adminService.listByUsername(map);
//		System.out.println("listAdminOn.get(0).getAdmin_id():"+listAdminOn);
		map.clear();
		map.put("netnum", netnum);
		map.put("appid", appid);
		map.put("appsecret", appsecret);
		map.put("partner", partner);
		map.put("partnerkey", partnerkey);
		map.put("p12name", p12name);
		map.put("link", "http://kd.hyuusoft.com/wxshop");
		sum = wxSettingService.insert(map);
		return sum > 0 ? "添加成功":"添加失败";
	}
	@ResponseBody
	@RequestMapping(value = "/main/banInsert.html")
	public String insert(String ban_img, String url, Integer status,
			Integer sort, Integer type, String netnum) {
		map.put("url", url);
		map.put("type", type);
		map.put("ban_img", ban_img);
		map.put("status", 1);
		map.put("sort", 0);
		map.put("netnum", netnum);
		return bannerService.insert(map) + "";
	}

	@ResponseBody
	@RequestMapping(value = "/main/banUpdate.html")
	public Object update(Integer ban_id, String ban_img, String url) {
		map.put("url", url);
		map.put("ban_img", ban_img);
		map.put("ban_id", ban_id);
		return bannerService.update(map) + "";

	}

	@ResponseBody
	@RequestMapping(value = "/main/banUpstatus.html")
	public Object upstatus(Integer ban_id, Integer status) {
		map.put("status", status);
		map.put("ban_id", ban_id);
		return bannerService.upstatus(map) + "";
	}

	@ResponseBody
	@RequestMapping(value = "/main/banSort.html")
	public Object sort(Integer ban_id, Integer sort) {
		map.put("sort", sort);
		map.put("ban_id", ban_id);
		return bannerService.sort(map) + "";
	}

	@RequestMapping(value = "/main/banList.html")
	public ModelAndView list(Integer status, String netnum) {
		banner.setStatus(status);
		banner.setNetnum(netnum);
		List<Banner> list = bannerService.list(banner);
		ModelAndView ml = new ModelAndView();
		ml.addObject("list", list);
		ml.setViewName("main/banner/list");
		return ml;
	}

	@RequestMapping(value = "/main/banListById.html")
	public ModelAndView listById(Integer ban_id) {
		banner.setBan_id(ban_id);
		List<Banner> list = bannerService.listById(banner);
		ModelAndView ml = new ModelAndView();
		ml.addObject("list", list);
		ml.setViewName("main/banner/info");
		return ml;
	}
}
