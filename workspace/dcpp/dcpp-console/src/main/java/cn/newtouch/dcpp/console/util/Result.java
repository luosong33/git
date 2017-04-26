package cn.newtouch.dcpp.console.util;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/20.
 */
public class Result {
    private String column = null;
    private ArrayList<String> value = new ArrayList<>();

    public Result(){

    }

    public String getColumn() {
        return column;
    }
    public void setColumn(String column) {
        this.column = column;
    }
    public ArrayList<String> getValue() {
        return value;
    }
    public void setValue(ArrayList<String> value) {
        this.value = value;
    }
}
