package com.weixin.dao;

import java.util.Map;

import com.weixin.entity.WxSetting;

public interface WxSettingMapper {
	public  int deleteByPrimaryKey(Integer id);

	public  int insert(WxSetting record);

	public  int insertSelective(WxSetting record);

	public   WxSetting selectByPrimaryKey(WxSetting id);

	public int updateByPrimaryKeySelective(WxSetting record);

	public  int updateByPrimaryKey(WxSetting record);

	public int insertData(Map<String, Object> map);
}