package org.jumao.bi.entites.register;

/**
 * Created by kartty on 2017/4/28.
 */
public class AuthUAreaDist {

    private Long total;
    private Long country;
    private String areaname;
    private Long areacode;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public String getAreaname() {
        return areaname;
    }

    public void setAreaname(String areaName) {
        this.areaname = areaName;
    }

    public Long getAreacode() {
        return areacode;
    }

    public void setAreacode(Long areaCode) {
        this.areacode = areaCode;
    }
}
