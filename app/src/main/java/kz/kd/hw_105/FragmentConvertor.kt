package kz.kd.hw_105

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentConvertor : Fragment(R.layout.fragment_convertor), IFAddCurrency,
    IFGetCurrencyPosToDelete, IFDeleteCurrency, IFBtnAddCurrency, IFSetCurrencyPosToDelete {

    private lateinit var tbSecondActivity: Toolbar
    private lateinit var tbConvertorDelete: Toolbar
    private lateinit var rvCurrency: RecyclerView
    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager

    private var currencyPosToDelete: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initConvertorMenu()
    }

    private fun initConvertorMenu() {
        setHasOptionsMenu(true)
        tbSecondActivity = requireActivity().findViewById(R.id.tb_second_activity)
        tbConvertorDelete = requireActivity().findViewById(R.id.tb_convertor_delete)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val convertorInflater: MenuInflater = requireActivity().menuInflater
        if (tbSecondActivity.isVisible) {
            convertorInflater.inflate(R.menu.menu_tb_convertor, menu)
        } else if (tbConvertorDelete.isVisible) {
            convertorInflater.inflate(R.menu.menu_tb_convertor_delete, menu)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_main_reset -> {
                currencyAdapter.reset()
                invalidateOptionsMenu(requireActivity())
                true
            }
            R.id.menu_main_sort_alpha -> {
                item.isChecked = !item.isChecked
                currencyAdapter.sortAlpha()
                true
            }
            R.id.menu_main_sort_num -> {
                item.isChecked = !item.isChecked
                currencyAdapter.sortNum()
                true
            }
            R.id.menu_delete -> {
                DFConvertorDelete().show(requireActivity().supportFragmentManager, null)
                tbConvertorDeleteChangeToConvertor(activity as SecondActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCurrencyRecycler(view)
        fillCurrency()
    }

    private fun initCurrencyRecycler(view: View) {
        rvCurrency = view.findViewById(R.id.rv_currency)
        currencyLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        currencyAdapter = CurrencyAdapter(layoutInflater, this, this)
        rvCurrency.adapter = currencyAdapter
        rvCurrency.layoutManager = currencyLayoutManager

        initCurrencyItemDecoration()
        initCurrencySwipeDrag()
    }

    private fun initCurrencyItemDecoration() {
        val dividerItemDecoration =
            DividerItemDecoration(context, currencyLayoutManager.orientation)
        dividerItemDecoration.setDrawable(
            context?.let {
                AppCompatResources.getDrawable(
                    it,
                    R.drawable.divider_bg
                )
            }!!
        )
        rvCurrency.addItemDecoration(dividerItemDecoration)
    }

    private fun initCurrencySwipeDrag() {
        val secondActivity = activity as SecondActivity
        val touchHelper =
            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    currencyAdapter.onMoveDrag(viewHolder.layoutPosition, target.layoutPosition)
                    tbConvertorDeleteChangeToConvertor(secondActivity)
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    currencyAdapter.deleteCurrencyAt(viewHolder.layoutPosition)
                    tbConvertorDeleteChangeToConvertor(secondActivity)
                }

                override fun isLongPressDragEnabled(): Boolean {
                    tbConvertorChangeToConvertorDelete(secondActivity)
                    return super.isLongPressDragEnabled()
                }
            })
        touchHelper.attachToRecyclerView(rvCurrency)
    }

    private fun tbConvertorChangeToConvertorDelete(activity: SecondActivity) {
        tbSecondActivity.isVisible = false
        tbConvertorDelete.isVisible = true
        activity.setSupportActionBar(tbConvertorDelete)
    }

    private fun tbConvertorDeleteChangeToConvertor(activity: SecondActivity) {
        tbSecondActivity.isVisible = true
        tbConvertorDelete.isVisible = false
    }

    private fun fillCurrency() {
        val currencyList = mutableListOf<Currency>()
        currencyList.add(
            Currency(
                amount = "1",
                flag = R.drawable.ic_kz,
                country = getString(R.string.kz),
                currencyName = getString(R.string.kz_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "2",
                flag = R.drawable.ic_usa,
                country = getString(R.string.usa),
                currencyName = getString(R.string.usa_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "3",
                flag = R.drawable.ic_tr,
                country = getString(R.string.tr),
                currencyName = getString(R.string.tr_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "4",
                flag = R.drawable.ic_eu,
                country = getString(R.string.eu),
                currencyName = getString(R.string.eu_currency)
            )
        )
        currencyAdapter.updateDataSet(currencyList)
    }

    override fun addCurrency(currency: Currency) {
        currencyAdapter.addNewCurrency(currency)
    }

    override fun getCurrencyPosToDelete(): Int {
//        return currencyAdapter.getCurrencyPosToDelete()
        return currencyPosToDelete
    }

    override fun deleteCurrencyAt(position: Int) {
        currencyAdapter.deleteCurrencyAt(position)
    }

    override fun btnAddCurrencyClicked() {
        BSDConvertor().show(childFragmentManager, null)
        currencyLayoutManager.scrollToPosition(currencyAdapter.itemCount)
    }

    override fun setCurrencyPosToDelete(position: Int) {
        currencyPosToDelete = position
    }
}