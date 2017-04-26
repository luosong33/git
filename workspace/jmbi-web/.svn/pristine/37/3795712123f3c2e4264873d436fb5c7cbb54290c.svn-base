package com.jumore.jmbi.enums;

/** 
* 发布人员类型，0委托方，1管理员，2供应商
* @author: panweiqiang
* @since: 2016年7月21日  上午10:33:25
* @history:
*/
public enum CreateUserTypeEnum {

    // 委托方
    CONSIGNOR("0", "委托方"),
    // 管理员
    MANAGER("1", "管理员"),
    // 供应商
    SUPPLIER("2", "供应商");

    /**
     * 0：未删除，1：已删除
     */
    String code;
    String name;

    CreateUserTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getName(String code) {
        for (CreateUserTypeEnum createUserTypeEnum : CreateUserTypeEnum.values()) {
            if (createUserTypeEnum.getCode().equals(code)) {
                return createUserTypeEnum.getName();
            }
        }
        return null;
    }
}
