package kz.kd.hw_105

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.kd.hw_105.account.FragmentAccount
import kz.kd.hw_105.convertor.FragmentConvertor
import kz.kd.hw_105.favorites.FragmentFavorites

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    private lateinit var tbSecondActivity: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolBar()
        initBottomNavigationBar()
    }

    private fun initToolBar() {
        tbSecondActivity = findViewById(R.id.tb_second_activity)
        setSupportActionBar(tbSecondActivity)
    }

    private fun initBottomNavigationBar() {
        val bnvSecondActivity: BottomNavigationView = findViewById(R.id.bnv_second_activity)
        bnvSecondActivity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bnv_chat -> {
                    tbSecondActivity.title = "Chat"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentChat(), "Fragment Chat")
                        .commit()
                    true
                }
                R.id.menu_bnv_favorites -> {
                    tbSecondActivity.title = "Qyzyqty kontent"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentFavorites(), "Fragment Favorites")
                        .commit()
                    true
                }
                R.id.menu_bnv_convertor -> {
                    tbSecondActivity.title = "Convertor"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentConvertor(), "Fragment Convertor")
                        .commit()
                    true
                }
                R.id.menu_bnv_search -> {
                    tbSecondActivity.title = "Search"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentSearch(), "Fragment Search")
                        .commit()
                    true
                }
                R.id.menu_bnv_account -> {
                    tbSecondActivity.title = "Account"
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentAccount(), "Fragment Account")
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}