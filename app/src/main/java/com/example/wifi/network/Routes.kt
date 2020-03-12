package com.example.wifi.network

import com.example.wifi.models.WiFi
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface Routes {

    @POST("wifi/")
    fun postWifi(
        @HeaderMap headers: Map<String, String>,
        @Body wifi: WiFi
    ):Call<ResponseBody>

    @POST("wifi-list/")
    fun postWifiList(
        @HeaderMap headers: Map<String, String>,
        @Body wifi: List<WiFi>
    ):Call<ResponseBody>
}