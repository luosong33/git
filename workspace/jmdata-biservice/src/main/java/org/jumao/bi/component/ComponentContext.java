package org.jumao.bi.component;

import java.util.List;
import java.util.Map;

import org.jumao.bi.entites.ParamBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
@Component
@Scope("prototype")
public class ComponentContext {
	private Class<?> returnType;//SerisData value属性返回值类型
	private Class<?> itemIdtype;//itemId 返回类型
	private List<String> transParam;//需要转换的参数
	private Map<String,?> untilMap;
	private ParamBean paramBean;//参数bean
	private String sql;//sql
	private Boolean dealPlatIdFlag = false;//是否需要平台id转换到平台名称
	public Class<?> getReturnType() {
		return returnType;
	}
	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}
	public List<String> getTransParam() {
		return transParam;
	}
	public void setTransParam(List<String> transParam) {
		this.transParam = transParam;
	}
	public Map<String, ?> getUntilMap() {
		return untilMap;
	}
	public void setUntilMap(Map<String, ?> untilMap) {
		this.untilMap = untilMap;
	}
	public ParamBean getParamBean() {
		return paramBean;
	}
	public void setParamBean(ParamBean paramBean) {
		this.paramBean = paramBean;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public Boolean getDealPlatIdFlag() {
		return dealPlatIdFlag;
	}
	public void setDealPlatIdFlag(Boolean dealPlatIdFlag) {
		this.dealPlatIdFlag = dealPlatIdFlag;
	}
	public Class<?> getItemIdtype() {
		return itemIdtype;
	}
	public void setItemIdtype(Class<?> itemIdtype) {
		this.itemIdtype = itemIdtype;
	}
	
}
