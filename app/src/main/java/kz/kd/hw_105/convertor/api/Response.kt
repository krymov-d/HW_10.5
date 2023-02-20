package kz.kd.hw_105.convertor.api

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("timestamp")
    val timestamp: Long,
    @SerializedName("source")
    val source: String,
    @SerializedName("quotes")
    val quotes: Map<String, Double>,
)
