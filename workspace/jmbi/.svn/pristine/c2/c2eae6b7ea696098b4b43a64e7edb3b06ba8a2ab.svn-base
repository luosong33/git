package com.jumore.jmbi.enums;

/** 
* 数据字典
* <p>注意：开关类的数据字典请设置true表示开启，false表示关闭</p>
* @author: panweiqiang
* @since: 2016年8月15日  下午5:07:58
* @history:
*/
public enum DatadictionaryEnum {
    need_to_bid_enable(0, "需求转竞拍开关", "true"), // 需求转竞拍开关
    need_to_bid_limit(1, "需求转竞拍时间设置(单位：分)", "15"), // 需求转竞拍时间设置
    chemical_switch(0, "意向单转订单通过化工开关", "true"), //意向单转订单通过化工开关
    company_statistics_enable(0, "公司运力统计开关", "true"), //公司运力统计开关(性能可能存在问题)
    strategic_cycle(1, "战略合作刷新周期(单位：天)", "7"), //战略合作刷新周期,默认一周
    gys_approve_enable(0, "供应商自动审核开关", "true"), //供应商审核开关
    line_approve_enable(0, "线路自动审核开关", "true"), //线路审核开关
    need_approve_enable(0, "需求自动审核开关", "true"), //需求审核开关
    ;

    private int    type;        // 0:开关，1：数字，2：文本
    private String name;
    private String defaultValue;
    private String remark;

    DatadictionaryEnum(int type, String name, String defaultValue) {
        this.type = type;
        this.name = name;
        this.defaultValue = defaultValue;
    }

    DatadictionaryEnum(int type, String name, String defaultValue, String remark) {
        this.type = type;
        this.name = name;
        this.defaultValue = defaultValue;
        this.remark = remark;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public String getRemark() {
        return remark;
    }

}
