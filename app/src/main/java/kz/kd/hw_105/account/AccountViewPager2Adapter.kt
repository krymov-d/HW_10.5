package kz.kd.hw_105.account

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AccountViewPager2Adapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FragmentAccountTabMain()
            1 -> FragmentAccountTabStatistics()
            else -> FragmentAccountTabAnother()
        }
    }
}