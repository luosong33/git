package com.jumore.dove.demo.common.util;

import org.apache.shiro.SecurityUtils;

import com.jumore.dove.demo.common.DDConst;
import com.jumore.dove.demo.common.model.Configuration;
import com.jumore.dove.demo.entity.DDUser;

public class SessionHelper {

    private static Configuration cfg;
    
    public static DDUser getUser() {
        DDUser user = (DDUser) SecurityUtils.getSubject().getSession().getAttribute(DDConst.Session.Session_User_Key);
        return user;
    }
}
