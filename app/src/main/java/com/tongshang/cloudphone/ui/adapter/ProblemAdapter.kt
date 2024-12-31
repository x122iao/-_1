package com.tongshang.cloudphone.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tongshang.cloudphone.R

class ProblemAdapter(private val problemList: List<String>) : RecyclerView.Adapter<ProblemAdapter.ProblemViewHolder>() {

    class ProblemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val problemTextView: TextView = itemView.findViewById(R.id.problemTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProblemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_problem, parent, false)
        return ProblemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProblemViewHolder, position: Int) {
        val problem = problemList[position]
        holder.problemTextView.text = problem
    }

    override fun getItemCount(): Int = problemList.size
}