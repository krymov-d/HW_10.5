package kz.kd.hw_105

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class AccountViewPager2Adapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val listOfFragments = mutableListOf<Fragment>()

    init {
        listOfFragments.add(FragmentAccountTabMain.getFragment())
        listOfFragments.add(FragmentAccountTabStatistics.getFragment())
        listOfFragments.add(FragmentAccountTabAnother.getFragment())
    }

    override fun getItemCount(): Int {
        return listOfFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return listOfFragments[position]
    }
}