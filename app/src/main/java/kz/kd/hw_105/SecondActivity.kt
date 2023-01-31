package kz.kd.hw_105

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    private lateinit var tbChat: Toolbar
    private lateinit var tbFavorites: Toolbar
    private lateinit var tbConvertor: Toolbar
    private lateinit var tbConvertorDelete: Toolbar
    private lateinit var tbSearch: Toolbar
    private lateinit var tbAccount: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initToolBar()
        initBottomNavigationBar()
    }

    private fun initToolBar() {
        tbChat = findViewById(R.id.tb_chat)
        tbFavorites = findViewById(R.id.tb_favorites)
        tbConvertor = findViewById(R.id.tb_convertor)
        tbConvertorDelete = findViewById(R.id.tb_convertor_delete)
        tbSearch = findViewById(R.id.tb_search)
        tbAccount = findViewById(R.id.tb_account)
    }

    private fun initBottomNavigationBar() {
        val bnvSecondActivity: BottomNavigationView = findViewById(R.id.bnv_second_activity)
        bnvSecondActivity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bnv_chat -> {
                    tbChat.isVisible = true
                    tbFavorites.isVisible = false
                    tbConvertor.isVisible = false
                    tbConvertorDelete.isVisible = false
                    tbSearch.isVisible = false
                    tbAccount.isVisible = false
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentChat(), "Fragment Chat").commit()
                    true
                }
                R.id.menu_bnv_favorites -> {
                    tbChat.isVisible = false
                    tbFavorites.isVisible = true
                    tbConvertor.isVisible = false
                    tbConvertorDelete.isVisible = false
                    tbSearch.isVisible = false
                    tbAccount.isVisible = false
                    setSupportActionBar(tbFavorites)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentFavorites(), "Fragment Chat")
                        .commit()

                    true
                }
                R.id.menu_bnv_convertor -> {
                    tbChat.isVisible = false
                    tbFavorites.isVisible = false
                    tbConvertor.isVisible = true
                    tbConvertorDelete.isVisible = false
                    tbSearch.isVisible = false
                    tbAccount.isVisible = false
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentConvertor(), "Fragment Convertor")
                        .commit()
                    true
                }
                R.id.menu_bnv_search -> {
                    tbChat.isVisible = false
                    tbFavorites.isVisible = false
                    tbConvertor.isVisible = false
                    tbConvertorDelete.isVisible = false
                    tbSearch.isVisible = true
                    tbAccount.isVisible = false
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentSearch(), "Fragment Chat")
                        .commit()

                    true
                }
                R.id.menu_bnv_account -> {
                    tbChat.isVisible = false
                    tbFavorites.isVisible = false
                    tbConvertor.isVisible = false
                    tbConvertorDelete.isVisible = false
                    tbSearch.isVisible = false
                    tbAccount.isVisible = true
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