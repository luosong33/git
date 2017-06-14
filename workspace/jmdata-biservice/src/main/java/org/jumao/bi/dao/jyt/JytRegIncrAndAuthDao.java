package org.jumao.bi.dao.jyt;

import org.jumao.bi.entites.trade.register.vo.GroupByVo;

import java.util.List;

/**
 * @author chen qian
 */
public interface JytRegIncrAndAuthDao {


    List<GroupByVo> getRegAreaDist(String startDate, String endDate, int topLimit) throws Exception;

}
