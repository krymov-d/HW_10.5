package kz.kd.hw_105.convertor.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CurrencyRetrofitBuilder {
    private const val BASE_URL = "https://api.apilayer.com/currency_data"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val currencyAPIService: IFCurrencyAPI = getRetrofit().create(IFCurrencyAPI::class.java)
}