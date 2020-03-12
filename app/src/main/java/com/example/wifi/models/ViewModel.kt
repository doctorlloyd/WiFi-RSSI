package com.example.wifi.models

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.example.wifi.network.ServiceManager
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class ViewModel(context: Context) {
    private var routes = ServiceManager(context)

    fun signalCheck(level: Int): String {
        var signalString = "No signals"
        if (level <= 0 && level >= -50) {
            signalString = "Best signal"
        } else if (level < -50 && level >= -70) {
            signalString = "Good signal"
        } else if (level < -70 && level >= -80) {
            signalString = "Low signal"
        } else if (level < -80 && level >= -100) {
            signalString = "Very weak signal"
        }
        return signalString
    }

    fun postWifi(wifi:WiFi) {
        routes.postWifi(wifi).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val restResponse = response.body()
                    //TODO with successful response
                } else {
                    //TODO with failed request
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //TODO with Exception
            }
        })
    }

    fun postWifiList(wifi:List<WiFi>) {
        routes.postWifiList(wifi).enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: retrofit2.Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val restResponse = response.body()
                    //TODO with successful response
                } else {
                    //TODO with failed request
                }
            }
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                //TODO with Exception
            }
        })
    }
}