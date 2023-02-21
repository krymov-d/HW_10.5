package kz.kd.hw_105

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kz.kd.hw_105.account.FragmentAccount
import kz.kd.hw_105.convertor.FragmentConvertor
import kz.kd.hw_105.favorites.FragmentFavorites

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    private lateinit var tbSecondActivity: Toolbar
    private lateinit var navHostFragmentSecondActivity: NavHostFragment
    private lateinit var navControllerSecondActivity: NavController
    private lateinit var bnvSecondActivity: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolBar()
        initNavigationHost()
        initBottomNavigationBar()
    }

    private fun initToolBar() {
        tbSecondActivity = findViewById(R.id.tb_second_activity)
        setSupportActionBar(tbSecondActivity)
    }

    private fun initNavigationHost() {
        navHostFragmentSecondActivity =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navControllerSecondActivity = navHostFragmentSecondActivity.navController
    }

    private fun initBottomNavigationBar() {
        bnvSecondActivity = findViewById(R.id.bnv_second_activity)
        bnvSecondActivity.setupWithNavController(navControllerSecondActivity)
        bnvSecondActivity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bnv_chat -> {
                    tbSecondActivity.title = "Chat"
                    navControllerSecondActivity.navigate(R.id.fragmentChat)
                    true
                }
                R.id.menu_bnv_favorites -> {
                    tbSecondActivity.title = "Qyzyqty kontent"
                    navControllerSecondActivity.navigate(R.id.fragmentFavorites)
                    true
                }
                R.id.menu_bnv_convertor -> {
                    tbSecondActivity.title = "Convertor"
                    navControllerSecondActivity.navigate(R.id.fragmentConvertor)
                    true
                }
                R.id.menu_bnv_search -> {
                    tbSecondActivity.title = "Search"
                    navControllerSecondActivity.navigate(R.id.fragmentSearch)
                    true
                }
                R.id.menu_bnv_account -> {
                    tbSecondActivity.title = "Account"
                    navControllerSecondActivity.navigate(R.id.fragmentAccount)
                    true
                }
                else -> false
            }
        }
    }
}