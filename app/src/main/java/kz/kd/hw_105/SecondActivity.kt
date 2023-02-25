package kz.kd.hw_105

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

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
        //tbSecondActivity.setupWithNavController(navControllerSecondActivity)
        bnvSecondActivity.selectedItemId = R.id.fragmentConvertor
//        bnvSecondActivity.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.fragmentChat -> {
//                    tbSecondActivity.title = resources.getString(R.string.tb_chat)
//                    true
//                }
//                R.id.fragmentFavorites -> {
//                    tbSecondActivity.title = resources.getString(R.string.tb_favorites_kz)
//                    true
//                }
//                R.id.fragmentConvertor -> {
//                    tbSecondActivity.title = resources.getString(R.string.tb_convertor)
//                    true
//                }
//                R.id.fragmentSearch -> {
//                    tbSecondActivity.title = resources.getString(R.string.tb_search)
//                    true
//                }
//                R.id.fragmentAccount -> {
//                    tbSecondActivity.title = resources.getString(R.string.tb_account)
//                    true
//                }
//                else -> false
//            }
//        }
    }
}