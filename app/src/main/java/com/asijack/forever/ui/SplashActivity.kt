package com.asijack.forever.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.annotation.RequiresApi
import com.asijack.common.mvvm.BindingActivity
import com.asijack.common.ui.activity.BaseActivity
import com.asijack.forever.R
import com.asijack.forever.databinding.AppActivitySplashBinding

/**
 * @author xinzhengjie
 * @date 2021/3/20 15:50
 */
class SplashActivity : BindingActivity<AppActivitySplashBinding>() {

    companion object {
        private const val TIME_COUNTDOWN: Long = 3 * 1000
    }

    /**
     * 隐藏底部虚拟键并全屏
     */
    private fun hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT in 12..18) {
            val view = this.window.decorView
            view.systemUiVisibility = View.GONE
        } else if (Build.VERSION.SDK_INT >= 19) {
            val decorView = window.decorView
            val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)

            decorView.systemUiVisibility = uiOptions
        }
    }

    override fun onCreateBinding(savedInstanceState: Bundle?): AppActivitySplashBinding {
       return AppActivitySplashBinding.inflate(layoutInflater)
    }

    override fun AppActivitySplashBinding.onBindingCreated(savedInstanceState: Bundle?) {
        //开启倒计时
        object : CountDownTimer(1000, TIME_COUNTDOWN) {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun onFinish() {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }

            override fun onTick(p0: Long) {
            }

        }.start()

        hideBottomUIMenu()
    }
}
