package com.tongshang.cloudphone.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.data.model.Item

/**
 * @author: z2660
 * @date: 2024/12/16
 */
class StandAdapter(private val itemList: List<Item>) : RecyclerView.Adapter<StandAdapter.MyViewHolder>() {

    private var selectedPosition: Int = 0 // 默认选中第一个 item

    // 创建 ViewHolder
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView54)
        val cons: ConstraintLayout = itemView.findViewById(R.id.constraint) // 假设 itemLayout 是整个 item 的布局
        val text1: TextView = itemView.findViewById(R.id.textView1) // 假设 itemLayout 是整个 item 的布局
        val text2: TextView = itemView.findViewById(R.id.jiage) // 假设 itemLayout 是整个 item 的布局
    }

    // 创建视图并绑定数据
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.seni_item, parent, false)
        return MyViewHolder(view)
    }

    // 绑定数据
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemList[position]
        holder.textView.text = item.name
        holder.text1.setTextColor(Color.parseColor("#757575"));
        holder.text2.setTextColor(Color.parseColor("#333333"));
        // 根据是否选中，设置背景
        if (position == selectedPosition) {
            holder.cons.setBackgroundResource(R.drawable.selected_background_stand) // 选中时的背景
            holder.text2.setTextColor(Color.parseColor("#39B1FF"));
            holder.text1.setTextColor(Color.parseColor("#39B1FF"));


        } else {
            holder.cons.setBackgroundResource(R.drawable.unselected_background_stand) // 未选中时的背景
        }

        // 点击事件，更新选中项
        holder.cons.setOnClickListener {
            // 更新选中的位置
            val previousPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousPosition) // 通知之前的项重新绘制
            notifyItemChanged(selectedPosition) // 通知当前项重新绘制
        }
    }

    // 返回数据项的数量
    override fun getItemCount(): Int {
        return itemList.size
    }
}

