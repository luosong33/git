package org.jumao.bi.entites.trade.register.vo;

import org.jumao.bi.entites.charts.CommonBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kartty on 2017/5/8.
 */
public class LineMultiSeriesVo {

    private String name = "";
    private String[] data = new String[0];


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
}
