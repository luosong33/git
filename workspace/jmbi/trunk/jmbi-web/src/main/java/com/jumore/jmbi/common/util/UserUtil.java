//package com.jumore.jmbi.common.util;
//
//import org.apache.shiro.SecurityUtils;
//
//import com.jumore.logisticsOperation.entity.permission.Account;
//
///** 
//* 用户信息
//* @author: panweiqiang
//* @since: 2016年7月19日  上午9:20:19
//* @history:
//*/
//public class UserUtil {
//
//    /** 
//     * 功能说明：从Session中获取用户id
//     * 
//     * @return 
//     * Long
//     */
//    public static Long getLoginId() {
//        Account account = getLoginUser();
//        if (account == null) {
//            return null;
//        }
//        return account.getId();
//    }
//
//    /** 
//     * 功能说明：从Session中获取用户id
//     * 
//     * @return 
//     * Long
//     */
//    public static Account getLoginUser() {
//        try {
//            Object account = SecurityUtils.getSubject().getSession().getAttribute(Consts.Session_User_Key);
//            if (account == null) {
//                return null;
//            }
//            return (Account) account;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//}
