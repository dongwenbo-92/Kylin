package com.dongwenbo.kylin.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dongwenbo.kylin.MainActivity
import com.dongwenbo.kylin.databinding.ActivityLoginBinding
import com.dongwenbo.kylin.global.BASE_URL
import com.dongwenbo.kylin.login.api.AuthService
import com.dongwenbo.kylin.login.utilities.CLIENT_ID
import com.dongwenbo.kylin.login.utilities.REDIRECT_URI
import com.dongwenbo.kylin.login.utilities.SCOPE

const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var timeStamp: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        binding.loginBtn.setOnClickListener {
            timeStamp = System.currentTimeMillis().toString()
            authorizeFromBrowser(CLIENT_ID, REDIRECT_URI, SCOPE, timeStamp)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //校验随机码
        val state = intent?.data?.getQueryParameter("state")
        if (state != null && state == timeStamp){
            val code = intent.data?.getQueryParameter("code")
            Log.d(TAG, "onResume: $code")
            if (code != null) {
//                accessToken(code)
                navigationToMainActivity()
                finish()
            }
        }
    }

    fun accessToken(code:String){
        val authService = AuthService.create()
        val response = authService.accessToken(CLIENT_ID,"",code)
    }

    fun navigationToMainActivity() {
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    /**
     * 从浏览器授权

     * @param clientId 应用ID
     * @param redirectUri 重定向URI
     * @param scope 授权范围
     * @param state 随机码
     * @param allowSignup 是否允许注册，默认TRUE
     */
    private fun authorizeFromBrowser(
        clientId: String,
        redirectUri: String,
        scope: String,
        state: String = "123456",
        allowSignup: String = "true"
    ) {
        val link = BASE_URL + AuthService.AUTH_PATH
        val paras =
            "client_id=$clientId&redirect_uri=$redirectUri&scope=$scope&state=$state&allow_signup=$allowSignup"
        val uri: Uri =
            Uri.parse("$link?$paras")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}
