package com.tongshang.cloudphone.ui.view.fragment

import android.content.Intent
import android.view.View
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity_buy
import com.tongshang.cloudphone.base.BaseFragment
import com.tongshang.cloudphone.databinding.FragmentMyBinding
import com.tongshang.cloudphone.ui.view.activity.*

/**
 * @author: z2660
 * @date: 2024/12/3
 *
 * 我的
 */
class MyFragment : BaseFragment() {

    private lateinit var _binding: FragmentMyBinding


    override fun getLayoutView(): Int {
        return R.layout.fragment_my
    }

    override fun initView(rootView: View) {

        // 初始化 ViewBinding
        _binding = FragmentMyBinding.bind(rootView)

        icon(false)

        _binding.imageView12.setOnClickListener{
            startActivity(Intent(requireContext(), SetupActivity::class.java))
        }

        _binding.imageView16.setOnClickListener {
            startActivity(Intent(requireContext(), AuthorizationActivity::class.java))
        }

        _binding.imageView18.setOnClickListener {
            startActivity(Intent(requireContext(), CertificationCertification::class.java))
        }

        _binding.imageView20.setOnClickListener {
            startActivity(Intent(requireContext(), HelpActivity::class.java))
        }

        _binding.ImBuy.setOnClickListener {
            startActivity(Intent(requireContext(), BaseActivity_buy::class.java))
        }

        _binding.imageView11.setOnClickListener {
//            if(){}现是否是登录状态
//            LoginTypeUtils.getInstance().login(this)
            startActivity(Intent(requireContext(), LoginActivity::class.java))

        }
    }

}