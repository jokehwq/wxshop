package com.yq.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class TbRate implements Serializable{
    private Integer id;
    /**
     * 需要转换的货币单位
     */
    private String transform;

    public TbRate(Integer id, String transform, String target, BigDecimal relevant, String netnum) {
        this.id = id;
        this.transform = transform;
        this.target = target;
        this.relevant = relevant;
        this.netnum = netnum;
    }

    /**
     * 转换到的目标货币单位
     */

    private String  target;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTransform() {
        return transform;
    }

    public void setTransform(String transform) {
        this.transform = transform;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public BigDecimal getRelevant() {
        return relevant;
    }

    public void setRelevant(BigDecimal relevant) {
        this.relevant = relevant;
    }

    public String getNetnum() {
        return netnum;
    }

    public void setNetnum(String netnum) {
        this.netnum = netnum;
    }

    /**
     * 相应的汇率

     */
    private BigDecimal relevant;
    private String netnum;

    @Override
    public String toString() {
        return "TbRate{" +
                "id=" + id +
                ", transform='" + transform + '\'' +
                ", target='" + target + '\'' +
                ", relevant=" + relevant +
                ", netnum='" + netnum + '\'' +
                '}';
    }
}
