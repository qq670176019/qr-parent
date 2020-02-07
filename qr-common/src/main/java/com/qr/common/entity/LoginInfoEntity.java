package com.qr.common.entity;

/**
 * @author:Jascon
 * @date:2020-02-05 22:32
 */
public class LoginInfoEntity {
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public String toString() {
        return "LoginInfoEntity{" +
                "accessToken='" + accessToken + '\'' +
                '}';
    }
}
