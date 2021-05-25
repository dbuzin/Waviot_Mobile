package ru.dbuzin.waviotapp.models

import com.google.gson.annotations.SerializedName

data class Registrator(
    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("channel_id")
    val channelId: String,

    @SerializedName("unit_id")
    val unitId: String,

    @SerializedName("offset")
    val offset: Double,

    @SerializedName("modem_value")
    val modemValue: Double,

    @SerializedName("last_value")
    val lastValue: Double,

    @SerializedName("last_value_timestamp")
    val lastValueTimestamp: Long,

    @SerializedName("billing_init_value")
    val billingInitValue: Double,

    @SerializedName("billing_init_timestamp")
    val billingInitTimestamp: Long
)
