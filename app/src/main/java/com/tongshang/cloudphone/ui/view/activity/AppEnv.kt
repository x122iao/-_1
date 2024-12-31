package com.tongshang.cloudphone.ui.view.activity

import com.sq.sdk.cloudgame.SdkConfig

/**
 * @author: z2660
 * @date: 2024/12/7
 */
enum class AppEnv(val env: String) {
    NEWTEST("local"),
    YYX("pro"),
    SQC("pro_cluster");

    // 根据 env 值返回对应的 CloudEnv
    fun toCloudEnv(): SdkConfig.CloudEnv {
        return when (this) {
            NEWTEST -> SdkConfig.CloudEnv.NEWTEST
            YYX -> SdkConfig.CloudEnv.YYX
            SQC -> SdkConfig.CloudEnv.SQC
        }
    }
}
