package com.tongshang.cloudphone.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.tongshang.cloudphone.R
import java.io.File


/**
 * @author: z2660
 * @date: 2024/12/26
 */
class CustomAdapter(
    private val dataList : String,
    private val onImageView5Click: (Int) -> Unit, // 点击回调
    private val onImageView48Click: (Int) -> Unit  ,// 点击回调
    private val ontextClick: (Int) -> Unit  // 点击回调
) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        // 如果没有数据，则返回 1（显示一个默认项）
        return 1
    }

    // 创建 ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_rec_equ, parent, false)
        return ViewHolder(view)
    }

    // 绑定数据到 ViewHolder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("适配器",dataList.toString())
        if (dataList.isNullOrEmpty() || dataList == null || dataList == "") {
            holder.bindNoData()  // 绑定没有数据的布局
        } else {
            holder.bindData(dataList, position)  // 绑定有数据的布局
        }
    }

    // ViewHolder
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: ImageView = itemView.findViewById(R.id.text)
        private val imageView5: ImageView = itemView.findViewById(R.id.imageView5)
        private val imageView48: ImageView = itemView.findViewById(R.id.imageView48)

        // 绑定没有数据时的视图
        fun bindNoData() {
            textView.visibility = View.GONE
            imageView5.visibility = View.VISIBLE
            imageView48.visibility = View.VISIBLE

            // 设置点击回调
            imageView5.setOnClickListener {
                onImageView5Click(adapterPosition)
            }
            imageView48.setOnClickListener {
                onImageView48Click(adapterPosition)
            }
        }

        // 绑定有数据时的视图
        fun bindData(dataList: String, position: Int) {
            textView.visibility = View.VISIBLE
            imageView5.visibility = View.GONE
            imageView48.visibility = View.GONE
            Log.e("适配器","刷新")
            Glide.with(itemView.context)
                .load(File(dataList))  // 加载图片
                .apply(RequestOptions().transform(RoundedCorners(16)))  // 设置圆角，16为圆角的半径
                .into(textView)  // 设置到 ImageView


            textView.setOnClickListener {
                ontextClick(position)
            }
        }
    }
}
