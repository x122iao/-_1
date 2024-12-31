package com.tongshang.cloudphone.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class InformationViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    private val deviceProblemList = listOf("设备无法启动", "设备连接不上网络", "设备电池问题")
    private val accountProblemList = listOf("无法登录", "密码错误", "账号冻结")
    private val purchaseProblemList = listOf("支付失败", "商品缺货", "订单取消")
    private val otherProblemList = listOf("其他未知问题")

    // 返回页面数量
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        val problemList = when (position) {
            0 -> deviceProblemList
            1 -> accountProblemList
            2 -> purchaseProblemList
            3 -> otherProblemList
            else -> deviceProblemList
        }
        return ProblemFragment.newInstance(problemList)
    }
}
