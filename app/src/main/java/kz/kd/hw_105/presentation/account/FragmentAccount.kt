package kz.kd.hw_105.presentation.account

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kz.kd.hw_105.R

class FragmentAccount : Fragment(R.layout.fragment_account) {

    private lateinit var vpAccount2: ViewPager2
    private lateinit var vpTabLayout: TabLayout
    private lateinit var vpAccountAdapter2: FragmentStateAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager(view)
        initNavigation(view)
    }

    private fun initNavigation(view: View) {
        view.findViewById<Button>(R.id.btn_account_to_search).setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_fragmentAccount_to_fragmentSearch)
        }
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