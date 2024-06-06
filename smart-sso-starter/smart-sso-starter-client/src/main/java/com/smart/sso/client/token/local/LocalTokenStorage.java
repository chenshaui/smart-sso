package com.smart.sso.client.token.local;

import com.smart.sso.base.entity.AccessToken;
import com.smart.sso.base.entity.ExpirationPolicy;
import com.smart.sso.client.ClientProperties;
import com.smart.sso.client.token.TokenStorage;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 借鉴CAS
 * 
 * @author Joe
 */
public final class LocalTokenStorage extends TokenStorage implements ExpirationPolicy {
    private final Map<String, StWrapper> stMap = new HashMap<>();
    private Map<String, String> accessTokenMap = new ConcurrentHashMap<>();

    public LocalTokenStorage(ClientProperties properties) {
        this.properties = properties;
    }

    @Override
    public void create(String st, AccessToken accessToken) {
        stMap.put(st, new StWrapper(accessToken, System.currentTimeMillis() + accessToken.getExpiresIn() * 1000
                , System.currentTimeMillis() + accessToken.getRefreshExpiresIn() * 1000));
        accessTokenMap.put(accessToken.getAccessToken(), st);
        logger.info("服务凭证生成成功, st:{}", st);
    }

    @Override
    public AccessToken getAndRefresh(String st) {
        StWrapper wrapper = stMap.get(st);
        if (wrapper == null) {
            return null;
        }
        // accessToken没过期直接返回
        if(!wrapper.isExpired()){
            return wrapper.accessToken;
        }
        // accessToken已过期，refreshToken没过期，使用refresh接口刷新
        if(!wrapper.isRefreshExpired()){
            AccessToken accessToken = refreshToken(wrapper.accessToken.getRefreshToken());
            if(accessToken != null){
                create(st, accessToken);
                return accessToken;
            }
        }
        return null;
    }

    @Override
    public void removeByServiceTicket(String st) {
        StWrapper wrapper = stMap.get(st);
        if (wrapper != null) {
            accessTokenMap.remove(wrapper.accessToken.getAccessToken());
        }
        stMap.remove(st);
        logger.debug("服务凭证删除成功, tgt:{}", st);
    }

    @Override
    public void removeByAccessToken(String accessToken) {
        String st = accessTokenMap.remove(accessToken);
        removeByServiceTicket(st);
    }

    @Override
    public void verifyExpired() {
        stMap.forEach((st, wrapper) -> {
            if (wrapper.isRefreshExpired()) {
                stMap.remove(st);
                accessTokenMap.remove(wrapper.accessToken.getAccessToken());
                logger.debug("服务凭证已失效, st:{}", st);
            }
        });
    }

    private class StWrapper {
        private AccessToken accessToken;
        private long expired;
        private long refreshExpired;

        public StWrapper(AccessToken accessToken, long expired, long refreshExpired) {
            super();
            this.accessToken = accessToken;
            this.expired = expired;
            this.refreshExpired = refreshExpired;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expired;
        }

        public boolean isRefreshExpired() {
            return System.currentTimeMillis() > refreshExpired;
        }
    }
}