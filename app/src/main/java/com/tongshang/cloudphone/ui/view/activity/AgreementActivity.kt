package com.tongshang.cloudphone.ui.view.activity

import android.os.Build
import android.webkit.WebSettings
import android.widget.FrameLayout
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.data.repository.NetworkManager
import com.tongshang.cloudphone.data.repository.ProductRepository
import com.tongshang.cloudphone.databinding.ActivityAgreementBinding
import com.tongshang.cloudphone.ui.viewmodel.AboutViewModel
import com.tongshang.cloudphone.ui.viewmodel.ProductViewModelFactory

/**
 * @author: z2660
 * @date: 2024/12/12
 *
 *  协议
 */
class AgreementActivity :BaseActivity() {

    private lateinit var _binding: ActivityAgreementBinding
    private lateinit var aboutViewModel: AboutViewModel

    override fun getLayoutView(): Int {
        return R.layout.activity_agreement
    }

    override fun initView() {
        _binding = ActivityAgreementBinding.inflate(layoutInflater)
        aboutViewModel = getViewModel(ProductViewModelFactory(NetworkManager(ProductRepository())))

        val contentLayout = findViewById<FrameLayout>(R.id.lay_content)
        contentLayout.removeAllViews()
        contentLayout.addView(_binding.root)

        _binding.webview.settings.setJavaScriptEnabled(true)
        _binding.webview.settings.setDomStorageEnabled(true)
        _binding.webview.settings.setDatabaseEnabled(true)
        _binding.webview.settings.setCacheMode(WebSettings.LOAD_NO_CACHE)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            _binding.webview.settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW)
        }
        _binding.webview.setScrollContainer(false)

        //1:服务协议 2：隐私协议 3：购买协议

        //1:服务协议 2：隐私协议 3：购买协议
        val type = intent.getIntExtra("type", 0)
        if (type == 1) {
            _binding.webview.loadUrl(getString(R.string.url_user))
            setTitleText("用户协议")
        } else if (type == 2) {
            _binding.webview.loadUrl(getString(R.string.url_privacy))
            setTitleText("隐私政策")
        }

    }


}