package com.tongshang.cloudphone.ui.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tongshang.cloudphone.R

class ProblemFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var problemAdapter: ProblemAdapter
    private var problemList: List<String>? = null

    companion object {
        private const val ARG_PROBLEM_LIST = "problem_list"

        // 使用 newInstance 方法来传递集合数据
        fun newInstance(problemList: List<String>): ProblemFragment {
            val fragment = ProblemFragment()
            val args = Bundle()
            args.putStringArrayList(ARG_PROBLEM_LIST, ArrayList(problemList))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_problem, container, false)

        // 从 Bundle 中获取传递的集合
        problemList = arguments?.getStringArrayList(ARG_PROBLEM_LIST)

        // 初始化 RecyclerView 和适配器
        recyclerView = rootView.findViewById(R.id.recy)
        recyclerView.layoutManager = LinearLayoutManager(context)

        // 设置适配器
        problemAdapter = ProblemAdapter(problemList ?: emptyList())
        recyclerView.adapter = problemAdapter

        return rootView
    }
}