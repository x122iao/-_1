package com.tongshang.cloudphone.cloudphone;

import android.content.pm.ActivityInfo;

import com.sq.sdk.cloudgame.SdkConfig;

/**
 * @title
 * @description
 * @auther sqtech
 */
public class AppConst {
    //传递参数bundle key
    public final static String BUNDLE_ARGS_KEY = "launch_args";

    public static final String TOKEN_WX = "b499dadb25a145919adf48e409eeb7fe";  //分配给你的商户token
    public static final String SECRET_WX = "49299926bb9e439cbeb5128c3e93f249"; //分配给你的商户key


    public static String TOKEN = TOKEN_WX;
    public static String SECRET = SECRET_WX;

    public static String USER_ID = "user_id_test";
    public static final String CLOUD_GAME = "qcom.android";
    public static final SdkConfig.CloudEnv ENV = SdkConfig.CloudEnv.YYX;
    public static final boolean QUEUE = false;
    public static final int ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    //需要测试的云机列表, 云机列表需要你后台 server2server返回获得
    public final static String[] TEST_USER_PHONE_IDS =  {"2dcd238ff96d411e839227a229f4e24f"};

}
