package com.ulb.code.ui.main

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.ulb.code.R
import com.ulb.common.base.BaseActivity

class MainActivity : BaseActivity() {

    private val fragmentManager: FragmentManager by lazy { supportFragmentManager }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}