package kz.kd.hw_105.presentation.convertor

import kz.kd.hw_105.domain.models.Currency

interface IFAddCurrency {
    fun addCurrency(currency: Currency)
}