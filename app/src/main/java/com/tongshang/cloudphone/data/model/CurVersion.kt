package com.tongshang.cloudphone.data.model

data class CurVersion(
    val curVersion: String,
    val downloadLink: Any,
    val publishTime: String,
    val renewNotice: RenewNotice,
    val renewVersion: String
) {
    data class RenewNotice(
        val content: String,
        val heading: String,
        val subtitle: String
    )
}