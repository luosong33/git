package com.jumore.jmbi.enums;

/** 
 * 一级产品类目
 *
 * @author: fanyin
 * @since: 2016年8月31日  上午9:20:32
 * @history:
 */
public enum BaseGoodsCategoryEnum {

    NONFEROUS("200", "有色"),

    CHEMICAL("300", "化工"),

    MINERAL("400", "矿产"),

    STEEL("500", "钢材"),

    ENERGE("600", "能源"),

    AGRICULTURE("700", "农产品"),

    OTHER("-1", "其他"),

    ;

    String code;
    String name;

    BaseGoodsCategoryEnum(String code, String name) {
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

    public static boolean isBaseCategory(String code) {
        for (BaseGoodsCategoryEnum categoryEnum : BaseGoodsCategoryEnum.values()) {
            if (categoryEnum.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
