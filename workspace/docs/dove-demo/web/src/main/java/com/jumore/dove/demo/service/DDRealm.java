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
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jumore.dove.demo.common.DDConst;
import com.jumore.dove.demo.entity.DDUser;
import com.jumore.dove.common.BusinessException;
import com.jumore.dove.common.log.LogHelper;
import com.jumore.dove.service.BaseService;

/**
 *
 */
public class DDRealm extends AuthorizingRealm {

    protected final LogHelper logHelper = LogHelper.getLogger(this.getClass());
    
    @Autowired
    BaseService baseService;

    /**
     * 为当前登录的Subject授予角色和权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
//        DDUser user = SessionHelper.getUser();
//        if(user.getType()==dove-demoConst.UserType.Admin){
//            simpleAuthorInfo.addRole("admin");
//        }
        return simpleAuthorInfo;
    }

    /**
     * 验证当前登录的Subject
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        logHelper.getBuilder().debug("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        DDUser vo = new DDUser();
        //vo.setAccount(token.getUsername());
        //vo.setPassword(String.valueOf(token.getPassword()));
        DDUser userPo = (DDUser) baseService.getByExample(vo);
        if (userPo == null) {
            throw new BusinessException("账号密码不正确");
        }
        SecurityUtils.getSubject().getSession().setAttribute(DDConst.Session.Session_User_Key, userPo);
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userPo, token.getPassword(), this.getName());
        //AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userPo, userPo.getPassword(), this.getName());
        return authcInfo;
    }
}
