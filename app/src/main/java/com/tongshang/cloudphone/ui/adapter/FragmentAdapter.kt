package com.tongshang.cloudphone.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tongshang.cloudphone.ui.view.fragment.EquipmentFragment
import com.tongshang.cloudphone.ui.view.fragment.InformationFragment
import com.tongshang.cloudphone.ui.view.fragment.MyFragment


/**
 * @author: z2660
 * @date: 2024/12/3
 */
class FragmentAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InformationFragment()
            1 -> EquipmentFragment()
            2 -> MyFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}
