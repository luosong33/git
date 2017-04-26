package com.jumore.jmbi.model.vo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

/** 
* 搜索引擎搜索基本类
* @author: panweiqiang
* @since: 2016年8月1日  下午3:50:39
* @history:
*/
public class BaseIndexingVo {

    /**
     * 全局的搜索的内容
     */
    private String global_ana_ch = "";

    public String getGlobal_ana_ch() {
        return global_ana_ch;
    }

    public void setGlobal_ana_ch(String global_ana_ch) {
        this.global_ana_ch = global_ana_ch;
    }

    /** 
     * 功能说明：添加内容到全局搜索内容中
     * <p>应用场景：</p>
     * <p>1.数据库中存的是code，但全局搜索时，需要根据code对应的名称搜到结果，这时可以把名称添加到全局搜索内容中</p>
     * <p>2.像地区之类的内容添加进来后可以支持根据分词器进行模糊匹配，否则只能精确匹配</p>
     * 
     * @param value 
     * void
     */
    public void addGlobalValue(String value) {
        if (StringUtils.isNotBlank(value)) {
            this.global_ana_ch += "," + value;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
