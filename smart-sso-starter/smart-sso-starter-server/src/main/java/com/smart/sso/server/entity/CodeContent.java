package com.smart.sso.server.entity;

/**
 * 授权存储信息
 * 
 * @author Joe
 */
public class CodeContent {

	private String tgt;
	private boolean sendLogoutRequest;
	private String redirectUri;

	public CodeContent() {
	}

	public CodeContent(String tgt, boolean sendLogoutRequest, String redirectUri) {
		this.tgt = tgt;
		this.sendLogoutRequest = sendLogoutRequest;
		this.redirectUri = redirectUri;
	}

	public String getTgt() {
		return tgt;
	}

	public void setTgt(String tgt) {
		this.tgt = tgt;
	}

	public boolean isSendLogoutRequest() {
		return sendLogoutRequest;
	}

	public void setSendLogoutRequest(boolean sendLogoutRequest) {
		this.sendLogoutRequest = sendLogoutRequest;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
}