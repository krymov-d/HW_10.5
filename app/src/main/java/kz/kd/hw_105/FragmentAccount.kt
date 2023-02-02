package kz.kd.hw_105

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class FragmentAccount : Fragment(R.layout.fragment_account) {

    private lateinit var vpAccount: ViewPager
    private lateinit var vpTabLayout: TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager(view)
    }

    private fun initViewPager(view: View) {
        vpAccount = view.findViewById(R.id.account_view_pager)
        val vpAccountAdapter = AccountViewPagerAdapter()
        vpAccount.adapter = vpAccountAdapter
        vpAccount.currentItem = 0
//        vpTabLayout.setupWithViewPager(vpAccount)
    }
}