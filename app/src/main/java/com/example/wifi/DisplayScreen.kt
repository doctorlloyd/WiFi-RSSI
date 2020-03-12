package com.example.wifi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.wifi.models.ViewModel
import com.example.wifi.models.WiFi
import kotlinx.android.synthetic.main.activity_display_screen.*
import java.lang.NullPointerException

class DisplayScreen : AppCompatActivity() {
    lateinit var model: ViewModel
    lateinit var selected: WiFi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_screen)
        model = ViewModel(this)
        try {
            selected = intent.getSerializableExtra("selected") as WiFi
            wifi_name.text = selected.name
            wifi_address.text = selected.address
            wifi_capabilities.text = selected.capabilities
            wifi_strength.text = model.signalCheck(selected.strength)
            wifi_venueName.text = selected.venueName
            setImage((selected.strength))
        } catch (e: NullPointerException) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

        submit_wifi_info.setOnClickListener {
            model.postWifi(selected)
        }
    }

    fun setImage(level:Int){
        if (level <= 0 && level >= -50) {
            signal_image.setImageResource(R.mipmap.best_signal)
        } else if (level < -50 && level >= -70) {
            signal_image.setImageResource(R.mipmap.good_signal)
        } else if (level < -70 && level >= -80) {
            signal_image.setImageResource(R.mipmap.low_signal)
        } else if (level < -80 && level >= -100) {
            signal_image.setImageResource(R.mipmap.weak_signal)
        }else{
            signal_image.setImageResource(R.mipmap.no_signal)
        }
    }
}
