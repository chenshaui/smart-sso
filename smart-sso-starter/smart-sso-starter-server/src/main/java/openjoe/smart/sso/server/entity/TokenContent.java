package openjoe.smart.sso.server.entity;

import openjoe.smart.sso.base.entity.TokenUser;

/**
 * Token存储信息
 *
 * @author Joe
 */
public class TokenContent extends CodeContent {

    private String accessToken;
    private String refreshToken;
    private TokenUser tokenUser;
    private String clientId;

    public TokenContent() {
        super();
    }

    public TokenContent(String accessToken, String refreshToken, TokenUser tokenUser, String clientId, String tgt, String redirectUri) {
        super(tgt, redirectUri);
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenUser = tokenUser;
        this.clientId = clientId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public TokenUser getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(TokenUser tokenUser) {
        this.tokenUser = tokenUser;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}