package com.jumore.jmbi.enums;

public enum UserMasterSlaveEnum {


    MASTER("1", "主账号"), SLAVE("2", "子账号");

    String code; // 状态编号
    String name; //状态名称

    UserMasterSlaveEnum(String level, String name) {
        this.code = level;
        this.name = name;
    }

    public static String getCode(String code) {
        for (UserMasterSlaveEnum userMasterSlaveEnum : UserMasterSlaveEnum.values()) {
            if (userMasterSlaveEnum.getCode().equals(code)) {
                return userMasterSlaveEnum.getName();
            }
        }
        return null;
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

    
}
