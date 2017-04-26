package com.jumore.jmbi.enums;

public enum SopTypeEnum {
    SOP("0", "SOP模板"), PRICE("1", "价格模板");

    private String code;

    private String name;

    SopTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(String code) {
        for (SopTypeEnum sopTypeEnum : SopTypeEnum.values()) {
            if (sopTypeEnum.getCode().equals(code)) {
                return sopTypeEnum.getName();
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
