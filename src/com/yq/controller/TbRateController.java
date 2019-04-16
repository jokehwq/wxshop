package com.yq.controller;





import com.yq.entity.Result;
import com.yq.entity.TbRate;
import com.yq.service.TbRateService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Bean
 * @since 2019-04-04
 */
@Controller
@RequestMapping("/rate")
public class TbRateController {
	@Autowired
    private TbRateService tbRateService;
    @RequestMapping("/findAll")
    public List<TbRate> findAll(){
        return tbRateService.findAll();
    }

    /**
     * 分页查询
     */
   /* @RequestMapping("/findPage")
    public PageResult findPage(Integer pageNum, Integer pageSize){
        return tbRateService.findPage(pageNum,pageSize);
    }*/

    /**
     * 新增币种
     */
    @RequestMapping("/add")
    public Result add(@RequestBody TbRate tbRate){
        try {
            tbRateService.add(tbRate);
            return new Result(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"添加失败");
        }
    }

    /**
     * 基于汇率id查询
     * @return
     */
    @RequestMapping("/findOne")
    public TbRate findOne(Long id){
        return tbRateService.findOne(id);
    }

    /**
     * 修改品牌
     */
    @RequestMapping("/update")
    public Result update(@RequestBody TbRate tbRate){
        try {
            tbRateService.update(tbRate);
            return new Result(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"修改失败");
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping("/delete")
    public Result delete(Long[] ids){
        try {
            tbRateService.delete(ids);
            return new Result(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,"删除失败");
        }
    }

}

