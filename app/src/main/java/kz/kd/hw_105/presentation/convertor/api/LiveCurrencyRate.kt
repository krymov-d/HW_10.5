package kz.kd.hw_105.presentation.convertor.api

import com.google.gson.annotations.SerializedName

data class LiveCurrencyRate(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("source")
    val source: String,
    @SerializedName("quotes")
    val quotes: Map<String, Double>,
)
