package com.jumore.jmbi.enums;

/** 
* 企业分值枚举
* @author: luochao
* @since: 2016年7月19日  下午7:55:26
* @history:
*/
public enum BaseScoreEnum {
    one(1l, "注册大数聚会员"),
    //
    two(2l, "企业自选-2级"),
    //
    three(3l, "合作伙伴-3级"),
    //
    four(4l, "战略合作伙伴-4级"),
    //
    five(5l, "上传合同模板"),
    //
    six(6l, "达成意向"),
    //
    seven(7l, "上传真实运输合同"),
    //
    eight(8l, "生成订单"),
    //
    nine(9l, "暂停交易"),
    //
    ten(10l, "邀请物流供应商注册大数聚物流"),
    //
    eleven(11l, "合同有效并生成订单");

    Long   baseScoreId; // 积分项id

    String item;       //描述

    BaseScoreEnum(Long baseScoreId, String item) {
        this.baseScoreId = baseScoreId;
        this.item = item;
    }

    public Long getBaseScoreId() {
        return baseScoreId;
    }

    public void setBaseScoreId(Long baseScoreId) {
        this.baseScoreId = baseScoreId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

}
