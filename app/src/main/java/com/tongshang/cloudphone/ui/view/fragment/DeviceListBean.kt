package com.tongshang.cloudphone.ui.view.fragment

class DeviceListBean : ArrayList<DeviceListBean.DeviceListBeanItem>(){
    data class DeviceListBeanItem(
        val bindStatus: Int,
        val buyTime: Long,
        val deviceAliases: String?,  // 如果是字符串类型，使用 String? 代表可空类型
        val deviceId: String,
        val displayStatus: Int,
        val instanceId: String,
        val isFlowStart: Boolean,
        val itemCode: String,
        val itemName: String,
        val lastRenewTime: Long?,  // 如果是时间戳，改为 Long?，可空类型
        val onHookState: Int,
        val orderNo: String,
        val oversoldStatus: Int,
        val regionCode: String,
        val regionId: Int,
        val resourcePoolId: Long,
        val screenshotAddress: String?,
        val startFlowBizType: Int,
        val status: Int,
        val tenantId: Long,
        val tnMachineId: Long,
        val userDeviceId: Long,
        val userId: Long,
        val userMaintStatus: Int,
        val validEndTime: Long,
        val validStartTime: Long
    )

}