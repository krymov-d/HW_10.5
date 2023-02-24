package kz.kd.hw_105.convertor.api

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

private const val API_KEY = "zcYKdMcIYR0KFKjbRZNU4aGZIgraz53q"

interface IFCurrencyAPI {
    @Headers("apikey: $API_KEY")
    @GET("live")
    suspend fun getCurrencyExchangeRate(
        @Query("source") source: String,
        @Query("currencies") currencies: String
    ): LiveCurrencyRate
}