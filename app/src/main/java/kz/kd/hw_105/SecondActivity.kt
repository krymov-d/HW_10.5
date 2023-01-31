package kz.kd.hw_105

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity(R.layout.activity_second) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initBottomNavigationBar()
    }

    private fun initBottomNavigationBar() {
        val bnvSecondActivity: BottomNavigationView = findViewById(R.id.bnv_second_activity)
        bnvSecondActivity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bnv_chat -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentChat(), "Fragment Chat").commit()
                    true
                }
                R.id.menu_bnv_favorites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentFavorites(), "Fragment Chat")
                        .commit()

                    true
                }
                R.id.menu_bnv_convertor -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentConvertor(), "Fragment Convertor")
                        .commit()
                    true
                }
                R.id.menu_bnv_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentSearch(), "Fragment Chat")
                        .commit()

                    true
                }
                R.id.menu_bnv_account -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentAccount(), "Fragment Chat")
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}