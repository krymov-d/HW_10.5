package kz.kd.hw_105.convertor

data class Currency(
    var amount: String = "",
    var flag: Int,
    var country: String,
    var currencyName: String
)