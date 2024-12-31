package com.tongshang.cloudphone.ui.view.fragment

import android.content.Intent
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity_buy
import com.tongshang.cloudphone.base.BaseFragment
import com.tongshang.cloudphone.data.model.Item
import com.tongshang.cloudphone.databinding.FragmentStandardardBinding
import com.tongshang.cloudphone.ui.adapter.StandAdapter
import com.tongshang.cloudphone.ui.view.activity.AgreementActivity
import com.tongshang.cloudphone.ui.view.activity.RenewalActivity
import com.tongshang.cloudphone.utils.ToastUtil

/**
 * @author: z2660
 * @date: 2024/12/13
 *
 *     标准
 */

class StandardFragment : BaseFragment() {

    private lateinit var _binding: FragmentStandardardBinding

    override fun getLayoutView(): Int {
        return R.layout.fragment_standardard
    }

    override fun initView(rootView: View) {
        // 初始化 ViewBinding
        _binding = FragmentStandardardBinding.bind(rootView)

        icon(false)
        iconImage(R.mipmap.found_1)
        _binding.base1.setOnClickListener {  activity?.finish() }


        Addersandsubtractors()

        setAgreementTextView()
        setOnClickListener()

        _binding.recy.layoutManager = LinearLayoutManager(requireContext());

        // 创建数据源
        val itemList = listOf(
            Item("Item 1"),
            Item("Item 2"),
            Item("Item 3"),
            Item("Item 4")
        )
        _binding.recy.adapter = StandAdapter(itemList)

    }

    private fun setOnClickListener(){
        _binding.imageView35.setOnClickListener {
            (activity as? BaseActivity_buy)?.setViewPagerItem(0)
        }
        _binding.imageView36.setOnClickListener {
            (activity as? BaseActivity_buy)?.setViewPagerItem(1)
        }
        _binding.imageView37.setOnClickListener {
            (activity as? BaseActivity_buy)?.setViewPagerItem(2)
        }

        _binding.bottom.setOnClickListener {
            startActivity(Intent(requireContext(),RenewalActivity::class.java))
        }
    }
    //    我已阅读并同意《天翼账号服务与隐私协议》、闪电云加速器《用户协议》与《隐私协议》注册并登录。
    private fun setAgreementTextView() {
        val HEAD = "阅读并同意"
        val URL = "《自动续费协议》"
        val AND = "和"
        val URL2 = "《购买协议》"
        val URL3 = "。"
        val TEXT = HEAD + URL + AND + URL2 + URL3

        val builder = SpannableStringBuilder()
        builder.append(TEXT)

        // 设置 "天翼账号服务与隐私协议" 为可点击
        builder.setSpan(object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(requireContext(), AgreementActivity::class.java)
                intent.putExtra("type", 3)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = resources.getColor(R.color.seni_1)
                ds.isUnderlineText = false
            }
        }, HEAD.length, HEAD.length + URL.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        // 设置 "用户协议" 为可点击
        builder.setSpan(
            object : ClickableSpan() {
                override fun onClick(widget: View) {
                    val intent = Intent(requireContext(), AgreementActivity::class.java)
                    intent.putExtra("type", 1)
                    startActivity(intent)
                }

                override fun updateDrawState(ds: TextPaint) {
                    super.updateDrawState(ds)
                    ds.color = resources.getColor(R.color.seni_1)
                    ds.isUnderlineText = false
                }
            },
            HEAD.length + URL.length + AND.length,
            HEAD.length + URL.length + AND.length + URL2.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )



        // 设置 TextView 允许点击
        _binding.tvAgreement.setMovementMethod(LinkMovementMethod.getInstance())
        _binding.tvAgreement.setText(builder)
        // 去掉点击后文字的背景色
        _binding.tvAgreement.setHighlightColor(Color.parseColor("#00000000"))
    }


    private fun Addersandsubtractors(){
        var count = 1 // 初始化计数器

        _binding.imageView41.setOnClickListener {
            if(count == 1 ){
                _binding.textView.text = "1"
                ToastUtil.showShortToast("数量不能小于1")
            }else{
                count --
                _binding.textView.text = count.toString()
            }
        }

        _binding.imageView42.setOnClickListener {
            count++
            _binding.textView.text = count.toString()
        }
    }

}