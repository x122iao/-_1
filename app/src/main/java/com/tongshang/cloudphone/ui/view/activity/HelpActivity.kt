package com.tongshang.cloudphone.ui.view.activity

import android.content.Intent
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.m7.imkfsdk.KfStartHelper
import com.moor.imkf.requesturl.RequestUrl
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.`interface`.AuthClickListener
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityHelpBinding
import com.tongshang.cloudphone.ui.adapter.ViewPagerAdapter
import com.tongshang.cloudphone.utils.GlideImageLoader

/**
 * @author: z2660
 * @date: 2024/12/6
 *
 * 反馈帮助    在线客服
 */
class HelpActivity : BaseActivity(),AuthClickListener {

    private lateinit var _binding: ActivityHelpBinding
//    listOf("设备问题","账号问题","购买问题","其他问题")

    override fun getLayoutView(): Int {
        return R.layout.activity_help
    }

    override fun initView() {
        _binding = ActivityHelpBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局

        setTitleText("反馈帮助")
        setLayAllBackgroundColor_1(R.color.grey)
        setAuth(true, "在线客服")

        // 点击空白处隐藏键盘
        hideKeyboard(_binding.root)

        setAuthClickListener(this)
        // Tab 数据
        val tabTitles = listOf("设备问题", "账号问题", "购买问题", "其他问题")

        val adapter = ViewPagerAdapter(this)
        _binding.viewPager.adapter = adapter

        // 使用 TabLayoutMediator 来连接 TabLayout 和 ViewPager2
        TabLayoutMediator(_binding.tabLayout, _binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "设备问题"
                1 -> "账号问题"
                2 -> "购买问题"
                3 -> "其他问题"
                else -> "设备问题"
            }
        }.attach()


// 获取 TabLayout 中的 LinearLayout
        val linearLayout = _binding.tabLayout.getChildAt(0) as LinearLayout

// 遍历 Tab Items 设置 margin
        for (i in 0 until linearLayout.childCount) {
            val tabView = linearLayout.getChildAt(i)
            val params = tabView.layoutParams as LinearLayout.LayoutParams
            params.setMargins(12, 12, 12, 12) // 设置 Tab Item 左右的间距
            tabView.layoutParams = params
        }


        _binding.next.setOnClickListener {
            startActivity(Intent(this, FeedbackActivity::class.java))
        }


    }

//    客服回调
    override fun onCustomerServiceClicked() {
        ykf()
    }



    private var helper: KfStartHelper? = null

    // 接入id（需后台配置获取）
    private val accessId = "da105e50-40b8-11ee-8f3e-8bdad8009fbf"

    //    用户名
    private val userName = "17851854017"

    //用户id
    private val userId = "1"


    private fun ykf() {

        if (helper == null) {
            helper = KfStartHelper.getInstance()
        }

        helper = KfStartHelper.getInstance()

        RequestUrl.setRequestBasic(RequestUrl.TENCENT_REQUEST)

        helper ?.setImageLoader(GlideImageLoader())
        helper ?.setChatActivityLeftText(getString(R.string.close))

//        helper?.let { initSdk(it) }

        helper ?.initSdkChat(accessId, userName, userId)

    }


}