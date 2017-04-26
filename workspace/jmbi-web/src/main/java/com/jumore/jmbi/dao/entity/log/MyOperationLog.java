/**
 * 
 */
package com.jumore.jmbi.dao.entity.log;

import com.jumore.dove.common.audit.OperationLog;
import com.jumore.dove.plugin.TableNameProvider;

/** 
* 重写日志表名
* @author: luochao
* @since: 2016年8月31日  上午11:15:05
* @history:
*/
public class MyOperationLog implements TableNameProvider {

    /**表名*/
    private static final String LOG_TABLE_NAME = "bi_operation_log";

    public String getName(Class<?> clazz) {
        if (clazz == OperationLog.class) {
            return LOG_TABLE_NAME;
        }
        return null;
    }

}
