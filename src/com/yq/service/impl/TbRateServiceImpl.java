package com.yq.service.impl;





import com.yq.dao.WxRateDao;
import com.yq.entity.TbRate;
import com.yq.service.TbRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Bean
 * @since 2019-04-04
 */
@Service
public class TbRateServiceImpl  implements TbRateService {
    @Autowired
    private WxRateDao wxRateDao;
    @Override
    public List<TbRate> findAll() {
        return wxRateDao.findAll();
    }


    @Override
    public void add(TbRate tbRate) {
        wxRateDao.insert(tbRate);
    }

    @Override
    public TbRate findOne(Long id) {
        return wxRateDao.selectByParmaryKey(id);
    }

    @Override
    public void update(TbRate tbRate) {
        wxRateDao.updateByParmaryKey(tbRate);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            wxRateDao.deleteByParmaryKey(id);
        }
    }
    }
 