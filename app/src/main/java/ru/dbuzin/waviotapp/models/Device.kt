package ru.dbuzin.waviotapp.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Device(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("class_name")
    val className: String,

    @SerializedName("device_sn")
    val deviceSn: Long,

    @SerializedName("modem_id")
    val modemId: String,

    @SerializedName("device_time")
    val deviceTime: Long,

    @SerializedName("config_time")
    val configTime: Long,

    @SerializedName("timezone")
    val timezone: String,

    @SerializedName("registrators")
    val registratorsMap: HashMap<String, Registrator>)
