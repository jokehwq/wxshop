package com.yq.dao;

import com.yq.entity.TbRate;

import java.util.List;

public interface WxRateDao {
    public List<TbRate> findAll();

    void insert(TbRate tbRate);

   TbRate selectByParmaryKey(Long id);

    void updateByParmaryKey(TbRate tbRate);

    void deleteByParmaryKey(Long id);
}

