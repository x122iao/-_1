package com.tongshang.cloudphone.ui.view.activity

import android.content.Intent
import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivitySetupBinding
import com.tongshang.cloudphone.utils.CacheUtils

/**
 * @author: z2660
 * @date: 2024/12/4
 *
 * 设置
 */
class SetupActivity : BaseActivity() {

    private lateinit var _binding: ActivitySetupBinding

    override fun getLayoutView(): Int {
        return R.layout.activity_setup // 这里返回 SetupActivity 的布局
    }

    override fun initView() {
        // 使用 ViewBinding 初始化控件
        _binding = ActivitySetupBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局
        // 设置标题和图标
        setTitleText("设置")
        setLayAllBackgroundColor_1(R.color.grey)

        // 获取缓存大小
        val cacheSize = CacheUtils.getCacheSize(this)
        val formattedSize = CacheUtils.formatSize(cacheSize)
        _binding.cache.text = formattedSize

        _binding.constraintLayout4.setOnClickListener {
            CacheUtils.clearCache(this)
            val cacheSize = CacheUtils.getCacheSize(this)
            val formattedSize = CacheUtils.formatSize(cacheSize)
            _binding.cache.text = formattedSize
        }

        _binding.constraintLayout3.setOnClickListener {
            startActivity(Intent(this,AccountSecurityActivity::class.java))

        }
        _binding.constraintLayout5.setOnClickListener {
            startActivity(Intent(this,AboutActivity::class.java))

        }
    }
}
//        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
//        contentLayout.removeAllViews() // 清空父布局
//        contentLayout.addView(_binding.root) // 将子布局添加到父布局