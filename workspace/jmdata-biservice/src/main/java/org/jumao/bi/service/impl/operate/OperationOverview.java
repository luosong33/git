package org.jumao.bi.service.impl.operate;

import org.jumao.bi.utis.EpTrade;
import org.jumao.bi.utis.ImpalaJDBC;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26.
 */
public class OperationOverview {

    ImpalaJDBC impalaJDBC = new ImpalaJDBC();

    public void getPayTotal(){
        String SQL = "select count(*) from jmbi_ep_trade where status in (2,3,4)";
//        String SQL = "select  *  from  jmbi_ep_trade where  key = \'242\'";
        String total = impalaJDBC.getTotal(SQL);
    }

    public static void main(String[] args) {
        new OperationOverview().getPayTotal();
        System.out.println();
    }


}
