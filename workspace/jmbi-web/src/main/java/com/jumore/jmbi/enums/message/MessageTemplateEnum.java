package com.jumore.jmbi.enums.message;

/** 
* 消息模板
* @author: luochao
* @since: 2016年7月19日  下午7:55:26
* @history:
*/
public enum MessageTemplateEnum {

    INVALID_ORDERWISH_FROM("作废意向单", "2", "[{companyName}]，您的意向单[{orderNo}]经审核，发现有违规行为操作，系统将作废该意向单，有任何问题，请联系客服解决，祝商祺！"),
    /**作废意向单-乙方*/
    INVALID_ORDERWISH_TO("作废意向单", "2", "[{companyName}]，您的意向单[{orderNo}]经审核，发现对方有违规行为操作，系统将作废该意向单，有任何问题，请联系客服解决，祝商祺！"),
    /**上传合同*/
    UPLOAD_CONTRACT("上传合同", "2", "[{companyName}]，已经上传了运输合同，请进入会员中心-意向管理中查看，祝商祺！"),
    /**生成订单*/
    GENERATE_ORDER("生成订单", "2", "[{buyerCompanyName}]和[{sellerCompanyName}]已经生成订单了，订单号：{orderNo}，我们将跟踪您的订单状态，有任何需要，请联系客服，祝商祺！"),

    /**确认合同有效并生成订单*/
    ORDER_INVALID_AND_GENERATE("合同有效并生成订单", "2", "[{buyerCompanyName}]和[{sellerCompanyName}]合同真实有效，并达成订单，订单号：{orderNo}，我们将跟踪您的订单状态，有任何需要，请联系客服，祝商祺！"),

    /**需求管理-报价-委托方*/
    NEED_OFFER_CONSIGNOR("需求管理-报价", "2", "[{buyerCompanyName}]已经对您的物流需求[{needsNo}]进行报价，您可以在您的会员中进行查看，祝商祺！"),
    /**需求管理-报价-供应商*/
    NEED_OFFER_SUPPLIER("需求管理-报价", "2", "您已对物流需求[{needsNo}]进行报价，您可以在您的会员中进行查看，祝商祺！"),
    /**需求管理-需求转竞拍*/
    NEED_BID("需求管理-需求转竞拍", "2", "[{companyName}]您的需求已经开始竞拍了，这样可以为您带来更多的选择，有问题请联系客服，祝商祺！"),
    /**需求管理-需求审批通过*/
    NEED_APPROVED("需求管理-需求审批通过", "2", "[{companyName}]您的[{needsNo}]经平台审核通过，您可以在会员中心进行查看，管理，并采纳供应商报价，祝商祺！"),
    /**需求管理-需求审批不通过*/
    NEED_REJECTED("需求管理-需求审批不通过", "2", "[{companyName}]您的[{needsNo}]经平台审核未通过，您可以在会员中心进行查看，管理祝商祺！"),

    /**供应商管理-供应商审核*/
    APPROVAL_PASSED("供应商管理-供应商审核", "2", "[{companyName}]已经审核通过，您可以进行相关业务操作了，祝商祺！"),

    /**供应商管理-供应商审核*/
    APPROVAL_NOPASSED("供应商管理-供应商审核", "2", "[{companyName}]未审核通过，如有问题，请联系客服，祝商祺！"),

    /**会员资质-暂停交易*/
    SUSPEND_TRADE("会员资质-暂停交易", "2", "[{companyName}]，经聚运通系统审核，发现您近期有很多违规行为操作，系统将暂停一周您的交易，您之前的业务不会有影响，有任何问题，请联系客服，祝商祺！"),

    /**会员资质-KPI*/
    MEMBER_KPI("会员资质-KPI", "2", "[{companyName}]，您这期的KPI：[{kpi}]，祝商祺！"),

    /**订单完成*/
    ORDER_FINISH("订单完成", "2", "[{companyName}]您的订单已经顺利完成，祝商祺！！"),

    /**手工减分*/
    REDUCE_SCORE("减分", "2", "[{companyName}]，由于您的[{reason}]，您的积分被扣除{score}分!"),

    /**手工加分*/
    PLUS_SCORE("加分", "2", "[{companyName}]，由于您的[{reason}]，您的积分+{score}分!"),

    /**企业升级加分*/
    COMPANY_UP_LEVEL("企业升级加分", "2", "[{companyName}]升级成[{reason}]，+{score}分！"),

    /**交易加分*/
    ORDER_PLUS_SCORE("交易加分", "2", "[{companyName}]，{reason}[{businessId}]，+{score}分！"),

    /**帐户加分*/
    ACCOUNT_PLUS_SCORE("帐户加分", "2", "[{companyName}]，[{reason}],+{score}分！"),

    /**帐户减分*/
    ACCOUNT_SUBTRACT_SCORE("帐户减分", "2", "[{companyName}]，[{reason}],{score}分！"),

    /** 需求推送到供应商需求管理中 */
    NEED_PUSH("需求推送到供应商需求管理中", "2", "[{companyName}]有一条物流需求请求[{needsNo}]，您可以在您的会员中心进行报价，祝商祺！"),

    /** 运力通过审核*/
    AUDIT_PASS("运力通过审核", "2", "[{companyName}]您的[{lineNo}]经平台审核通过，您可以在会员中心进行查看，管理，对需求进行报价，祝商祺！"),
   
    /** 运力不通过审核*/
    AUDIT_NOT_PASS("运力不通过审核", "2", "[{companyName}]您的[{lineNo}]经平台审核未通过，您可以在会员中心进行查看，管理，祝商祺！");

    String operation; //操作

    String code;     // 消息类型，来源 MessageTemplateEnum

    String content;  //消息内容

    MessageTemplateEnum(String operation, String code, String content) {
        this.operation = operation;
        this.code = code;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

}
