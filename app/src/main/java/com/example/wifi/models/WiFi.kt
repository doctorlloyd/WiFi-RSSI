package com.example.wifi.models

import java.io.Serializable

data class WiFi(
    val name: String,
    val strength: Int,
    val capabilities: String,
    val address: String,
    val venueName: String
) : Serializable