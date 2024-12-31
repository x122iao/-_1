package com.tongshang.cloudphone.ui.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.ui.view.fragment.DeviceListBean
import com.tongshang.cloudphone.ui.view.fragment.ItemBean
import java.io.File

/**
 * @author: z2660
 * @date: 2024/12/31
 */
class YourAdapter(
    private val dataList: List<Any>, // 数据列表，包括数据项和默认图像项
    private val onItemClickListener: OnItemClickListener, // 数据项点击回调接口
    private val onImageClickListener: OnImageClickListener // 图像项点击回调接口
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 定义视图类型
    companion object {
        private const val VIEW_TYPE_DATA = 0 // 数据项视图
        private const val VIEW_TYPE_DEFAULT_IMAGE = 1 // 默认图像项视图
    }

    // 判断当前项的数据类型
    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is DeviceListBean -> VIEW_TYPE_DATA // 数据项
            "DEFAULT_IMAGE" -> VIEW_TYPE_DEFAULT_IMAGE // 默认图像项
            else -> throw IllegalArgumentException("Invalid view type") // 异常类型，必要时可以换成日志或默认值
        }
    }

    // 创建不同类型的 ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_DATA -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
                DataViewHolder(view)
            }
            VIEW_TYPE_DEFAULT_IMAGE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_default_image, parent, false)
                DefaultImageViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    // 绑定数据到视图
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DataViewHolder -> {
                val item = dataList[position] as DeviceListBean
                holder.bind(item)
            }
            is DefaultImageViewHolder -> {
                // 只需要将点击事件监听传递过去
                holder.bind(onImageClickListener)
            }
        }

        // 数据项的点击事件回调
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }
    }

    // 获取数据项的数量
    override fun getItemCount(): Int = dataList.size

    // 数据项 ViewHolder
    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)
        private val textView62: TextView = itemView.findViewById(R.id.textView62)
        private val button: Button = itemView.findViewById(R.id.button)

        fun bind(item: DeviceListBean) {

            Glide.with(itemView.context)
                .load(File(""))  // 加载图片
                .error(R.mipmap.about_1)
                .into(imageView)  // 设置到 ImageView
            button.setOnClickListener {
                // TODO:  跳转到续费 需要携带当前配置信息   disposition 等
            }
            textView62.setOnClickListener {
                // TODO:  跳转到续费 需要携带当前配置信息   disposition 等
            }
        }
    }

    // 默认图像项 ViewHolder
    inner class DefaultImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView1: ImageView = itemView.findViewById(R.id.imageView5)
        private val imageView2: ImageView = itemView.findViewById(R.id.imageView48)

        fun bind(onImageClickListener: OnImageClickListener) {
            // 设置点击事件
            imageView1.setOnClickListener {
                onImageClickListener.onImageClick(imageView1.id)  // 传递点击的 imageView1 ID
            }
            imageView2.setOnClickListener {
                onImageClickListener.onImageClick(imageView2.id)  // 传递点击的 imageView2 ID
            }
        }
    }

    // 数据项点击回调接口
    interface OnItemClickListener {
        fun onItemClick(position: Int) // 返回点击的数据项位置
    }

    // 图像项点击回调接口
    interface OnImageClickListener {
        fun onImageClick(viewId: Int) // 返回点击的视图ID
    }
}
