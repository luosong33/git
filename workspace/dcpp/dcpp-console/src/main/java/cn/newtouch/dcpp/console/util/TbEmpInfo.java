package cn.newtouch.dcpp.console.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by luosong on 2017/4/24.
 */
public class TbEmpInfo {

    private Integer id;
    private String name;
    private Integer age;
    private String tel;
    private ArrayList<HashMap<String, String>> aData;

    public ArrayList<HashMap<String, String>> getaData() {
        return aData;
    }

    public void setaData(ArrayList<HashMap<String, String>> aData) {
        this.aData = aData;
    }


    public static List<String> getColumn(){
        ArrayList<String> list = new ArrayList<>();
        list.add("id");
        list.add("name");
        list.add("age");
        list.add("tel");
        return list;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
