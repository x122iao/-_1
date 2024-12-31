package com.tongshang.cloudphone.cloudphone

import android.content.Context
import android.os.Bundle
import com.sq.sdk.cloudgame.*
import com.tongshang.cloudphone.BuildConfig
import com.tongshang.cloudphone.cloudphone.AppConst.TOKEN
import com.tongshang.cloudphone.cloudphone.AppConst.USER_ID
import com.tongshang.cloudphone.ui.view.activity.AppEnv
import java.util.Objects

/**
 * @author: z2660
 * @date: 2024/12/26
 */
class CpSdkApiModel {




    companion object {
        /**
         * CloudSdk API
         * 初始化接口
         * @param context 上下文
         */


        var mISdkInitListener: ISdkInitListener? = null
        val mICloudSdkListener: ICloudSdkListener? = null
        var isSdkInitialized = false  // 用来标记 SDK 是否已经初始化过


        fun init(context: Context) {


            // 初始化时配置 mISdkInitListener
             mISdkInitListener = object : ISdkInitListener {
                override fun onInitResult(i: Int, s: String) {
                    // 仅在 SDK 初始化成功且尚未初始化过时处理
                    if (!isSdkInitialized) {
                        // 标记 SDK 已初始化
                        isSdkInitialized = true
                        Log.d("CloudSdk", "SDK 初始化成功，结果：$i, 消息：$s")
                    }
                }

                override fun onMessage(i: Int, s: String): Boolean {
                    // 处理消息
                    Log.d("CloudSdk", "接收到消息，i: $i, 消息：$s")
                    return mICloudSdkListener?.onMessage(i, s) ?: false
                }
            }


            // 初始化

            CloudSdk.getInstance().init(context, SdkConfig.Builder()
                .setAppEnv(AppEnv.YYX.toCloudEnv())  // 将 AppEnv 转换为 CloudEnv
                .setSupportH265(true)  // 是否支持H265, 默认true
                .setEnableLog(BuildConfig.IS_DEBUG)  // 是否开启日志，默认false
                .setPermissionCheck(false)  // 是否需要sdk做敏感读写存储权限的合规授权检查
//            .setBusinessChannelCode("001")  // 业务方分发到云机的渠道号
                .setSdkInitListener(mISdkInitListener)  // 设置初始化监听器
                .create()  // 创建配置
            )

        }

        /**
         * CloudSdk API
         * 释放资源接口
         */
        fun fini() {
            // 释放资源
            CloudSdk.getInstance().fini()
        }


        /**
         * CloudSdk API
         * 启动云机
         * @param context
         * @param config
         */
        fun start(context: Context, config: StartConfig) {
            CloudSdk.getInstance().start(context, config)
        }


        /**
         * CloudSdk API
         * 主动刷新截图
         * @param deviceId
         * @param listener
         */
        fun notifyScreenshot(deviceId: String?, listener: ICloudSdkListener?) {
            /**
             * 通知截图
             * ************************截图需要的必要参数******************************
             * bundle.putString(SQ_BUNDLE_KEY_TOKEN,TOKEN);
             * bundle.putString(SQ_BUNDLE_KEY_SECRET,SECRET);
             * bundle.putString(SQ_BUNDLE_KEY_USERID, userId);
             * bundle.putString(SQ_BUNDLE_KEY_USER_PHONE_ID, userPhoneId); //新增
             * bundle.putString(SQ_BUNDLE_KEY_SCREENSHOT_CHANNEL,ScreenshotChannel.SDK.ordinal());//截图选择的方案, see @ScreenshotChannel
             * bundle.putString(SQ_BUNDLE_KEY_SCREENSHOT_URL,"https://www.cdn-capture-screenshot.com");//截图回调url
             * bundle.putInt(SQ_BUNDLE_KEY_SCREENSHOT_INTERVAL, 60); //截图间隔时间，如果不传，默认5s
             * bundle.putBoolean(SQ_BUNDLE_KEY_COMPRESS,false);//是否压缩 true——>压缩 false——>不压缩
             * @param bundle          请求参数
             */
            /**
             * 返回参数
             * result.putOpt(RESP_KEY_ACTION, ACTION_SCREENSHOT_UPDATE);
             * data.putOpt("path", path); //图片路径
             * data.putOpt("deviceId", deviceId);//应该deviceId
             * result.putOpt(RESP_KEY_DATA, data);
             */
            val bundle = Bundle()
            bundle.putString(ICloudSdkApi.SQ_BUNDLE_KEY_TOKEN, TOKEN)
            bundle.putString(
                ICloudSdkApi.SQ_BUNDLE_KEY_SECRET,
                ""
            ) //如果是商户固定token 只传token，需要sdk自动获取临时token
            bundle.putBoolean(ICloudSdkApi.SQ_BUNDLE_KEY_COMPRESS, false)
            bundle.putString(ICloudSdkApi.SQ_BUNDLE_KEY_USER_PHONE_ID, deviceId) //userPhoneId
            bundle.putString(ICloudSdkApi.SQ_BUNDLE_KEY_USERID, USER_ID) //userId
            bundle.putInt(ICloudSdkApi.SQ_BUNDLE_KEY_SCREENSHOT_INTERVAL, 10)
            bundle.putInt(
                ICloudSdkApi.SQ_BUNDLE_KEY_SCREENSHOT_CHANNEL,
                ICloudSdkApi.ScreenshotChannel.SDK.ordinal
            ) //截图选择的方案, see @ScreenshotChannel
            CloudSdk.getInstance().notifyScreenshot(bundle, listener)
        }

        /**
         * CloudSdk API
         * 把列表设备id 传到sdk
         * @param list
         */
//        fun setDeviceListData(list: List<DeviceBean?>) {
//            //传到sdk
//            val userPhoneList: MutableList<*> = ArrayList<Any?>(list.size)
//            for (deviceBean in list) {
//                if (deviceBean != null) {
//                    userPhoneList.add(deviceBean.getUserPhoneId() as Nothing)
//                }
//            }
//            CloudSdk.getInstance().setDeviceList(null)
//            CloudSdk.getInstance().setDeviceList(userPhoneList as MutableList<String>?)
//        }


    }
}
