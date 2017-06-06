package com.jumore.dove.demo.service;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jumore.dove.common.BusinessException;
import com.jumore.dove.common.log.LogHelper;
import com.jumore.dove.demo.common.DDConst;
import com.jumore.dove.demo.entity.DDUser;
import com.jumore.dove.service.BaseService;
import com.jumore.dove.web.auth.DoveCasRealm;

/**
 *
 */
public class DDCasRealm extends DoveCasRealm {

    protected final LogHelper logHelper = LogHelper.getLogger(this.getClass());
    
    @Autowired
    BaseService baseService;

    /**
     * 为当前登录的Subject授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        return simpleAuthorInfo;
    }

    /**
     * 验证当前登录的Subject
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
    	AuthenticationInfo authcInfo = super.doGetAuthenticationInfo(authcToken);
        return authcInfo;
    }

	@Override
	protected String getBizCode() {
		return null;
	}
}
