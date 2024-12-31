package com.tongshang.cloudphone.base

import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.databinding.ActivityBaseBuyBinding
import com.tongshang.cloudphone.ui.adapter.BuyAdapter

/**
 * @author: z2660
 * @date: 2024/12/13
 */
class BaseActivity_buy :BaseActivity() {


    private lateinit var _binding: ActivityBaseBuyBinding

    override fun getLayoutView(): Int {
        return R.layout.activity_base_buy
    }

    override fun initView() {
        // 初始化 ViewBinding
        _binding = ActivityBaseBuyBinding.inflate(layoutInflater)
        setContentView(_binding.root) // 设置子类的 ViewBinding 根视图


        _binding.viewpager.adapter = BuyAdapter(this)



    }

    fun setViewPagerItem(position: Int) {
        _binding.viewpager.setCurrentItem(position, false)
    }
}