package com.tongshang.cloudphone.base

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gyf.immersionbar.ImmersionBar
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.`interface`.AuthClickListener
import com.tongshang.cloudphone.ui.view.activity.AuthorizationRecordsActivity
import com.tongshang.cloudphone.utils.ScreenUtils

/**
 * BaseActivity 是所有活动的基类，提供了一些通用的功能和初始化逻辑。
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val TAG: String = "BaseActivity"
    protected lateinit var titleTextView: TextView
    protected lateinit var ivclose: ImageView
    protected lateinit var contentLayout: FrameLayout
    protected lateinit var auth: TextView
    protected lateinit var buy: Button


    private var authClickListener: AuthClickListener? = null

    // 设置点击事件回调
    fun setAuthClickListener(listener: AuthClickListener) {
        this.authClickListener = listener
    }


    protected inline fun <reified T : ViewModel> getViewModel(factory: ViewModelProvider.Factory): T {
        return ViewModelProvider(this, factory).get(T::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // 初始化沉浸式状态栏
        initImmersionBar()

        setContentView(R.layout.base_activity) // 基础的布局文件

        val findViewById = findViewById<ImageView>(R.id.iv_close)
        titleTextView = findViewById<TextView>(R.id.tv_title)
        ivclose = findViewById<ImageView>(R.id.iv_close)
        auth = findViewById<TextView>(R.id.auth)
        buy = findViewById<Button>(R.id.buy)


        // 设置顶部边距以避免重叠
        setTopMargin()

        // 加载子布局
        contentLayout = findViewById<FrameLayout>(R.id.lay_content)
        View.inflate(this, getLayoutView(), contentLayout)

        // 初始化子类视图
        initView()
        setListener()
        findViewById.setOnClickListener {
            finish()
        }


        auth.setOnClickListener {
            // 根据 auth.text 的内容触发不同的回调
            if (auth.text == "授权记录") {
                startActivity(Intent(this, AuthorizationRecordsActivity::class.java))
            } else if (auth.text == "在线客服") {
                authClickListener?.onCustomerServiceClicked()
            }
        }


    }


    /**
     * 初始化沉浸式状态栏
     */
    private fun initImmersionBar() {
        ImmersionBar.with(this)
            .transparentStatusBar() // 设置透明状态栏
            .statusBarDarkFont(true)
            .navigationBarColor(R.color.black)
            .navigationBarDarkIcon(true)
            .init() // 初始化
    }

    /**
     * 设置内容视图顶部边距，避免与状态栏重叠
     */
    private fun setTopMargin() {
        // 计算状态栏高度并设置顶部边距
        findViewById<LinearLayout>(R.id.lay_all).setTopMargin(
            ScreenUtils.getInstance().getStatusBarHeight(this)
        )
    }


    /**
     * 设置 lay_all 背景颜色
     * 如果 colorResId 为 -1，则移除背景色
     */
    protected open fun setLayAllBackgroundColor(colorResId: Int) {
        val layAll = findViewById<View>(R.id.lay_title)
        if (colorResId == -1) {
            // 如果传入 -1，则移除背景
            layAll.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        } else {
            // 设置背景颜色
            val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                resources.getColor(colorResId, null)
            } else {
                TODO("VERSION.SDK_INT < M")
            }
            layAll.setBackgroundColor(color)
        }
    }

    /**
     * 设置 lay_content 背景颜色
     * 如果 colorResId 为 -1，则移除背景色
     */
    protected open fun setLayAllBackgroundColor_1(colorResId: Int) {
        if (colorResId == -1) {
            // 如果传入 -1，则移除背景
            contentLayout.setBackgroundColor(android.graphics.Color.TRANSPARENT)
        } else {
            // 设置背景颜色
            val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                resources.getColor(colorResId, null)
            } else {
                TODO("VERSION.SDK_INT < M")
            }
            contentLayout.setBackgroundColor(color)
        }
    }


    /**
     * 设置视图的顶部边距
     */
    private fun View.setTopMargin(margin: Int) {
        val params = layoutParams as FrameLayout.LayoutParams
        params.setMargins(0, margin, 0, 0)
        layoutParams = params
    }

    protected abstract fun getLayoutView(): Int
    protected abstract fun initView()
    protected open fun setListener() {}

    /**
     * 设置标题文本
     */
    protected open fun setTitleText(titleText: String) {
        if (::titleTextView.isInitialized) {
            titleTextView.text = titleText
        } else {
            Log.e(TAG, "TextView tv_title is not initialized!")
        }
    }

    /**
     * 设置 imageView22 的图片资源
     */
    protected open fun setExtraImage(imageResId: Int) {
        val extraImage = findViewById<ImageView>(R.id.imageView22)
        extraImage.setImageResource(imageResId)
    }


    protected open fun setAuth(show: Boolean?, name: String?) {
        auth.text = name

        // 如果 show 为 true，则显示 auth，否则隐藏 auth
        if (show == true) {
            auth.visibility = View.VISIBLE // 显示
        } else {
            auth.visibility = View.GONE // 隐藏
        }

    }
    protected open fun setBuy(show: Boolean?) {

        // 如果 show 为 true，则显示 auth，否则隐藏 auth
        if (show == true) {
            buy.visibility = View.VISIBLE // 显示
        } else {
            buy.visibility = View.GONE // 隐藏
        }

    }


    protected open fun setIconImage(imageResId: Int) {
        val closeIcon = findViewById<ImageView>(R.id.iv_close)
        closeIcon.setImageResource(imageResId)
    }


    /**
     * 控制视图的显示与隐藏
     */
    protected fun View.toggleVisibility(show: Boolean?) {
        visibility = if (show == true) View.VISIBLE else View.GONE
    }


    // 隐藏键盘的方法
// 隐藏键盘的方法，接收一个 View 类型的参数
    @SuppressLint("ClickableViewAccessibility")
    protected fun hideKeyboard(view: View) {
        // 点击空白处隐藏键盘
        view.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                // 只要指定的 View 没有获取焦点，键盘就会被隐藏
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            false
        }


    }



}
