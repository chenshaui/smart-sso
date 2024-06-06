package com.smart.sso.client.constant;

/**
 * @author Joe
 */
public class ClientConstant {

	/**
	 * 服务端登出地址
	 */
	public static final String LOGOUT_URL = "/logout";
    
    /**
     * 模糊匹配后缀
     */
    public static final String URL_FUZZY_MATCH = "/*";

	/**
	 * 存cookie中ST名称，和CAS概念保存一致
	 */
	public static final String COOKIE_SERVICE_TICKET = "ST";
	
	/**
	 * 未登录或已过期
	 */
	public static final int NO_LOGIN = 2100;
}