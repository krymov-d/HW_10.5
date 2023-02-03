package kz.kd.hw_105

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class FragmentAccount : Fragment(R.layout.fragment_account) {

    private lateinit var vpAccount2: ViewPager2
    private lateinit var vpTabLayout: TabLayout
    private lateinit var vpAccountAdapter2: FragmentStateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager(view)
    }

    private fun initViewPager(view: View) {
        vpAccount2 = view.findViewById(R.id.account_view_pager)
        vpAccountAdapter2 = AccountViewPager2Adapter(requireActivity())

        vpAccount2.adapter = vpAccountAdapter2

        vpTabLayout = view.findViewById(R.id.account_tab_layout)
        TabLayoutMediator(vpTabLayout, vpAccount2) { tab, position ->
            val tabNames = listOf("Основные", "Статистика", "Еще один таб")
            tab.text = tabNames[position]
        }.attach()
    }
}