package com.tongshang.cloudphone.ui.view.activity

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.m7.imkfsdk.utils.permission.PermissionConstants
import com.m7.imkfsdk.utils.permission.PermissionXUtil
import com.m7.imkfsdk.utils.permission.callback.OnRequestCallback
import com.tongshang.cloudphone.*
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.cloudphone.CpSdkApiModel
import com.tongshang.cloudphone.databinding.ActivityMainBinding
import com.tongshang.cloudphone.ui.adapter.FragmentAdapter
import com.tongshang.cloudphone.utils.ToastUtil


class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding // ViewBinding for MainActivity

    override fun getLayoutView(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        // 初始化 ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // 设置子类的 ViewBinding 根视图

        // TODO: 云手机
        // 初始化 CpSdkApi
        CpSdkApiModel.init(this)

        PermissionXUtil.checkPermission(
            this, object : OnRequestCallback {
                override fun requestSuccess() {
                    // 权限请求成功
                    ToastUtil.showShortToast("存储权限已授权")
                }

                fun requestFail() {
                    // 权限请求失败
                    ToastUtil.showShortToast("存储权限申请失败")

                }
            },  // 根据版本选择权限
            if (Build.VERSION.SDK_INT >= 33) PermissionConstants.IMAGEBY_API33 else Manifest.permission.READ_EXTERNAL_STORAGE
        )


        val fragmentAdapter = FragmentAdapter(this)
        binding.viewPager.adapter = fragmentAdapter

        // 禁用 ViewPager2 的滑动功能，可以这样做
        binding.viewPager.isUserInputEnabled = false
        // 设置 ViewPager2 默认打开第二页
        binding.viewPager.setCurrentItem(1, false)
        binding.bottomNavigationView.selectedItemId = R.id.navigation_dashboard // 设置 BottomNavigationView 为第二项
        binding.viewPager.setUserInputEnabled(false); // true:滑动，false：禁止滑动

        // 注册 ViewPager2 的页面变化监听器
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                // 根据页面位置设置 BottomNavigationView 选中的项
                when (position) {
                    0 -> binding.bottomNavigationView.selectedItemId = R.id.navigation_home
                    1 -> binding.bottomNavigationView.selectedItemId = R.id.navigation_dashboard
                    2 -> binding.bottomNavigationView.selectedItemId = R.id.navigation_notifications
                }
            }
        })

        // 设置 BottomNavigationView 的项选择监听器
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val currentItem = binding.viewPager.currentItem
            when (item.itemId) {
                R.id.navigation_home -> {
                    if (currentItem != 0) {
                        binding.viewPager.setCurrentItem(0, false)
                    }
                    true
                }
                R.id.navigation_dashboard -> {
                    if (currentItem != 1) {
                        binding.viewPager.setCurrentItem(1, false)
                    }
                    true
                }
                R.id.navigation_notifications -> {
                    if (currentItem != 2) {
                        binding.viewPager.setCurrentItem(2, false)
                    }
                    true
                }
                else -> false
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        // 释放sdk资源
        CpSdkApiModel.fini()
    }
}



