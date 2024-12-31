package com.tongshang.cloudphone.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.tongshang.cloudphone.R
import org.jetbrains.annotations.Nullable

/**
 * @author: z2660
 * @date: 2024/10/25
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var TAG: String
    protected lateinit var mTitleText: TextView
    protected lateinit var mRightImage: ImageView
    protected lateinit var ivClose: ImageView
    protected lateinit var imageView22: ImageView
    protected lateinit var layTitle: RelativeLayout



    protected inline fun <reified T : ViewModel> getViewModel(factory: ViewModelProvider.Factory): T {
        return ViewModelProvider(this, factory).get(T::class.java)
    }

    @Nullable
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        TAG = this::class.java.name

        val rootView = inflater.inflate(R.layout.base_fragment, container, false)

        ImmersionBar.with(this)
            .transparentStatusBar() // 设置透明状态栏
            .statusBarDarkFont(true) // 状态栏字体颜色为白色
            .navigationBarColor(R.color.black) // 导航栏颜色
            .navigationBarDarkIcon(true) // 导航栏图标为白色
            .init() // 初始化

        // 初始化界面元素
        mTitleText = rootView.findViewById(R.id.tv_title)
        mRightImage = rootView.findViewById(R.id.iv_right)
        ivClose = rootView.findViewById(R.id.iv_close)
        layTitle = rootView.findViewById(R.id.lay_title)
        imageView22 = rootView.findViewById(R.id.imageView22)

        // 设置状态栏的边距
        val params =
            rootView.findViewById<LinearLayout>(R.id.lay_all).layoutParams as FrameLayout.LayoutParams
        params.setMargins(0, 10, 0, 0)

        // 关闭按钮点击事件
        ivClose.setOnClickListener { activity?.finish() }

        // 加载子布局
        val contentView = rootView.findViewById<FrameLayout>(R.id.lay_content)
        val view = inflater.inflate(getLayoutView(), contentView, false)
        contentView.addView(view)

        // 调用子类的初始化方法
        initView(view)

        setListener()

        return rootView
    }

    /**
     * 获取布局
     */
    protected abstract fun getLayoutView(): Int

    /**
     * 初始化
     */
    protected abstract fun initView(rootView: View)

    /**
     * 设置监听
     */
    protected open fun setListener() {}

    /**
     * 设置标题
     */
    protected fun setTitleText(titleText: String?) {
        if (::mTitleText.isInitialized && titleText != null) {
            mTitleText.text = titleText
        }
    }

    /**
     * 是否开启返回图标
     */
    protected fun icon(show: Boolean) {
        ivClose.visibility = if (show) View.VISIBLE else View.GONE
    }

    /**
     * 是否开启标题
     */
    protected fun title(isGone: Boolean) {
        layTitle.visibility = if (isGone) View.GONE else View.VISIBLE
    }

    /**
     * 控制额外的 ImageView 的显示与隐藏
     */
    protected fun image(show: Boolean) {
        imageView22.visibility = if (show) View.VISIBLE else View.GONE
    }

    /**
     * 设置背景的资源
     */
    protected fun iconImage(image: Int) {
        imageView22.setImageResource(image)
    }
}
