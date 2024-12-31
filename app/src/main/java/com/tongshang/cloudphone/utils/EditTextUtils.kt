package com.tongshang.cloudphone.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView

/**
 * @author: z2660
 * @date: 2024/12/7
 */
object EditTextUtils {

    // 封装监听文本输入并显示已输入字符数的逻辑
    fun setCharacterCountWatcher(
        editText: EditText,
        textView: TextView,
        maxLength: Int
    ) {
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // 获取当前输入的字符长度
                val currentLength = charSequence?.length ?: 0
                // 更新显示字符数
                textView.text = "$currentLength/$maxLength"
            }

            override fun afterTextChanged(editable: Editable?) {}
        })
    }
}
