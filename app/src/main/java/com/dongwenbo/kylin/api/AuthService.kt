package com.dongwenbo.kylin.api

import retrofit2.http.GET
import retrofit2.http.Query

interface AuthService {

    /**
     * OAuth2认证
     *
     * @param clientId 应用id
     * @param redirectUri 重定向URI
     * @param login 特定账户授权，可空
     * @param scope 授权范围
     * @param state 随机串
     * @param allowSignup 是否允许注册，默认可以
     */
    @GET("login/oauth/authorize")
    fun authorize(
        @Query("client_id") clientId: String,
        @Query("redirect_uri") redirectUri: String,
        @Query("login") login: String? = null,
        @Query("scope") scope: String = "repo,gist,user,admin",
        @Query("state") state: String,
        @Query("allow_signup") allowSignup: String = "true"
    )
}

