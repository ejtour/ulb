package com.ulb.code.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ulb.code.ui.main.MainActivity
import com.ulb.code.R
import com.ulb.code.config.UserConfig
import com.ulb.common.BaseAnimationListener
import com.ulb.common.base.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


/**
 * @doc
 */
class SplashActivity : BaseActivity() {

    private val alphaAnimation by lazy {
        AlphaAnimation(0.3F, 1.0F).apply {
            this.duration = 1000
            this.setAnimationListener(object : BaseAnimationListener() {
                override fun onAnimationEnd(animation: Animation?) {
                    Optional.ofNullable(animation).ifPresent { jumpToMain() }
                }
            })
        }
    }

    private val viewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        viewModel.loginByPsd("maidi", "tangyu", "123456a")
//
//        viewModel.loginRes.observe(this, androidx.lifecycle.Observer {
//            UserConfig.accessToken = it.access_token
//            splash_view.startAnimation(alphaAnimation)
//        })

        //test
        splash_view.startAnimation(alphaAnimation)
    }

    private fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

}