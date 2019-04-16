package com.yq.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yq.dao.AdminDao;
import com.yq.entity.Admin;
import com.yq.service.AdminService;

@Service
public class AdminService  {
	private Logger log= Logger.getLogger("");
	@Autowired
	private AdminDao adminDao;

	public Admin isExist(Map<String, Object> map) {
			return adminDao.isExist(map);
	}

	public String update(Map<String, Object> map) {

		try {
			return adminDao.update(map)+"";
		} catch (Exception e) {
			log.info("update>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+e.getMessage());
			return "-1";
		}

	}
	public String listById(Map<String, Object> map){
		return adminDao.listById(map);
	}

	public List<Admin> listByUsername(Map<String, Object> map) {
		
		return adminDao.listByUsername(map);
		
	}

	public int insert(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return adminDao.insert(map);
	}

	public int count() {
		// TODO Auto-generated method stub
		return adminDao.count();
	}

	public List<Admin> list(int i, int limit) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", i);
		map.put("limit", limit);
		return adminDao.list(map);
	}

}
