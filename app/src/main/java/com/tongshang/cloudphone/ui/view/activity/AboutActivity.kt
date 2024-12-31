package com.tongshang.cloudphone.ui.view.activity

import android.content.Intent
import android.widget.FrameLayout
import android.widget.Toast
import com.google.gson.JsonObject
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.data.repository.NetworkManager
import com.tongshang.cloudphone.data.repository.ProductRepository
import com.tongshang.cloudphone.databinding.ActivityAboutBinding
import com.tongshang.cloudphone.ui.viewmodel.AboutViewModel
import com.tongshang.cloudphone.ui.viewmodel.ProductViewModelFactory

/**
 * @author: z2660
 * @date: 2024/12/6
 * 关于我们
 */

class AboutActivity : BaseActivity() {

    private lateinit var _binding: ActivityAboutBinding
    private lateinit var aboutViewModel: AboutViewModel

    override fun getLayoutView(): Int {
        return R.layout.activity_about
    }

    override fun initView() {
        _binding = ActivityAboutBinding.inflate(layoutInflater)
        aboutViewModel = getViewModel(ProductViewModelFactory(NetworkManager(ProductRepository())))

        val contentLayout = findViewById<FrameLayout>(R.id.lay_content)
        contentLayout.removeAllViews()
        contentLayout.addView(_binding.root)

        // 设置标题和背景色
        setTitleText("关于我们")
        setLayAllBackgroundColor_1(R.color.grey)


        _binding.constraintLayout4.setOnClickListener {
            val intent = Intent(this, AgreementActivity::class.java)
            intent.putExtra("type", 1)
            startActivity(intent)
        }
        _binding.constraintLayout5.setOnClickListener {
            val intent = Intent(this, AgreementActivity::class.java)
            intent.putExtra("type", 2)
            startActivity(intent)
        }

        // 设置请求参数
        val params = JsonObject().apply {
            addProperty("platform", "1")
        }

        // 发起请求
        aboutViewModel.fetchCurVersion(this, params)

        // 观察数据变化
        observeViewModel()
    }

    private fun observeViewModel() {
        aboutViewModel.curVersion.observe(this) { version ->
            // 更新 UI
            _binding.version.text = version.curVersion
            Toast.makeText(this, "当前版本: ${version.curVersion}", Toast.LENGTH_SHORT).show()
        }


    }
}

