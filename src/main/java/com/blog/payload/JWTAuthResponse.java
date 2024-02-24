package com.blog.payload;

public class JWTAuthResponse {
    private String accessToken;
    private String tokenType="Bearer";
    public JWTAuthResponse(String accessToken){
        this.accessToken=accessToken;
    }
    public void setAccessToken(String accessToken){
        this.accessToken=accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setTokenType(String tokenType){
        this.tokenType=tokenType;
    }
    public String getTokenType(){
        return tokenType;
    }
}
