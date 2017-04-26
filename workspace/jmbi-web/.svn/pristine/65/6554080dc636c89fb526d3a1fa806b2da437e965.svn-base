package com.jumore.jmbi.enums;

public enum PunishStatusEnum {
    SUSPEND("1", "暂停交易"), BANNED("2", "封号"), UNLOCK("3", "封号");

    String code; // 状态编号
    String name; //状态名称

    PunishStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getLevel(String level) {
        for (PunishStatusEnum punishStatusEnum : PunishStatusEnum.values()) {
            if (punishStatusEnum.getCode().equals(level)) {
                return punishStatusEnum.getName();
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
