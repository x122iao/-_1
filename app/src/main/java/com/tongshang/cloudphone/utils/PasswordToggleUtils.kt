package com.tongshang.cloudphone.utils

import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.EditText
import android.widget.ImageView

object PasswordToggleUtils {

    /**
     * 控制 EditText 的内容显示和隐藏，同时切换 ImageView 的图标
     * @param editText 密码输入框
     * @param toggleIcon 切换图标的 ImageView
     * @param showIcon 显示密码时的图标资源
     * @param hideIcon 隐藏密码时的图标资源
     */
    fun setPasswordVisibilityToggle(
        editText: EditText,
        toggleIcon: ImageView,
        showIcon: Int,
        hideIcon: Int
    ) {
        toggleIcon.setOnClickListener {
            if (editText.transformationMethod == PasswordTransformationMethod.getInstance()) {
                // 如果是隐藏密码，切换为显示密码
                editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                toggleIcon.setImageResource(showIcon)  // 设置显示密码图标
            } else {
                // 如果是显示密码，切换为隐藏密码
                editText.transformationMethod = PasswordTransformationMethod.getInstance()
                toggleIcon.setImageResource(hideIcon)  // 设置隐藏密码图标
            }

            // 将光标移到文本末尾
            editText.setSelection(editText.text.length)
        }
    }
}
