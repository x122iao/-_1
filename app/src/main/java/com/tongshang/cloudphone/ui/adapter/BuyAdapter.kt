package com.tongshang.cloudphone.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tongshang.cloudphone.ui.view.fragment.*


/**
 * @author: z2660
 * @date: 2024/12/3
 */
class BuyAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FoundationFragment()
            1 -> StandardFragment()
            2 -> SeniorFragment()
            else -> throw IllegalArgumentException("Invalid position")
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}
