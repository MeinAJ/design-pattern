package com.geega.cloud.utils.regex;

import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 *
 * @author Jun.An3
 * @date 2022/05/11
 */
public class RegexUtils {

    private RegexUtils() {
    }

    /**
     * 邮箱正则表达式
     * <支持格式>
     * xxx.xxx@xxx.xxx
     * xxx.xxx@xxx.xxx.xxx
     * xxx@xxx.xxx
     * xxx@xxx.xxx.xxx
     * </支持格式>
     */
    private static final String EMAIL_REGEX = "[\\da-zA-Z]+(\\.[a-zA-Z]+)*@[\\da-zA-Z]+(\\.[a-zA-Z]+)+";

    /**
     * 身份真正则表达式
     * <支持格式>
     * 500226911407122337
     * 50022691140712233X
     * 50022691140712233x
     * </支持格式>
     */
    private static final String ID_CARD_REGEX = "[1-9]+\\d{16}[Xx\\d]";

    /**
     * 手机号正则表达式
     * <支持格式>
     * 15102336807
     * 10000000000
     * </支持格式>
     */
    private static final String MOBILE_REGEX = "1\\d{10}";

    /**
     * 固定电话正则表达式
     * <支持格式>
     * 88888888
     * 12345678
     * </支持格式>
     */
    private static final String PHONE_REGEX = "[1-9]\\d{7}";

    /**
     * 邮政编码正则表达式
     * <支持格式>
     * 888888
     * 123456
     * </支持格式>
     */
    private static final String POST_CODE_REGEX = "[1-9]\\d{5}";

    /**
     * IPV4地址正则表达式
     * <支持格式>
     * 127.0.0.1
     * 192.168.0.1
     * 192.168.0.0
     * 0.0.0.0
     * 1.1.1.1
     * </支持格式>
     */
    private static final String IPV4_REGEX = "(([1-9][\\d]{0,2})|(0))(\\.(([1-9][\\d]{0,2})|(0))){3}";

    public static void main(String[] args) {
        //验证邮箱
        System.out.println("验证邮箱");
        System.out.println(check(EMAIL_REGEX, "xxx.@xxx.xxx"));
        System.out.println(check(EMAIL_REGEX, "xxx@xxx.xxx"));
        System.out.println(check(EMAIL_REGEX, "xxx@xxx.xxx.xxx"));
        System.out.println(check(EMAIL_REGEX, "xxx.xxx@xxx.xxx.xxx"));
        System.out.println();
        //验证身份证
        //验证手机号
        //验证IP地址
        System.out.println("验证IP地址");
        System.out.println(check(IPV4_REGEX, "127.01.0.1"));
        System.out.println(check(IPV4_REGEX, "127.00.0.1"));
        System.out.println(check(IPV4_REGEX, "127.0.0.1"));
        System.out.println(check(IPV4_REGEX, "192.168.0.1"));
        System.out.println(check(IPV4_REGEX, "192.168.0.0"));
        System.out.println(check(IPV4_REGEX, "0.0.0.0"));
        System.out.println(check(IPV4_REGEX, "1.1.1.1"));
        //验证完整URL地址
        //验证邮政编码
    }

    /**
     * 验证邮箱
     *
     * @param email email地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkEmail(String email) {
        return check(EMAIL_REGEX, email);
    }

    /**
     * 验证身份证号码
     *
     * @param idCard 居民身份证号码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIdCard(String idCard) {
        return check(ID_CARD_REGEX, idCard);
    }


    /**
     * 验证手机号码
     *
     * @param mobile 移动电话号码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        return check(MOBILE_REGEX, mobile);
    }

    /**
     * 验证固定电话号码
     *
     * @param phone 固定电话
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPhone(String phone) {
        return check(PHONE_REGEX, phone);
    }

    /**
     * 验证ipv4地址
     *
     * @param ip ipv4地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIpV4(String ip) {
        return check(IPV4_REGEX, ip);
    }

    /**
     * 通用检查方法
     */
    public static boolean check(String regex, String checkString) {
        return Pattern.matches(regex, checkString);
    }

}