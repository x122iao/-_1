package com.tongshang.cloudphone.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * TextWatcher 工具类，用于方便地给 EditText 添加监听器。
 */
object TextWatcherUtil {

    /**
     * 给 EditText 设置文本变化监听器
     * @param editText 要监听的 EditText
     * @param onTextChanged 文本变化时的回调函数，返回当前文本内容
     */
    fun setTextChangedListener(editText: EditText, onTextChanged: (String) -> Unit) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // 这里可以处理文本变化前的逻辑
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // 文本正在变化时调用
                onTextChanged(charSequence?.toString().orEmpty())
            }

            override fun afterTextChanged(editable: Editable?) {
                // 这里可以处理文本变化后的逻辑
            }
        })
    }
}
