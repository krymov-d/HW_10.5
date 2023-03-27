package kz.kd.hw_105.data

import kz.kd.hw_105.constants.API_KEY
import kz.kd.hw_105.presentation.convertor.api.LiveCurrencyRate
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MainApi {

    @Headers("apikey: $API_KEY")
    @GET("live")
    suspend fun getCurrencyExchangeRate(
        @Query("source") source: String,
        @Query("currencies") currencies: String
    ): LiveCurrencyRate
}