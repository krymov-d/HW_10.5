package kz.kd.hw_105.account

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kz.kd.hw_105.R

class AccountViewPagerAdapter : PagerAdapter() {

    private val layouts = listOf(
        R.layout.vp_account_tab_main,
        R.layout.vp_account_tab_statistics,
        R.layout.vp_account_tab_another
    )

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val layout = inflater.inflate(layouts[position], container, false) as ViewGroup
        container.addView(layout)
        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    override fun getCount(): Int {
        return layouts.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "Основные"
            }
            1 -> {
                "Статистика"
            }
            2 -> {
                "Еще один таб"
            }
            else -> {
                null
            }
        }
    }
}