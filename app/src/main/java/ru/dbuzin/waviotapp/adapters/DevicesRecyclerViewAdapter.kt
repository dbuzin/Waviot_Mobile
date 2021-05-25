package ru.dbuzin.waviotapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.google.android.material.textview.MaterialTextView
import ru.dbuzin.waviotapp.R
import ru.dbuzin.waviotapp.interfaces.ConsumptionOnClickListener
import ru.dbuzin.waviotapp.models.Device


class DevicesRecyclerViewAdapter(devices: MutableList<Device>, mListener: ConsumptionOnClickListener): RecyclerView.Adapter<DevicesRecyclerViewAdapter.DevicesViewHolder>() {
    val devices: MutableList<Device> = devices
    val mListener: ConsumptionOnClickListener = mListener

    class DevicesViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {

        var deviceLogo: AppCompatImageView = itemView.findViewById(R.id.device_logo_image_view)
        var modemIdTextView: MaterialTextView = itemView.findViewById(R.id.modem_id_text_view)
        var serialTextView: MaterialTextView = itemView.findViewById(R.id.device_sn_text_view)
        var goConsumptionButton: MaterialButton = itemView.findViewById(R.id.go_consumption_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevicesViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.device_card_layout, parent, false)
        return DevicesViewHolder(view)
    }

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        holder.modemIdTextView.text = holder.itemView.context.getString(R.string.modem_id, devices[position].modemId)
        holder.serialTextView.text = holder.itemView.context.getString(R.string.serial_number, devices[position].deviceSn)
        when(devices[position].className){
            "Electricity" -> Glide.with(holder.itemView).load(R.drawable.electro_logo).into(holder.deviceLogo)
            "Water" -> Glide.with(holder.itemView).load(R.drawable.water_logo).into(holder.deviceLogo)
            "Heat" -> Glide.with(holder.itemView).load(R.drawable.heat_logo).into(holder.deviceLogo)
            "Gas" -> Glide.with(holder.itemView).load(R.drawable.gaz_logo).into(holder.deviceLogo)
        }
        holder.goConsumptionButton.setOnClickListener { mListener.onClick(devices[position].id) }
    }

    override fun getItemCount(): Int {
        return devices.size
    }
}