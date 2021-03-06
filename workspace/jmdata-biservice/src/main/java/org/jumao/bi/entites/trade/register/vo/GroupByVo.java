package org.jumao.bi.entites.trade.register.vo;

/**
 * 按数据源或类型 group，并取一个数量
 */
public class GroupByVo {

    private Integer type;
    private String typeName;//按具体业务可选则性的查询
    private Long total;
    private Double valD;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Double getValD() {
        return valD;
    }

    public void setValD(Double valD) {
        this.valD = valD;
    }
}
