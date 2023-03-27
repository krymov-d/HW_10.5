package kz.kd.hw_105.presentation.convertor.api

import com.google.gson.annotations.SerializedName

data class ResponseList(
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("currencies")
    val currencies: Map<String, String>,
)
