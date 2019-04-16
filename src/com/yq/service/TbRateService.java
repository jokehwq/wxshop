package com.yq.service;


import com.yq.entity.TbRate;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Bean
 * @since 2019-04-04
 */
public interface TbRateService {
    public List<TbRate> findAll();

    //PageResult findPage(Integer pageNum, Integer pageSize);

    void add(TbRate tbRate);

    TbRate findOne(Long id);

    void update(TbRate tbRate);

    void delete(Long[] ids);


}
