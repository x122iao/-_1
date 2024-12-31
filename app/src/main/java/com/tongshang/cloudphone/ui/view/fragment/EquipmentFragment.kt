package com.tongshang.cloudphone.ui.view.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.gson.JsonObject
import com.sq.sdk.cloudgame.*
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.base.BaseActivity_buy
import com.tongshang.cloudphone.base.BaseFragment
import com.tongshang.cloudphone.cloudphone.AppConst
import com.tongshang.cloudphone.cloudphone.CpSdkApiModel
import com.tongshang.cloudphone.data.repository.NetworkManager
import com.tongshang.cloudphone.data.repository.ProductRepository
import com.tongshang.cloudphone.databinding.FragmentEquipentBinding
import com.tongshang.cloudphone.ui.view.adapter.YourAdapter
import com.tongshang.cloudphone.ui.viewmodel.EquipmentViewModel
import com.tongshang.cloudphone.ui.viewmodel.ProductViewModelFactory
import org.json.JSONException
import org.json.JSONObject


/**
 * @author: z2660
 * @date: 2024/12/3
 *
 * 云设备
 */
class EquipmentFragment : BaseFragment() {

    private lateinit var _binding: FragmentEquipentBinding
    private lateinit var equipmentViewModel: EquipmentViewModel




    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: YourAdapter
    private val dataList = mutableListOf<Any>()

    override fun getLayoutView(): Int {
        return R.layout.fragment_equipent
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun initView(rootView: View) {

        // 初始化 ViewBinding
        _binding = FragmentEquipentBinding.bind(rootView)
        equipmentViewModel = getViewModel(ProductViewModelFactory(NetworkManager(ProductRepository())))

        icon(false)


        val jsonObject = JsonObject()
        jsonObject.addProperty("userId","1859847902663794690")
        equipmentViewModel.getDeviceList(requireContext(),jsonObject)

        observeSpeedData()

        viewPager2 = _binding.viewPager2

//        if (hasData()) {
//            // 有数据时添加数据项
//            for (i in 0..9) {  // 假设有10个数据项
//                dataList.add(ItemBean("云机名字","剩余时长",1,"分组",""))
//            }
//        }

        // 如果有数据，添加默认图像项到最后
//        if (dataList.isNotEmpty()) {
//            dataList.add("DEFAULT_IMAGE")
//        } else {
//            // 如果没有数据，直接显示默认图像项
//            dataList.add("DEFAULT_IMAGE")
//        }
//


        // 获取当前页面的下标
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("ViewPager", "当前页下标：$position")
            }
        })




    }

    private fun hasData(): Boolean {

        // 根据实际情况判断是否有数据
        return false  // 假设有数据
    }

    private fun observeSpeedData() {
        equipmentViewModel.deviceList.observe(viewLifecycleOwner) { deviceList ->
            // 构建设备列表 + 默认图像项
            val combinedList = mutableListOf<Any>()

            Log.d("deviceList", deviceList.size.toString())

            if (deviceList.isNullOrEmpty()) {
                // 如果设备列表为空，添加默认图像项到列表
                Log.d("observeSpeedData", "设备列表为空")
                combinedList.add("DEFAULT_IMAGE")  // 默认图像项
            } else {
                // 设备列表不为空，添加设备项
                Log.d("observeSpeedData", "设备列表不为空")
                combinedList.addAll(deviceList)  // 设备项
            }

            // 设备列表最后添加默认图像项
            combinedList.add("DEFAULT_IMAGE")  // 默认图像项

            // 设置 ViewPager2 和适配器
            adapter = YourAdapter(
                combinedList,  // 传递合并后的列表
                object : YourAdapter.OnItemClickListener {
                    override fun onItemClick(position: Int) {
                        // 处理数据项点击事件
                        val item = combinedList[position]
                        if (item is ItemBean) {
                            Log.d("YourAdapter", "点击了数据项: ${item.name}，位置: $position")
                        }
                    }
                },
                object : YourAdapter.OnImageClickListener {
                    override fun onImageClick(viewId: Int) {
                        // 处理图像项点击事件
                        when (viewId) {
                            R.id.imageView5 -> startActivity(Intent(requireContext(), BaseActivity_buy::class.java))
                            R.id.imageView48 -> startActivity(Intent(requireContext(), BaseActivity_buy::class.java))
                        }
                    }
                }
            )
            viewPager2.adapter = adapter  // 更新 ViewPager2 的适配器
        }
    }



}




