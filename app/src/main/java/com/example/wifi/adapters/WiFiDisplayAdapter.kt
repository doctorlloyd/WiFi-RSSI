package com.example.wifi.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.wifi.DisplayScreen
import com.example.wifi.R
import com.example.wifi.models.ViewModel
import com.example.wifi.models.WiFi

class WiFiDisplayAdapter (private val context: Context, private val WIFIs: List<WiFi>) :
    RecyclerView.Adapter<WiFiDisplayAdapter.ViewHolder>() {
    private val model = ViewModel(context)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var wifiName: TextView = itemView.findViewById(R.id.wifi_name)
        var wifiAddress: TextView = itemView.findViewById(R.id.wifi_address)
        var wifiCapabilities: TextView = itemView.findViewById(R.id.wifi_capabilities)
        var wifiStrength: TextView = itemView.findViewById(R.id.wifi_strength)
        var wifiVenueName: TextView = itemView.findViewById(R.id.wifi_venueName)
        var wifiCardView: CardView = itemView.findViewById(R.id.wifi_card_view)
        var wifiButton: Button = itemView.findViewById(R.id.submit_wifi_info)

        init {
            wifiButton.setOnClickListener {
                val wyfy:WiFi = WIFIs[adapterPosition]
                model.postWifi(wyfy)
            }
            wifiCardView.setOnClickListener { view: View ->
                val intent = Intent(context, DisplayScreen::class.java)
                intent.putExtra("selected", WIFIs[adapterPosition])
                view.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row, viewGroup, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val wifi = WIFIs[position]
        viewHolder.wifiName.text = wifi.name
        viewHolder.wifiAddress.text = wifi.address
        viewHolder.wifiCapabilities.text = wifi.capabilities
        viewHolder.wifiStrength.text = model.signalCheck(wifi.strength)
        viewHolder.wifiVenueName.text = wifi.venueName
    }

    override fun getItemCount(): Int {
        return WIFIs.size
    }


}