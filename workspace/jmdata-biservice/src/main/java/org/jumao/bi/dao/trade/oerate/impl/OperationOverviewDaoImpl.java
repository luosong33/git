package org.jumao.bi.dao.trade.oerate.impl;

import org.jumao.bi.dao.trade.oerate.IOperationOverviewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/28.
 */
@Repository("operationOverviewDaoImpl")
public class OperationOverviewDaoImpl implements IOperationOverviewDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getTopTotal(String SQL) {
        return jdbcTemplate.queryForList(SQL);
    }
}
