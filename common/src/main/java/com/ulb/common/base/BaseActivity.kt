/*
 * Copyright (c) 2020. vipyinzhiwei <vipyinzhiwei@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ulb.common.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ulb.common.R
import com.ulb.common.utils.LogUtils
import java.lang.ref.WeakReference

/**
 * 应用程序中所有Activity的基类。
 */
@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    /**
     * 判断当前Activity是否在前台。
     */
    protected var isActive: Boolean = false

    /**
     * 当前Activity的实例。
     */
    protected var activity: Activity? = null

    /** 当前Activity的弱引用，防止内存泄露  */
    private var activityWR: WeakReference<Activity>? = null

    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "BaseActivity-->onCreate()")
        activity = this
        activityWR = WeakReference(activity!!)
        //若要管理所有Activity 可在此处添加
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        LogUtils.d(TAG, "BaseActivity-->onSaveInstanceState()")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        LogUtils.d(TAG, "BaseActivity-->onRestoreInstanceState()")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        LogUtils.d(TAG, "BaseActivity-->onNewIntent()")
    }

    override fun onRestart() {
        super.onRestart()
        LogUtils.d(TAG, "BaseActivity-->onRestart()")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d(TAG, "BaseActivity-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG, "BaseActivity-->onResume()")
        isActive = true
        //统计页面等生命周期可自行添加
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d(TAG, "BaseActivity-->onPause()")
        isActive = false
        //统计页面等生命周期可自行添加
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d(TAG, "BaseActivity-->onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "BaseActivity-->onDestroy()")
        activity = null
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)
        setupViews()
    }

    override fun setContentView(layoutView: View) {
        super.setContentView(layoutView)
        setupViews()
    }

    protected open fun setupViews() {
        val navigateBefore = findViewById<ImageView>(R.id.ivNavigateBefore)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        navigateBefore?.setOnClickListener { finish() }
        tvTitle?.isSelected = true  //获取焦点，实现跑马灯效果。

    }
}
