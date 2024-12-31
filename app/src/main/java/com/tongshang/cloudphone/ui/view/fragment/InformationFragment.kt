package com.tongshang.cloudphone.ui.view.fragment

import android.view.View
import android.widget.LinearLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseFragment
import com.tongshang.cloudphone.databinding.FragmentInformationBinding
import com.tongshang.cloudphone.ui.adapter.InformationViewPagerAdapter
import com.tongshang.cloudphone.ui.adapter.ViewPagerAdapter

/**
 * @author: z2660
 * @date: 2024/12/3
 *
 * 资讯
 */
class InformationFragment : BaseFragment() {

    private lateinit var _binding: FragmentInformationBinding

    override fun getLayoutView(): Int {
        return R.layout.fragment_information
    }

    override fun initView(rootView: View) {
        // 初始化 ViewBinding
        _binding = FragmentInformationBinding.bind(rootView)

        icon(false)

        // Tab 数据
        val tabTitles = listOf("公告", "游戏", "咨询")

        val adapter = InformationViewPagerAdapter(requireActivity())
        _binding.viewPager.adapter = adapter

        // 使用 TabLayoutMediator 来连接 TabLayout 和 ViewPager2
        TabLayoutMediator(_binding.tabLayout, _binding.viewPager) { tab, position ->
            tab.text = tabTitles.get(position)
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



    }


}
