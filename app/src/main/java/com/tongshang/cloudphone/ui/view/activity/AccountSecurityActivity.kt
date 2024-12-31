package com.tongshang.cloudphone.ui.view.activity

import android.content.Intent
import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityAccountsecBinding
import com.tongshang.cloudphone.utils.CountDownDialog

/**
 * @author: z2660
 * @date: 2024/12/4
 * 账户与安全
 */
class AccountSecurityActivity : BaseActivity() {

    private lateinit var _binding: ActivityAccountsecBinding


    override fun getLayoutView(): Int {
        return R.layout.activity_accountsec
    }

    override fun initView() {


        _binding = ActivityAccountsecBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局


        // 设置标题和图标
        setTitleText("账户与安全")
        setLayAllBackgroundColor_1(R.color.grey)


        _binding.constraintLayout3.setOnClickListener {
            startActivity(Intent(this,RebindingActivity::class.java))
        }

        _binding.constraintLayout4.setOnClickListener {
            startActivity(Intent(this,ReviseActivity::class.java))
        }
        _binding.constraint5.setOnClickListener {
            CountDownDialog(this,"注销账号","注销账号，将删除该账号所有的数据与个人信息，并无法恢复，请知悉账号注销的影响，谨慎考虑。").showDialog()
        }


    }
}