package ru.dbuzin.waviotapp.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Devices(
        @SerializedName("devices")
        val devicesMap: HashMap<Long, Device>)
