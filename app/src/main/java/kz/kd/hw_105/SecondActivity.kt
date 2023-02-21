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
        bnvSecondActivity.selectedItemId = R.id.fragmentConvertor
//        bnvSecondActivity.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.fragmentChat -> {
//                    tbSecondActivity.title = "Chat"
//                    true
//                }
//                R.id.fragmentFavorites -> {
//                    tbSecondActivity.title = "Qyzyqty kontent"
//                    true
//                }
//                R.id.fragmentConvertor -> {
//                    tbSecondActivity.title = "Convertor"
//                    true
//                }
//                R.id.fragmentSearch -> {
//                    tbSecondActivity.title = "Search"
//                    true
//                }
//                R.id.fragmentAccount -> {
//                    tbSecondActivity.title = "Account"
//                    true
//                }
//                else -> false
//            }
//        }
    }
}