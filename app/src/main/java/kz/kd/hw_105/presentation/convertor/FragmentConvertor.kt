package kz.kd.hw_105.presentation.convertor

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kz.kd.hw_105.R
import kz.kd.hw_105.SecondActivity
import kz.kd.hw_105.domain.models.Currency
import kz.kd.hw_105.presentation.convertor.api.CurrencyRetrofitBuilder
import kz.kd.hw_105.presentation.convertor.api.LiveCurrencyRate

class FragmentConvertor : Fragment(), IFAddCurrency, IFDeleteCurrency,
    IFBtnAddCurrency, IFSetCurrencyPosToDelete, IFUpdateCurrencyList {

    private lateinit var tbSecondActivity: Toolbar
    private lateinit var tbConvertorDelete: Toolbar
    private lateinit var rvCurrency: RecyclerView
    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager
    private var currencyList = mutableListOf<Currency>()
    private var currencyPosToDelete: Int = 0
    private lateinit var currencyRate: LiveCurrencyRate
    private val convertorThread = ConvertorThread()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_convertor, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestCurrencyApi()
        initConvertorMenu()
        initCurrencyAdapter()
        fillCurrency()
    }

    private fun requestCurrencyApi() {
        MainScope().launch(Dispatchers.IO) {
            currencyRate =
                CurrencyRetrofitBuilder.currencyAPIService.getCurrencyExchangeRate(
                    "KZT",
                    "USD,TRY,EUR,RUB"
                )
        }
//        GlobalScope.launch(Dispatchers.Default) {
//            currencyRate =
//                CurrencyRetrofitBuilder.currencyAPIService.getCurrencyExchangeRate(
//                    "KZT",
//                    "USD,TRY,EUR,RUB"
//                )
//        }
    }

    private fun initConvertorMenu() {
        setHasOptionsMenu(true)
        tbSecondActivity = requireActivity().findViewById(R.id.tb_second_activity)
        tbConvertorDelete = requireActivity().findViewById(R.id.tb_convertor_delete)
    }

    private fun initCurrencyAdapter() {
        currencyAdapter = CurrencyAdapter(layoutInflater, this, this, this)
    }

    private fun fillCurrency() {
        currencyList.add(
            Currency(
                amount = "",
                flag = R.drawable.ic_kz,
                country = getString(R.string.kz),
                currencyName = getString(R.string.kz_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "",
                flag = R.drawable.ic_usa,
                country = getString(R.string.usa),
                currencyName = getString(R.string.usa_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "",
                flag = R.drawable.ic_tr,
                country = getString(R.string.tr),
                currencyName = getString(R.string.tr_currency)
            )
        )
        currencyList.add(
            Currency(
                amount = "",
                flag = R.drawable.ic_eu,
                country = getString(R.string.eu),
                currencyName = getString(R.string.eu_currency)
            )
        )
        currencyAdapter.updateDataSet(currencyList)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCurrencyRecycler(view)
        initNavigation(view)
    }

    private fun initCurrencyRecycler(view: View) {
        rvCurrency = view.findViewById(R.id.rv_currency)
        currencyLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvCurrency.adapter =
            currencyAdapter //Recycler View Adapter initialization was moved to method onCreate()
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

    private fun initNavigation(view: View) {
        view.findViewById<Button>(R.id.btn_convertor_to_search).setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_fragmentConvertor_to_fragmentSearch)
        }
        view.findViewById<Button>(R.id.btn_convertor_to_favorites).setOnClickListener {
            NavHostFragment.findNavController(this)
                .navigate(R.id.action_fragmentConvertor_to_fragmentFavorites)
        }
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
            R.id.menu_main_update -> {
                requestCurrencyApi()
                true
            }
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
                DFConvertorDelete(this).show(requireActivity().supportFragmentManager, null)
                tbConvertorDeleteChangeToConvertor(activity as SecondActivity)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    override fun addCurrency(currency: Currency) {
        requestCurrencyApi()
        currencyAdapter.addNewCurrency(currency)
    }

    override fun deleteCurrencyAt() {
        currencyAdapter.deleteCurrencyAt(currencyPosToDelete)
    }

    override fun btnAddCurrencyClicked() {
        //BSDConvertor().show(childFragmentManager, null)
        BSDConvertorFlags().show(childFragmentManager, null)
        currencyLayoutManager.scrollToPosition(currencyAdapter.itemCount)
    }

    override fun setCurrencyPosToDelete(position: Int) {
        currencyPosToDelete = position
    }

    private var rateKZTUSD = 0.0
    private var rateKZTTRY = 0.0
    private var rateKZTEUR = 0.0
    private var rateKZTRUS = 0.0

    //    private val handlerConvertorMain = object : Handler(Looper.getMainLooper()) {
//        override fun handleMessage(msg: Message) {
//            super.handleMessage(msg)
//        }
//    }
    override fun updateCurrencyList(countryName: String, currencyAmount: Double) {
        when (countryName) {
            resources.getString(R.string.kz) -> {
                rateKZTUSD = currencyAmount * currencyRate.quotes["KZTUSD"]!!
                rateKZTTRY = currencyAmount * currencyRate.quotes["KZTTRY"]!!
                rateKZTEUR = currencyAmount * currencyRate.quotes["KZTEUR"]!!
                rateKZTRUS = currencyAmount * currencyRate.quotes["KZTRUB"]!!
                currencyAdapter.convertCurrencies(rateKZTUSD, rateKZTTRY, rateKZTEUR, rateKZTRUS)
            }
//            resources.getString(R.string.usa) -> {
//                rateKZTUSD = currencyAmount / currencyRate.quotes["KZTUSD"]!!
//                rateKZTTRY = rateKZTUSD * currencyRate.quotes["KZTTRY"]!!
//                rateKZTEUR = rateKZTUSD * currencyRate.quotes["KZTEUR"]!!
//                rateKZTRUS = rateKZTUSD * currencyRate.quotes["KZTRUS"]!!
//            }
//            resources.getString(R.string.tr) -> {
//                rateKZTTRY = currencyAmount / currencyRate.quotes["KZTTRY"]!!
//                rateKZTUSD = rateKZTTRY * currencyRate.quotes["KZTUSD"]!!
//                rateKZTEUR = rateKZTTRY * currencyRate.quotes["KZTEUR"]!!
//                rateKZTRUS = rateKZTTRY * currencyRate.quotes["KZTRUS"]!!
//            }
//            resources.getString(R.string.eu) -> {
//                rateKZTEUR = currencyAmount / currencyRate.quotes["KZTEUR"]!!
//                rateKZTUSD = rateKZTEUR * currencyRate.quotes["KZTUSD"]!!
//                rateKZTTRY = rateKZTEUR * currencyRate.quotes["KZTTRY"]!!
//                rateKZTRUS = rateKZTEUR * currencyRate.quotes["KZTRUS"]!!
//            }
//            resources.getString(R.string.rus) -> {
//                rateKZTRUS = currencyAmount / currencyRate.quotes["KZTRUS"]!!
//                rateKZTUSD = rateKZTRUS * currencyRate.quotes["KZTUSD"]!!
//                rateKZTTRY = rateKZTRUS * currencyRate.quotes["KZTTRY"]!!
//                rateKZTEUR = rateKZTRUS * currencyRate.quotes["KZTEUR"]!!
//            }
        }
    }
}