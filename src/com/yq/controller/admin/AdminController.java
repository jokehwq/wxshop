package com.yq.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yq.entity.Admin;
import com.yq.service.AdminService;
import com.yq.util.MD5Util;

@Controller
@RequestMapping("/admin/")
public class AdminController {
	private Logger log = Logger.getLogger("");
	@Autowired
	private AdminService adminService;
	Map<String, Object> map = new HashMap<String, Object>();

	@ResponseBody
	@RequestMapping(value = "isExist.html")
	public Map<String, Object> isExist(String username, String password, HttpSession session) {
		map.clear();
		map.put("username", username);
		map.put("password", MD5Util.MD5Encode(password, ""));
		Admin admin = adminService.isExist(map);
		map.clear();
		if (admin != null) {
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("admin", admin);
			map.clear();
			map.put("code", admin.getNetnum());
			return map;
		} else {
			map.clear();
			map.put("code", 0);
			return map;
		}

	}

	@ResponseBody
	@RequestMapping(value = "update.html")
	public String update(String password, String password2, HttpSession session) {
		int admin_id = 0;
		if (session.getAttribute("admin") != null) {
			Admin admin = (Admin) session.getAttribute("admin");
			admin_id = admin.getAdmin_id();
		}
		map.put("admin_id", admin_id);
		String pwd = adminService.listById(map);
		String rs = "0";
		if (MD5Util.MD5Encode(password, "").equals(pwd)) {

			map.put("password", MD5Util.MD5Encode(password2, ""));
			map.put("pwd", password2);
			rs = adminService.update(map);
		}

		return rs;
	}
	@ResponseBody
	@RequestMapping(value = "showAdmin.html")
	public Map<String, Object> showAdmin(int page, int limit){
		int total = adminService.count();
		System.out.println("total:"+total);
		map.clear();
		map.put("code", 0);
		map.put("msg", "成功");
		map.put("count", total);
		map.put("data", adminService.list((page-1)*limit, limit));
		return map;
	}
	public static void main(String[] args) {
		String a = "1111";
		System.out.println(MD5Util.MD5Encode(a, ""));
	}
	// @ResponseBody
	// @RequestMapping(value = "update.html")
	// public String listById(){
	// map.put("admin_id", 1);
	// return adminService.listById(map);
	// }
}
