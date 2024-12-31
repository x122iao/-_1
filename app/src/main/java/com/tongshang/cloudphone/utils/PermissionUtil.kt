package com.tongshang.cloudphone.utils

import android.Manifest
import android.os.Build
import androidx.fragment.app.FragmentActivity
import com.m7.imkfsdk.utils.permission.PermissionConstants
import com.m7.imkfsdk.utils.permission.PermissionXUtil
import com.m7.imkfsdk.utils.permission.callback.OnRequestCallback

object PermissionUtil {

    /**
     * 请求权限的通用方法
     * @param activity FragmentActivity，必须是 FragmentActivity 或其子类
     * @param permissions 权限数组，可以是任意权限
     * @param callback 请求成功后的回调
     */
    fun requestPermissions(activity: FragmentActivity, permissions: Array<String>, callback: OnRequestCallback) {
        // 请求权限
        PermissionXUtil.checkPermission(activity, callback, *permissions)
    }

    /**
     * 请求读取外部存储的权限
     * 根据系统版本判断请求不同的权限
     * @param activity FragmentActivity
     * @param callback 请求成功后的回调
     */
    fun requestReadExternalStoragePermission(activity: FragmentActivity, callback: OnRequestCallback) {
        val permission = if (Build.VERSION.SDK_INT >= 33) {
            arrayOf(PermissionConstants.IMAGEBY_API33) // Android 13 及以上版本需要特殊权限
        } else {
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE) // 旧版本请求普通的存储权限
        }

        requestPermissions(activity, permission, callback)
    }
}
