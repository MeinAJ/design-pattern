package com.geega.cloud.common.base.response.error;

/**
 * @author Jun.An3
 * @date 2021/04/30
 */
public enum BizErrorEnum implements IBizError {
    /***/
    SUCCESS("0000", "success"),
    //1000以下为系统级错误
    SYSTEM_ERROR("0001", "system error"),
    NOT_ERROR_MESSAGE("0002", ""),
    NOT_LOGIN_ERROR("0010", "未登录"),
    NO_AUTH_ERROR("403", "无访问权限"),
    NO_SAVE_PERMISSION("0101", "操作失败，无保存权限"),
    NO_DELETE_PERMISSION("0102", "操作失败，无删除权限"),
    NO_QUERY_PERMISSION("0103", "操作失败，无查看权限"),
    NO_REQUEST_HEADER("0104", "请求未添加请求头"),
    OPERATE_NOT_ALLOW("500", "当前应用已启用，不允许操作"),
    DATA_NOT_EXIST_ERROR("500", "数据不存在"),
    TENANT_ERROR("500", "租户id错误"),
    NO_DATA_AUTH_ERROR("0201", "当前用户,无数据权限"),
    RPC_CALL_ERROR("0301", "远程调用失败"),

    // 数据操作错误定义
    BODY_NOT_MATCH("400", "请求的数据格式不符!"),
    SIGNATURE_NOT_MATCH("401", "请求的数字签名不匹配!"),
    NOT_FOUND("404", "未找到该资源!"),
    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),
    SERVER_BUSY("503", "服务器正忙，请稍后再试!"),
    FAIL("-1", "失败！"),

    /**
     * 迁移汇总
     */
    OBJECT_EMPTY("1005", "对象不能为空！"),
    REMOTE_CALL_FAIL("1006", "远程调用失败！"),
    UNKNOWN_FORM_FORMAT("1007", "未知的表单格式"),
    FILE_CHUNK_NOT_COMPLETE("1008", "分片文件未上传完成"),
    FILE_UPLOAD_TASK_NOT_EXISTS("1009", "文件上传已提交或不存在"),
    NO_SUCH_IMPLEMENTATION_BEAN("1010", "未找到对应类"),

    GET_GUC_USER_ERROR("2000", "查询用户详情失败"),
    GET_USER_ACCOUNT_ERROR("2000", "通过用户工号查询集团用户详情失败，请检查GUC用户工号%s"),
    GET_SAP_USER_ORG_ERROR("2001", "查询人员组织信息失败!"),
    FILE_UPLOAD_ERROR("2002", "文件上传失败"),
    GET_WEAVER_USER_ERROR("2003", "查询OA用户失败"),

    REGISTER_OA_ERROR("3001", "OA系统注册失败"),
    GET_OA_TOKEN_ERROR("3002", "获取OA系统TOKEN失败"),

    /**
     * ONES相关异常枚举
     */
    ONES_GET_USER_EMAIL_INFO_ERROR("4001", "获取邮箱uuid信息失败"),
    ONES_GET_USER_INFO_ERROR("4002", "获取邮箱uuid信息失败"),

    /**
     * MQ
     */
    MQ_SEND_ERROR_ERROR("5000", "发送消息失败"),
    ;

    BizErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final String code;

    private final String msg;

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
