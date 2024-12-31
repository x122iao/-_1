package com.tongshang.cloudphone.utils

import android.content.Context
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.tongshang.cloudphone.R

/**
 * @author: z2660
 * @date: 2024/12/6
 */

class CountDownDialog(
    context: Context,
    private val title: String,
    private val content: String,
    private val cancelText: String = "取消",
    private val confirmText: String = "确定"
) : AlertDialog(context) {

    private lateinit var okButton: Button
    private lateinit var cancelButton: Button
    private lateinit var countDownTextView: TextView
    private var isButtonEnabled = false // 控制按钮是否启用
    private var countDownTimer: CountDownTimer

    init {
        // 传入布局
        val dialogView = layoutInflater.inflate(R.layout.dialog_layout, null)
        setView(dialogView)

        // 获取视图组件
        okButton = dialogView.findViewById(R.id.okButton)
        cancelButton = dialogView.findViewById(R.id.cancelButton)
        countDownTextView = dialogView.findViewById(R.id.dialogTitle)

        // 设置标题和内容
        val dialogTitle = dialogView.findViewById<TextView>(R.id.dialogTitle)
        dialogTitle.text = title
        val dialogContent = dialogView.findViewById<TextView>(R.id.dialogContent)
        dialogContent.text = content

        // 初始化倒计时逻辑
        countDownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                okButton.text = "$confirmText ($secondsLeft)"
            }

            override fun onFinish() {
                isButtonEnabled = true
                okButton.text = confirmText
                okButton.isEnabled = true
            }
        }

        // 初始化按钮
        cancelButton.text = cancelText
        cancelButton.setOnClickListener {
            dismiss()  // 取消按钮点击时关闭弹窗
        }

        // 确定按钮逻辑
        okButton.text = "$confirmText (3)"
        okButton.isEnabled = false
        okButton.setOnClickListener {
            if (isButtonEnabled) {
                // 确定按钮点击事件
                Toast.makeText(context, "操作已确认", Toast.LENGTH_SHORT).show()
                dismiss()
            } else {
                Toast.makeText(context, "请等待倒计时结束", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // 显示弹窗并启动倒计时
    fun showDialog() {
        show()
        countDownTimer.start()
    }

    // 销毁弹窗时，取消倒计时
    override fun dismiss() {
        super.dismiss()
        countDownTimer.cancel()
    }
}
