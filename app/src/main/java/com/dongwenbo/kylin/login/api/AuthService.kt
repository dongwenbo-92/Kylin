package com.dongwenbo.kylin.login.api

interface AuthService {
    companion object {
        const val BASE_URL = "https://github.com/"
        const val AUTH_PATH = "login/oauth/authorize"
    }
}

