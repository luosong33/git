package org.jumao.bi.dao.jyt.impl;

/**
 * @author chen qian
 */

import org.jumao.bi.dao.jyt.JytRegIncrAndAuthDao;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository(JytRegIncrAndAuthDaoImpl.Jyt_Reg_Incr_And_Auth_Dao)
public class JytRegIncrAndAuthDaoImpl extends GeneralBasicDao implements JytRegIncrAndAuthDao {

    public static final String Jyt_Reg_Incr_And_Auth_Dao = "jytRegIncrAndAuthDao";


    @Override
    public List<GroupByVo> getRegAreaDist(String startDate, String endDate, int topLimit) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr(
                "SELECT t.", Key.Type, ", a.area_name as ", Key.TYPE_NAME, ", t.", Key.TOTAL,
                " FROM (SELECT count(c.id) as ", Key.TOTAL, ", c.area as ", Key.Type,
                " FROM ", Table.Lg_Company, " c WHERE code_status = '2' and c.delete_flag = '0' and c.area is not null and",
                " (c.create_time between '", startDate, "' and '", endDate, "')",
                " GROUP BY c.area",
                topLimit > 0 ? StringUtils.joinStr(" order by ", Key.TOTAL, " desc limit ", topLimit) : "",
                " ) t LEFT JOIN ", Table.Lg_Base_Area, " a ON t.", Key.Type, " = a.area_code");

        List<GroupByVo> list = getVoList(sql, GroupByVo.class);
        Collections.sort(list, groupByVoComp);
        return list;
    }



}
