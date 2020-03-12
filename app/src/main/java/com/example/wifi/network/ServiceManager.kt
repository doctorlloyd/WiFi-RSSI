package com.example.wifi.network

import LoggingInterceptor
import android.content.Context
import com.example.wifi.models.WiFi
import com.github.simonpercic.oklog3.OkLogInterceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceManager (private var context: Context) {
    private val headers = HashMap<String, String>()
    private val uriPattern = "https://lloyd.co.za/api/"

    private fun getHttpClient(): OkHttpClient {
        return getHttpClientBuilder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()
    }

    private fun getHttpClientBuilder(): OkHttpClient.Builder {
        val httpClientBuilder = OkHttpClient.Builder()

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        httpClientBuilder.addInterceptor(LoggingInterceptor())
        httpClientBuilder.addInterceptor(logging.setLevel(HttpLoggingInterceptor.Level.BODY))
        httpClientBuilder.addInterceptor(
            OkLogInterceptor.builder()
                .withRequestHeaders(true)
                .withRequestBody(true)
                .withRequestBodyState(true)
                .withResponseHeaders(true)
                .withResponseBodyState(true)
                .withRequestContentType(true)
                .withRequestContentLength(true)
                .withResponseMessage(true)
                .withAllLogData()
                .build()
        )
        return httpClientBuilder
    }

    private fun getService(): Routes {
        return getRetrofit().create(Routes::class.java)
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(uriPattern)
            .client(getHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun setAuthToken(_token: String){
        headers["Accept"] = "application/json"
        headers["Authorization"] = "JWT $_token"
    }

    fun postWifi(wifi: WiFi): Call<ResponseBody> {
        return getService().postWifi(headers, wifi)
    }

    fun postWifiList(wifi: List<WiFi>): Call<ResponseBody> {
        return getService().postWifiList(headers, wifi)
    }
}