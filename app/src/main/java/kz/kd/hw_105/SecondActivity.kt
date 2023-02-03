package kz.kd.hw_105

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
        setToolBarVisibility(0)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        if (tbConvertor.isVisible) {
            inflater.inflate(R.menu.menu_tb_convertor, menu)
        } else if (tbConvertorDelete.isVisible) {
            inflater.inflate(R.menu.menu_tb_convertor_delete, menu)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_reset -> {
//                currencyAdapter.reset()
                invalidateOptionsMenu()
                true
            }
            R.id.menu_main_sort_alpha -> {
                item.isChecked = !item.isChecked
//                currencyAdapter.sortAlpha()
                true
            }
            R.id.menu_main_sort_num -> {
                item.isChecked = !item.isChecked
//                currencyAdapter.sortNum()
                true
            }
            R.id.menu_delete -> {
//                DFDelete().show(supportFragmentManager, null)
                DFConvertorDelete().show(supportFragmentManager, null)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initBottomNavigationBar() {
        val bnvSecondActivity: BottomNavigationView = findViewById(R.id.bnv_second_activity)
        bnvSecondActivity.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_bnv_chat -> {
                    setToolBarVisibility(1)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentChat(), "Fragment Chat")
                        .commit()
                    true
                }
                R.id.menu_bnv_favorites -> {
                    setToolBarVisibility(2)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentFavorites(), "Fragment Favorites")
                        .commit()
                    true
                }
                R.id.menu_bnv_convertor -> {
                    setToolBarVisibility(3)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentConvertor(), "Fragment Convertor")
                        .commit()
                    true
                }
                R.id.menu_bnv_search -> {
                    setToolBarVisibility(4)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentSearch(), "Fragment Search")
                        .commit()
                    true
                }
                R.id.menu_bnv_account -> {
                    setToolBarVisibility(5)
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fl_second_activity, FragmentAccount(), "Fragment Account")
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

    private fun setToolBarVisibility(type: Int) {
        when (type) {
            0 -> {
                tbChat.isVisible = false
                tbFavorites.isVisible = false
                tbConvertor.isVisible = false
                tbConvertorDelete.isVisible = false
                tbSearch.isVisible = false
                tbAccount.isVisible = false
            }
            1 -> {
                tbChat.isVisible = true
                tbFavorites.isVisible = false
                tbConvertor.isVisible = false
                tbConvertorDelete.isVisible = false
                tbSearch.isVisible = false
                tbAccount.isVisible = false
                setSupportActionBar(tbChat)
            }
            2 -> {
                tbChat.isVisible = false
                tbFavorites.isVisible = true
                tbConvertor.isVisible = false
                tbConvertorDelete.isVisible = false
                tbSearch.isVisible = false
                tbAccount.isVisible = false
                setSupportActionBar(tbFavorites)
            }
            3 -> {
                tbChat.isVisible = false
                tbFavorites.isVisible = false
                tbConvertor.isVisible = true
                tbConvertorDelete.isVisible = false
                tbSearch.isVisible = false
                tbAccount.isVisible = false
                setSupportActionBar(tbConvertor)
                tbConvertor.overflowIcon?.setTint(resources.getColor(R.color.btn_text_color))
            }
            4 -> {
                tbChat.isVisible = false
                tbFavorites.isVisible = false
                tbConvertor.isVisible = false
                tbConvertorDelete.isVisible = false
                tbSearch.isVisible = true
                tbAccount.isVisible = false
                setSupportActionBar(tbSearch)
            }
            5 -> {
                tbChat.isVisible = false
                tbFavorites.isVisible = false
                tbConvertor.isVisible = false
                tbConvertorDelete.isVisible = false
                tbSearch.isVisible = false
                tbAccount.isVisible = true
                setSupportActionBar(tbAccount)
            }
        }
    }

    fun tbConvertorChangeToConvertorDelete() {
        tbConvertor.isVisible = false
        tbConvertorDelete.isVisible = true
        setSupportActionBar(tbConvertorDelete)
    }

    fun tbConvertorDeleteChangeToConvertor() {
        tbConvertor.isVisible = true
        tbConvertorDelete.isVisible = false
    }

    override fun onBackPressed() {
        tbConvertorDeleteChangeToConvertor()
        super.onBackPressed()
    }
}