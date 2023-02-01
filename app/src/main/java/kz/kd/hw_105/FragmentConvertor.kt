package kz.kd.hw_105

import android.os.Bundle
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val KEY_VISIBLE_ITEM = "Visible Item"
private const val KEY_COMPLETELY_VISIBLE_ITEM = "Completely Visible Item"

class FragmentConvertor : Fragment(R.layout.fragment_convertor), NewCurrencyAdd {

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager
    private lateinit var rvCurrency: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Does not work
        if (savedInstanceState != null) {
            val firstVisibleItem = savedInstanceState.getInt(KEY_VISIBLE_ITEM)
            val firstCompletelyVisibleItem = savedInstanceState.getInt(KEY_COMPLETELY_VISIBLE_ITEM)
            currencyLayoutManager.scrollToPositionWithOffset(
                firstVisibleItem,
                firstCompletelyVisibleItem
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCurrency = view.findViewById(R.id.rv_currency)
        initCurrencyRecycler()
        fillCurrency()
    }

    //Does not work
    override fun onSaveInstanceState(outState: Bundle) {
        val firstVisibleItem = currencyLayoutManager.findFirstVisibleItemPosition()
        val firstCompletelyVisibleItem =
            currencyLayoutManager.findFirstCompletelyVisibleItemPosition()
        outState.putInt(KEY_VISIBLE_ITEM, firstVisibleItem)
        outState.putInt(KEY_COMPLETELY_VISIBLE_ITEM, firstCompletelyVisibleItem)
        super.onSaveInstanceState(outState)
    }

    private fun initCurrencyRecycler() {
        currencyAdapter =
            CurrencyAdapter(layoutInflater, object : CurrencyAdapter.BtnAddClickListener {
                override fun bntAddClicked() {
                    currencyLayoutManager.scrollToPosition(currencyAdapter.itemCount)
                    BSDConvertor().show(childFragmentManager, null)
                }
            })
        currencyLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvCurrency.layoutManager = currencyLayoutManager
        rvCurrency.adapter = currencyAdapter

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

        swipeCurrency()
    }

    private fun swipeCurrency() {
        val secondActivity = activity as SecondActivity?
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
                    secondActivity?.tbConvertorDeleteChangeToConvertor()
                    return true
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    currencyAdapter.onSwipeDelete(viewHolder.layoutPosition)
                    secondActivity?.tbConvertorDeleteChangeToConvertor()
                }

                override fun isLongPressDragEnabled(): Boolean {
                    (activity as SecondActivity?)?.tbConvertorChangeToConvertorDelete()
                    return super.isLongPressDragEnabled()
                }
            })
        touchHelper.attachToRecyclerView(rvCurrency)
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

    override fun setNewCurrency(currency: Currency) {
    }
}