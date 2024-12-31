package com.tongshang.cloudphone.ui.view.activity

import android.content.Context
import android.content.pm.ActivityInfo
import android.view.View
import android.widget.FrameLayout
import com.sq.sdk.cloudgame.*
import com.tongshang.cloudphone.BuildConfig
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity
import com.tongshang.cloudphone.databinding.ActivityAaaaaaaaaaBinding
import com.tongshang.cloudphone.utils.ToastUtil

/**
 * @author: z2660
 * @date: 2024/12/7
 */
class Aaaaaaaaaaaaaaaaaaaaaa : BaseActivity() {

    private lateinit var _binding: ActivityAaaaaaaaaaBinding

    // mISdkInitListener 可能是类中的一个可空成员变量
    private var mISdkInitListener: ISdkInitListener? = null
    private var mICloudSdkListener: ICloudSdkListener? = null
    private var isSdkInitialized = false  // 用来标记 SDK 是否已经初始化过

    override fun getLayoutView(): Int {
        return R.layout.activity_aaaaaaaaaa
    }

    override fun initView() {
        _binding = ActivityAaaaaaaaaaBinding.inflate(layoutInflater)
        val contentLayout = findViewById<FrameLayout>(R.id.lay_content) // 获取父布局容器
        contentLayout.removeAllViews() // 清空父布局
        contentLayout.addView(_binding.root) // 将子布局添加到父布局

        // 初始化时配置 mISdkInitListener
        mISdkInitListener = object : ISdkInitListener {
            override fun onInitResult(i: Int, s: String) {
                // 仅在 SDK 初始化成功且尚未初始化过时处理
                if (!isSdkInitialized) {
                    isSdkInitialized = true  // 标记 SDK 已初始化
                    // 处理初始化结果
                    mISdkInitListener?.onInitResult(i, s)
                }
            }

            override fun onMessage(i: Int, s: String): Boolean {
                // 处理消息
                return mISdkInitListener?.onMessage(i, s) ?: false
            }
        }

        // 初始化 CloudSdk
        CloudSdk.getInstance().init(this, SdkConfig.Builder()
            .setAppEnv(AppEnv.YYX.toCloudEnv())  // 将 AppEnv 转换为 CloudEnv
            .setSupportH265(true)  // 是否支持H265, 默认true
            .setEnableLog(BuildConfig.IS_DEBUG)  // 是否开启日志，默认false
            .setPermissionCheck(false)  // 是否需要sdk做敏感读写存储权限的合规授权检查
//            .setBusinessChannelCode("001")  // 业务方分发到云机的渠道号
            .setSdkInitListener(mISdkInitListener)  // 设置初始化监听器
            .create()  // 创建配置
        )


        if(isSdkInitialized){

            ToastUtil.showShortToast("云手机初始化成功")

            val myMutableList: MutableList<String> = mutableListOf()
            myMutableList.add("cd4b829886f8495ea822b58c3d9e8744")
//            传入用户云机列表
            CloudSdk.getInstance().setDeviceList(myMutableList)


            /**
             * 【必选性】
             * 触发启动云串流
             * @param config 传入启动配置项参数
             */
            CloudSdk.getInstance().start(
                this, // 传递当前 Activity 或 Context
                StartConfig.Builder()
                    .setToken("862c507f49d74122be8f2005e03f202f")
                    .setKey("71b4031653ce48dead953c528471b373")
                    .setUserId("1872546190793469953") // 真实用户标识id，一般由客户上层对接登录后获得唯一标识身份
                    .setUserPhoneId("1872549156737910792") // 商户用户设备ID，由客户上层通过server2server分配给真实用户云机后的数据
                    .setOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    .setListener(object : ICloudSdkListener { // 确保使用 ICloudSdkListener 接口
                        override fun onMessage(action: Int, message: String): Boolean {
                            when (action) {
                                1 -> { // ACTION_LAUNCH_SUCCESS
                                    Log.d("云手机", "云 SDK 启动成功。消息：$message")
                                    ToastUtil.showShortToast("云 SDK 启动成功。消息：$message")

                                }
                                2 -> { // ACTION_FAILURE_CONNECT
                                    Log.e("云手机", "连接失败。消息：$message")
                                    ToastUtil.showShortToast("连接失败。消息：$message")

                                }
                                3 -> { // ACTION_USER_EXIT
                                    Log.d("云手机", "用户退出。消息：$message")
                                    ToastUtil.showShortToast("用户退出。消息：$message")
                                }
                                else -> {
                                        Log.d("云手机", "未知的操作：$action，消息：$message")
                                    ToastUtil.showShortToast("未知的操作：$action，消息：$message")
                                }
                            }
                            return true
                        }

                        override fun onMenuClick(context: Context, view: View): Boolean {
                            Log.d("云手机", "菜单点击！")
                            ToastUtil.showShortToast("菜单点击！")
                            return true // 已处理
                        }

                        override fun onPayment(context: Context, paymentInfo: String): Boolean {
                            Log.d("云手机", "收到支付信息：$paymentInfo")
                            ToastUtil.showShortToast("收到支付信息：$paymentInfo")
                            return true // 已处理
                        }

                        override fun onMemberCtrlPermissions(permission: String): Boolean {
                            Log.d("云手机", "收到会员控制权限：$permission")
                            ToastUtil.showShortToast("收到会员控制权限：$permission")
                            return true // 已处理
                        }
                    })
                    .create()
            )


        }else{
            ToastUtil.showShortToast("云手机初始化失败")
        }


    }

}
