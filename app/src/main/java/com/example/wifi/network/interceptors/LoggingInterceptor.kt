import android.content.ContentValues.TAG
import android.util.Log
import com.example.wifi.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.text.DecimalFormat


class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (BuildConfig.DEBUG) {
            val t1 = System.nanoTime()
            Log.d(TAG, "intercept: [" + request.method().toUpperCase() + "] " + request.url().toString())
            Log.d(
                TAG, "intercept: " + String.format(
                    "Sending request %s on %s",
                    request.url(), request.headers()
                )
            )
            val response = chain.proceed(request)

            val t2 = System.nanoTime()
            Log.d(
                TAG, "intercept: " + ">>> Response: " + "[" + response.request().method().toUpperCase() + "] "
                        + response.request().url().toString()
            )
            Log.d(TAG, "intercept: " + "Request Body: " + response.request().body())
            Log.d(TAG, "intercept: " + "Request Headers: " + response.request().headers())
            Log.d(TAG, "intercept: " + "Time Elapsed: " + DecimalFormat("#.00").format((t2 - t1) / 1e9) + " seconds")
            Log.d(TAG, "intercept: " + "Response Headers: " + response.headers())
//            Log.d(TAG, "intercept: " + HttpStatus().getStatusMessage(response.code()) + " [" + response.code() + "]")
            return response
        }
        return chain.proceed(request)
    }

}