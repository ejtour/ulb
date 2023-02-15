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

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import com.ulb.common.R
import com.ulb.common.RequestLifecycle
import com.ulb.common.utils.LogUtils


/**
 * 应用程序中所有Fragment的基类。
 */
open class BaseFragment : Fragment(), RequestLifecycle {

    /**
     * 是否已经加载过数据
     */
    private var mHasLoadedData = false

    /**
     * Fragment中由于服务器或网络异常导致加载失败显示的布局。
     */
    private var loadErrorView: View? = null

    /**
     * Fragment中inflate出来的布局。
     */
    protected var rootView: View? = null

    /**
     * Fragment中显示加载等待的控件。
     */
    protected var loading: ProgressBar? = null

    /**
     * 依附的Activity
     */
    lateinit var activity: Activity

    /**
     * 日志输出标志
     */
    protected val TAG: String = this.javaClass.simpleName

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // 缓存当前依附的activity
        activity = requireActivity()
        LogUtils.d(TAG, "BaseFragment-->onAttach()")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LogUtils.d(TAG, "BaseFragment-->onCreate()")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        LogUtils.d(TAG, "BaseFragment-->onCreateView()")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        LogUtils.d(TAG, "BaseFragment-->onViewCreated()")
    }

    override fun onStart() {
        super.onStart()
        LogUtils.d(TAG, "BaseFragment-->onStart()")
    }

    override fun onResume() {
        super.onResume()
        LogUtils.d(TAG, "BaseFragment-->onResume()")
        //当Fragment在屏幕上可见并且没有加载过数据时调用
        if (!mHasLoadedData) {
            loadDataOnce()
            LogUtils.d(TAG, "BaseFragment-->loadDataOnce()")
            mHasLoadedData = true
        }
    }

    override fun onPause() {
        super.onPause()
        LogUtils.d(TAG, "BaseFragment-->onPause()")
    }

    override fun onStop() {
        super.onStop()
        LogUtils.d(TAG, "BaseFragment-->onStop()")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        LogUtils.d(TAG, "BaseFragment-->onDestroyView()")
        if (rootView?.parent != null) (rootView?.parent as ViewGroup).removeView(rootView)
    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtils.d(TAG, "BaseFragment-->onDestroy()")
    }

    override fun onDetach() {
        super.onDetach()
        LogUtils.d(TAG, "BaseFragment-->onDetach()")
    }

    /**
     * 开始加载，将加载等待控件显示。
     */
    @CallSuper
    override fun startLoading() {
        loading?.visibility = View.VISIBLE
        hideLoadErrorView()
    }

    /**
     * 加载完成，将加载等待控件隐藏。
     */
    @CallSuper
    override fun loadFinished() {
        loading?.visibility = View.GONE
    }

    /**
     * 加载失败，将加载等待控件隐藏。
     */
    @CallSuper
    override fun loadFailed(msg: String?) {
        loading?.visibility = View.GONE
    }

    /**
     * 在Fragment基类中获取通用的控件，会将传入的View实例原封不动返回。
     * @param view Fragment中inflate出来的View实例。
     * @return  Fragment中inflate出来的View实例原封不动返回。
     */
    fun onCreateView(view: View): View {
        LogUtils.d(TAG, "BaseFragment-->onCreateView()")
        rootView = view
        //loading = view.findViewById(R.id.loading)
        return view
    }

    /**
     * 页面首次可见时调用一次该方法，在这里可以请求网络数据等。
     */
    open fun loadDataOnce() {

    }

    /**
     * 当Fragment中的加载内容服务器返回失败或网络异常，通过此方法显示提示界面给用户。
     *
     * @param tip 界面中的提示信息
     * @param block 点击屏幕重新加载，回调处理。
     */
    protected fun showLoadErrorView(tip: String, block: View.() -> Unit) {
        if (loadErrorView != null) {
            loadErrorView?.visibility = View.VISIBLE
            return
        }
        if (rootView != null) {
//            val viewStub = rootView?.findViewById<ViewStub>(R.id.loadErrorView)
//            if (viewStub != null) {
//                loadErrorView = viewStub.inflate()
//                val loadErrorText = loadErrorView?.findViewById<TextView>(R.id.loadErrorText)
//                loadErrorText?.text = tip
//                val loadErrorkRootView = loadErrorView?.findViewById<View>(R.id.loadErrorkRootView)
//                loadErrorkRootView?.setOnClickListener {
//                    it?.block()
//                }
//            }
        }
    }

    /**
     * 将load error view进行隐藏。
     */
    protected fun hideLoadErrorView() {
        loadErrorView?.visibility = View.GONE
    }

}