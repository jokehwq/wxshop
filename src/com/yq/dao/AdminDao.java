package com.yq.dao;

import java.util.List;
import java.util.Map;

import com.yq.entity.Admin;

public interface AdminDao {

	public Admin isExist(Map<String, Object> map);

	public int update(Map<String, Object> map);

	public String listById(Map<String, Object> map);

	public List<Admin> listByUsername(Map<String, Object> map);

	public int insert(Map<String, Object> map);

	public int count();

	public List<Admin> list(Map<String, Object> map);
}
