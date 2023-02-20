package kz.kd.hw_105.convertor.api

import retrofit2.http.GET
import retrofit2.http.Headers

interface IFCurrencyAPI {
    @Headers("apikey: " + "zcYKdMcIYR0KFKjbRZNU4aGZIgraz53q")
    @GET("live?source={KZT}&currencies={USD}")
    suspend fun getCurrencyList(): List<Response>
}