package org.jumao.bi.dao.jyt.impl;

import org.apache.log4j.Logger;
import org.jumao.bi.dao.jyt.JytOverviewDao;
import org.jumao.bi.dao.trade.impl.GeneralBasicDao;
import org.jumao.bi.entites.trade.register.vo.GroupByVo;
import org.jumao.bi.entites.trade.register.vo.TimeTotalVo;
import org.jumao.bi.utis.GeneralUtils;
import org.jumao.bi.utis.StringUtils;
import org.jumao.bi.utis.Verifier;
import org.jumao.bi.utis.constants.Key;
import org.jumao.bi.utis.constants.Table;
import org.jumao.bi.utis.enums.JytEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chen qian
 */
@Repository(JytOverviewDaoImpl.Jyt_Overview_Dao)
public class JytOverviewDaoImpl extends GeneralBasicDao implements JytOverviewDao {

    private Logger logger = Logger.getLogger(JytOverviewDaoImpl.class);

    public static final String Jyt_Overview_Dao = "jytOverviewDao";

    @Autowired
    protected JdbcTemplate jdbcTemplate;


    @Override
    public String getNumbersBy(String itemIdStr) {
        int itemId = Integer.parseInt(itemIdStr);
        String sql = null;
        JytEnum jytEnum = JytEnum.getByType(itemId);

        switch (jytEnum) {
            case GYSQYS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Company, " where identity = '1' and code_status = '2';");
                break;
            case WTFQYS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Company, " WHERE identity = '0' AND code_status = '2' AND is_virtual_company = 0;");
                break;
            case WTFGRS:
                sql = StringUtils.joinStr("select count(u.id) from ", Table.Lg_Base_Company_User,
                        " u, ", Table.Lg_Company, " c where u.company_id=c.id and c.identity='0' and c.code_status = '2' and c.is_virtual_company = 0;");
                break;
            case CKS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Warehouse, " where check_status=1;");
                break;
            case XLS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Line, " where check_status='1';");
                break;
            case HZ:
                sql = StringUtils.joinStr("select sum(total_price) from ", Table.Lg_Order
                        , " where delete_flag='0' and active_flag='0';");
                break;
            case DDJE:
                sql = StringUtils.joinStr("select sum(pay_money) from ",
                        Table.Lg_Order, " where  delete_flag='0' and active_flag='0';");
                break;
            case DDS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Order, " where delete_flag='0' and active_flag='0';");
                break;
            case SJS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Driver, " where check_status='2'");
                break;
            case CLS:
                sql = StringUtils.joinStr("select count(*) from ",
                        Table.Lg_Vehicle, " where check_status='2'");
                break;
            default:
                logger.error(StringUtils.joinStr(
                        GeneralUtils.getCurrMethod(), " get a undefined itemId:", itemIdStr
                ));
                break;
        }

        if (!Verifier.isEffectiveStr(sql)) {
            return "0";
        }
        return jdbcTemplate.queryForObject(sql, Long.class) + jytEnum.getUnit();
    }


    @Override
    public List<TimeTotalVo> getLineChartByItemId(String itemIdStr, String startDate, String endDate, boolean accurateToHour) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String tmGrpLen = getCrtTmGroupLength(accurateToHour);
        int itemId = Integer.parseInt(itemIdStr);
        String sql = null;
        JytEnum jytEnum = JytEnum.getByType(itemId);

        switch (jytEnum) {
            case GYSQYS:
                sql = getSqlForTimeAndCount(tmGrpLen, startDate, endDate, Table.Lg_Company,
                        "is_virtual_company = 1 AND code_status = '2' and identity = '1'");
                break;
            case WTFQYS:
                sql = getSqlForTimeAndCount(tmGrpLen, startDate, endDate, Table.Lg_Company,
                        "is_virtual_company = 1 AND code_status = '2' and identity = '0'");
                break;
            case WTFGRS:
                sql = getSqlForTimeAndCount(tmGrpLen, startDate, endDate, Table.Lg_Company,
                        "is_virtual_company = 0 AND code_status = '2' and identity = '0'");
                break;
            case CKS:
                sql = getSqlForTimeAndCount(tmGrpLen, startDate, endDate, Table.Lg_Warehouse,
                    "check_status = 1");
                break;
            case XLS:
                sql = getSqlForTimeAndCount(tmGrpLen, startDate, endDate, Table.Lg_Line,
                    "check_status = '1'");
                break;
            case HZ:
                sql = getSqlForHz(tmGrpLen, startDate, endDate);
                break;
            //case DDJE: sql = ""; break; //未提供sql
            case DDS:
                sql = getSqlForTimeAndCount(tmGrpLen, startDate, endDate, Table.Lg_Order,
                        "delete_flag = '0' and active_flag = '0'");
                break;
            case SJS:
                sql = getSqlForTimeAndCount(tmGrpLen, startDate, endDate, Table.Lg_Driver,
                    "check_status = '2'");
                break;
            case CLS:
                sql = getSqlForTimeAndCount(tmGrpLen, startDate, endDate, Table.Lg_Vehicle,
                        "check_status = '2'");
                break;
            default:
                logger.error(StringUtils.joinStr(
                        GeneralUtils.getCurrMethod(), " get a undefined itemId:", itemIdStr
                ));
                break;
        }

        if (!Verifier.isEffectiveStr(sql)) {
            return new ArrayList<TimeTotalVo>();
        }
        return getVoList(sql, TimeTotalVo.class);
    }

    private String getSqlForTimeAndCount(String tmGrpLen, String startDate, String endDate,
                                         String table, String condi) {
        return StringUtils.joinStr(
                "select ", tmGrpLen, " as ", Key.CREATE_TIME, ", count(*) as ", Key.TOTAL,
                " from ", table, " where ", condi,
                " and (create_time between '", startDate, "' and '", endDate, "')",
                " GROUP BY ", Key.CREATE_TIME);
    }

    private String getSqlForHz(String tmGrpLen, String startDate, String endDate) {
        return StringUtils.joinStr(
                "select ", tmGrpLen, " as ", Key.CREATE_TIME, ", sum(pay_money) as ", Key.TOTAL,
                " from ", Table.Lg_Order, " where",
                " delete_flag = '0' and active_flag = '0'",
                " and (create_time between '", startDate, "' and '", endDate, "')",
                " GROUP BY ", Key.CREATE_TIME);
    }


    @Override
    public List<GroupByVo> getTransportLineBy(String startDate, String endDate) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr(
                "select line_type as ", Key.Type,
                ", count(*) as ", Key.TOTAL, " from ", Table.Lg_Line,
                " where check_status = '1' and",
                " (create_time between '", startDate, "' and '", endDate, "') group by line_type");

        return getVoList(sql, GroupByVo.class);
    }


    @Override
    public List<GroupByVo> getRequireTop5BarChartBy(String startDate, String endDate, int topLimit) throws Exception {
        startDate = dealStartDate(startDate);
        endDate = dealEndDate(endDate);

        String sql = StringUtils.joinStr(
                "select company_id as ", Key.Type, ", company_name as ", Key.TYPE_NAME,
                ", count(*) as ", Key.TOTAL, " from ", Table.Lg_Needs,
                " where check_status = '1' and",
                " (create_time between '", startDate, "' and '", endDate, ")",
                " group by company_id, company_name order by ", Key.TOTAL, " desc limit ", topLimit);

        return getVoList(sql, GroupByVo.class);
    }


}
