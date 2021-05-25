package ru.dbuzin.waviotapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.dbuzin.waviotapp.R
import ru.dbuzin.waviotapp.adapters.DevicesRecyclerViewAdapter
import ru.dbuzin.waviotapp.databinding.FragmentDevicesBinding
import ru.dbuzin.waviotapp.interfaces.ConsumptionOnClickListener
import ru.dbuzin.waviotapp.models.Device
import ru.dbuzin.waviotapp.models.Devices
import ru.dbuzin.waviotapp.presenters.DevicesFragmentPresenter
import ru.dbuzin.waviotapp.views.DevicesFragmentView

class DevicesFragment : MvpAppCompatFragment(), DevicesFragmentView, ConsumptionOnClickListener {

    @InjectPresenter
    lateinit var devicesFragmentPresenter: DevicesFragmentPresenter

    lateinit var mBinding: FragmentDevicesBinding
    lateinit var devicesRecyclerView: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var devicesRecyclerViewAdapter: DevicesRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDevicesBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        devicesRecyclerView = mBinding.devicesRecyclerView
        progressBar = mBinding.devicesProgressBar
        devicesFragmentPresenter.getDevicesList()
    }

    override fun onSuccess(devices: MutableList<Device>) {
        progressBar.isActivated = false
        progressBar.visibility = View.GONE
        val linearLayoutManager = LinearLayoutManager(this.context)
        devicesRecyclerViewAdapter = DevicesRecyclerViewAdapter(devices, this)
        devicesRecyclerView.layoutManager = linearLayoutManager
        devicesRecyclerView.adapter = devicesRecyclerViewAdapter
    }

    override fun onError(error: String?) {
        progressBar.isActivated = false
        progressBar.visibility = View.GONE
        Toast.makeText(this.context, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.isActivated = true
        progressBar.visibility = View.VISIBLE
    }

    override fun onClick(id: Long) {
        TODO("Not yet implemented")
    }
}