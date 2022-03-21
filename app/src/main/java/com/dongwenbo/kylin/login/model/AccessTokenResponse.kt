package com.dongwenbo.kylin.login.model

import com.google.gson.annotations.SerializedName

/**
 * Accept: application/json
{
"access_token":"gho_16C7e42F292c6912E7710c838347Ae178B4a",
"scope":"repo,gist",
"token_type":"bearer"
}
 *
 */
data class AccessTokenResponse(
    @field:SerializedName("access_token") val accessToken: String,
    @field:SerializedName("scope") val scope: String,
    @field:SerializedName("token_type") val tokenType: String)
