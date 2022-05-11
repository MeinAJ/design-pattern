package com.geega.cloud.utils.regex;

import java.util.regex.Matcher;
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
    private static final String ID_CARD_REGEX = "[1-9]+[\\d]{16}[Xx\\d]";

    /**
     * 手机号正则表达式
     * <支持格式>
     * 15102336807
     * 10000000000
     * </支持格式>
     */
    private static final String MOBILE_REGEX = "1[\\d]{10}";

    /**
     * 固定电话正则表达式
     * <支持格式>
     * 88888888
     * 12345678
     * </支持格式>
     */
    private static final String PHONE_REGEX = "[1-9][\\d]{7}";

    /**
     * 邮政编码正则表达式
     * <支持格式>
     * 888888
     * 123456
     * </支持格式>
     */
    private static final String POST_CODE_REGEX = "[1-9][\\d]{5}";

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
    private static final String IPV4_REGEX = "";

    public static void main(String[] args) {
        //验证邮箱
        boolean isOk = check("xxx@xxx.xxx", EMAIL_REGEX);
        System.out.println(isOk);
        isOk = check("xxx@xxx.xxx.xxx", EMAIL_REGEX);
        System.out.println(isOk);
        isOk = check("xxx.xxx@xxx.xxx.xxx", EMAIL_REGEX);
        System.out.println(isOk);
        //验证身份证
        //验证手机号
        //验证IP地址
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
        return check(idCard, ID_CARD_REGEX);
    }


    /**
     * 验证手机号码
     *
     * @param mobile 移动电话号码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkMobile(String mobile) {
        return check(mobile, MOBILE_REGEX);
    }

    /**
     * 验证固定电话号码
     *
     * @param phone 固定电话
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPhone(String phone) {
        return check(phone, PHONE_REGEX);
    }

    /**
     * 验证整数（正整数和负整数）
     *
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDigit(String digit) {
        String regex = "\\-?[1-9]\\d+";
        return Pattern.matches(regex, digit);
    }

    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     *
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkDecimals(String decimals) {
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?";
        return Pattern.matches(regex, decimals);
    }

    /**
     * 验证空白字符
     *
     * @param blankSpace 空白字符，包括：空格、\t、\n、\r、\f、\x0B
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBlankSpace(String blankSpace) {
        String regex = "\\s+";
        return Pattern.matches(regex, blankSpace);
    }

    /**
     * 验证中文
     *
     * @param chinese 中文字符
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkChinese(String chinese) {
        String regex = "^[\u4E00-\u9FA5]+$";
        return Pattern.matches(regex, chinese);
    }

    /**
     * 验证日期（年月日）
     *
     * @param birthday 日期，格式：1992-09-03，或1992.09.03
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkBirthday(String birthday) {
        String regex = "[1-9]{4}([-./])\\d{1,2}\\1\\d{1,2}";
        return Pattern.matches(regex, birthday);
    }

    /**
     * 验证URL地址
     *
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

    /**
     * <pre>
     * 获取网址 URL 的一级域名
     * http://detail.tmall.com/item.htm?spm=a230r.1.10.44.1xpDSH&id=15453106243&_u=f4ve1uq1092 ->> tmall.com
     * </pre>
     *
     * @param url
     * @return
     */
    public static String getDomain(String url) {
        Pattern p = Pattern.compile("(?<=http://|\\.)[^.]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        // 获取完整的域名
        // Pattern p=Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(url);
        matcher.find();
        return matcher.group();
    }

    /**
     * 匹配中国邮政编码
     *
     * @param postcode 邮政编码
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkPostcode(String postcode) {
        String regex = "[1-9]\\d{5}";
        return Pattern.matches(regex, postcode);
    }

    /**
     * 匹配IP地址(简单匹配，格式，如：192.168.1.1，127.0.0.1，没有匹配IP段的大小)
     *
     * @param ipAddress IPv4标准地址
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkIpAddress(String ipAddress) {
        String regex = "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))";
        return Pattern.matches(regex, ipAddress);
    }

    /**
     * 通用检查方法
     */
    public static boolean check(String checkString, String regex) {
        return Pattern.matches(checkString, regex);
    }

}