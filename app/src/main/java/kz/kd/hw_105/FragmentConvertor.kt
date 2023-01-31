package kz.kd.hw_105

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentConvertor : Fragment(R.layout.fragment_convertor) {

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var currencyLayoutManager: LinearLayoutManager
    private lateinit var rvCurrency: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvCurrency = view.findViewById(R.id.rv_currency)
        initCurrencyRecycler()
    }

    private fun initCurrencyRecycler() {
        currencyAdapter =
            CurrencyAdapter(layoutInflater, object : CurrencyAdapter.BtnAddClickListener {
                override fun bntAddClicked() {
                    currencyLayoutManager.scrollToPosition(currencyAdapter.itemCount)
                }
            })
        currencyLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rvCurrency.layoutManager = currencyLayoutManager
        rvCurrency.adapter = currencyAdapter
    }
}