package com.tongshang.cloudphone.ui.view.activity

import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.data.repository.NetworkManager
import com.tongshang.cloudphone.data.repository.ProductRepository
import com.tongshang.cloudphone.databinding.ActivityCertificationBinding
import com.tongshang.cloudphone.ui.viewmodel.CertificationViewModel
import com.tongshang.cloudphone.ui.viewmodel.ProductViewModelFactory
import com.tongshang.cloudphone.utils.ToastUtil

/**
 * @author: z2660
 * @date: 2024/12/6
 *
 * 实名认证
 */
class CertificationCertification : BaseActivity() {

    private lateinit var _binding: ActivityCertificationBinding
    private lateinit var certificationViewModel: CertificationViewModel

    override fun getLayoutView(): Int {
        return R.layout.activity_certification
    }

    override fun initView() {

        _binding = ActivityCertificationBinding.inflate(layoutInflater)
        // 初始化 ViewModel
        certificationViewModel = getViewModel(ProductViewModelFactory(NetworkManager(
            ProductRepository()
        )))

        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局

        // 设置标题和图标
//        setTitleText("云机授权")
        setExtraImage(R.mipmap.cert_1)

        // 点击空白处隐藏键盘
        hideKeyboard(_binding.root)

        // 发起请求
        certificationViewModel.fetchSpeedData(this)

        // 观察速度数据变化
        observeSpeedData()


    }

    private fun observeSpeedData() {
        certificationViewModel.speedData.observe(this) { speeds ->
            // 更新 UI
            if (speeds.isNotEmpty()) {
                val speedText = speeds.joinToString(", ") { it.toString() }
                ToastUtil.showShortToast(speedText+"")
            } else {
                ToastUtil.showShortToast("请求失败或没有速度数据")
            }
        }
    }
}