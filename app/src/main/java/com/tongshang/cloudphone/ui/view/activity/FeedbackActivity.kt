package com.tongshang.cloudphone.ui.view.activity

import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityFeedbackBinding
import com.tongshang.cloudphone.utils.EditTextUtils

/**
 * @author: z2660
 * @date: 2024/12/6
 * 意见反馈
 */
class FeedbackActivity : BaseActivity() {

    private lateinit var _binding: ActivityFeedbackBinding

    override fun getLayoutView(): Int {
        return R.layout.activity_feedback
    }

    override fun initView() {
        _binding = ActivityFeedbackBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局

        setTitleText("意见反馈")
        setLayAllBackgroundColor_1(R.color.white)

        // 点击空白处隐藏键盘
        hideKeyboard(_binding.root)


        EditTextUtils.setCharacterCountWatcher( _binding.editText7, _binding.index, 100)

        _binding.editText7.setOnClickListener{
        }




    }
}