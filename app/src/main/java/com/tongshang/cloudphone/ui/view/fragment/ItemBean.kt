package com.tongshang.cloudphone.ui.view.fragment

import android.media.Image

/**
 * @author: z2660
 * @date: 2024/12/31
 */
// ItemBean 用于表示 ViewPager 中的数据项
data class ItemBean(
    val name: String,// 用户定义的云机名字  （用户可修改）
    val duration: String, //剩余时长
    val disposition: Int,// 配置
    val grouping: String, // 拓展用  分组
    val imagePath: String, // 示例截图
)

