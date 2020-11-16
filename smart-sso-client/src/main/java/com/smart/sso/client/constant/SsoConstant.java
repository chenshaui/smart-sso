package com.smart.sso.client.constant;

/**
 * @author Joe
 */
public class SsoConstant {
	
	/**
     * 服务端登录地址
     */
	public static final String LOGIN_URL = "{0}/login?appId={1}&redirectUri={2}";

	/**
	 * 服务端登出地址
	 */
	public static final String LOGOUT_URL = "{0}/logout?redirectUri={1}";
    
    /**
     * 服务端单点登出回调客户端登出参数名称
     */
    public static final String LOGOUT_PARAMETER_NAME = "logoutRequest";
    
    /**
     * 本地session中的AccessToken信息
     */
    public static final String SESSION_ACCESS_TOKEN = "_sessionAccessToken";
    
    /**
     * 模糊匹配后缀
     */
    public static final String URL_FUZZY_MATCH = "/*";
	
	/**
	 * 未登录或已过期
	 */
	public static final int NO_LOGIN = 2100;
}