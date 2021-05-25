package ru.dbuzin.waviotapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Cookie(@SerializedName("sc")  val sc: String,
                  @SerializedName("rt") val rt: String,
                  @SerializedName("WAVIOT_JWT") val jwt: String,
                  @SerializedName("sessid") @PrimaryKey val sessId: String)
