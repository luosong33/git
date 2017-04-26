package com.jumore.jmbi.enums;

/** 
* 账号状态
* @author: luochao
* @since: 2016年7月19日  下午7:55:26
* @history:
*/
public enum AccountStatusEnum {

    CHECK_PENDING(0, "待审核"), NORMAL(1, "正常"), BLOCK_UP(4, "停用");

    Integer code; // 状态编号
    String  name; //状态名称

    AccountStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Integer code) {
        for (AccountStatusEnum orderStatusEnum : AccountStatusEnum.values()) {
            if (orderStatusEnum.getCode().equals(code)) {
                return orderStatusEnum.getName();
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
