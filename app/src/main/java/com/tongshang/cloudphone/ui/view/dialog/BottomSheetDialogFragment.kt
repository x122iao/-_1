package com.tongshang.cloudphone.ui.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.tongshang.cloudphone.R
import com.tongshang.cloudphone.ui.view.activity.RenewalActivity

class BottomSheetFragment : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 返回弹窗的布局
        val view = inflater.inflate(R.layout.dialog_bottom_sheet, container, false)
        val bottom = view.findViewById<Button>(R.id.bottom)


        bottom.setOnClickListener {
            startActivity(Intent(requireContext(), RenewalActivity::class.java))
        }
        // 你可以设置自定义内容
        return view
    }


}

