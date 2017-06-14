package org.jumao.bi.dao.jyt;

import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;

import java.util.List;

/**
 * @author chen qian
 */
public interface JytOverviewDao {

    String getNumbersBy(String itemId);

    List<TimeTotalVo> getLineChartByItemId(String itemId, String startDate, String endDate, boolean accurateToHour) throws Exception;

    List<GroupByVo> getTransportLineBy(String startDate, String endDate) throws Exception;

    List<GroupByVo> getRequireTop5BarChartBy(String startDate, String endDate, int top_ele10) throws Exception;


}
