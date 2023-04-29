package kz.kd.hw_105.presentation.convertor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import kz.kd.hw_105.*
import kz.kd.hw_105.domain.models.Currency

class CurrencyAdapter(
    private val layoutInflater: LayoutInflater,
    private val listener: IFBtnAddCurrency,
    private val setCurrencyPositionToDelete: IFSetCurrencyPosToDelete,
    private val updateCurrencyList: IFUpdateCurrencyList,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var currencyList: MutableList<Currency> = mutableListOf()
    private var currencyListCopy: MutableList<Currency> = mutableListOf()
    private var sortType: Int = 0

    override fun getItemViewType(position: Int): Int {
        return if (position == currencyList.size) {
            1
        } else {
            0
        }

        //!!!!!!!!!!!!!!!!!!!!!!
        //val kztCurrency = currencyList.find { it.country == "KZT" }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutID: Int = if (viewType == 0) {
            R.layout.card_currency
        } else {
            R.layout.btn_add
        }
        val view = layoutInflater.inflate(layoutID, parent, false)

        return if (viewType == 0) {
            CurrencyViewHolder(view, updateCurrencyList)
        } else {
            BtnAddViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CurrencyViewHolder) {
            val currency = currencyList[position]
            holder.bind(currency)
            holder.itemView.setOnLongClickListener {
                setCurrencyPositionToDelete.setCurrencyPosToDelete(holder.layoutPosition)
                true
            }
        } else if (holder is BtnAddViewHolder) {
            val btnAdd: Button = holder.itemView.findViewById(R.id.btn_add)
            btnAdd.setOnClickListener {
                listener.btnAddCurrencyClicked()
            }
        }
    }

    override fun getItemCount(): Int {
        return currencyList.size + 1
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDataSet(newDataSet: List<Currency>) {
        currencyList.clear()
        currencyList.addAll(newDataSet)
        reserveCopy(currencyList)
        notifyDataSetChanged()
    }

    fun onMoveDrag(startPosition: Int, stopPosition: Int) {
        val temporaryCurrency: Currency = currencyList[stopPosition]
        currencyList[stopPosition] = currencyList[startPosition]
        currencyList[startPosition] = temporaryCurrency
        reserveCopy(currencyList)
        notifyItemMoved(startPosition, stopPosition)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteCurrencyAt(position: Int) {
        currencyList.removeAt(position)
        reserveCopy(currencyList)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, currencyList.size)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNewCurrency(currency: Currency) {
        val newCurrencyListSize = currencyList.size + 1
        currencyList.add(currency)
        reserveCopy(currencyList)
        when (sortType) {
            1 -> currencyList.sortBy { it.country }
            2 -> {
                currencyList.sortBy { it.amount }
                currencyList.reverse()
            }
        }
        val newPosition = currencyList.indexOf(currency)
        notifyItemInserted(newPosition)
        notifyItemRangeChanged(newPosition, newCurrencyListSize)
    }

    private fun reserveCopy(copyDataSet: List<Currency>) {
        currencyListCopy.clear()
        currencyListCopy.addAll(copyDataSet)
        currencyListCopy.sortBy { it.amount }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun reset() {
        sortType = 0
        currencyList.clear()
        currencyList.addAll(currencyListCopy)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortAlpha() {
        sortType = 1
        currencyList.sortBy { it.country }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortNum() {
        sortType = 2
        currencyList.sortBy { it.amount }
        currencyList.reverse()
        notifyDataSetChanged()
    }

    fun convertCurrencies(vararg rate: Double) {
        currencyList[1].amount = rate[0].toString()
        notifyItemChanged(1)
        currencyList[2].amount = rate[1].toString()
        notifyItemChanged(2)
        currencyList[3].amount = rate[2].toString()
        notifyItemChanged(3)
    }
}