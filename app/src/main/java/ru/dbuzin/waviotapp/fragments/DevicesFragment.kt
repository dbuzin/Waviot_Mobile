package ru.dbuzin.waviotapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.dbuzin.waviotapp.R
import ru.dbuzin.waviotapp.presenters.DevicesFragmentPresenter
import ru.dbuzin.waviotapp.views.DevicesFragmentView

class DevicesFragment : MvpAppCompatFragment(), DevicesFragmentView {

    @InjectPresenter
    lateinit var devicesFragmentPresenter: DevicesFragmentPresenter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_devices, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        devicesFragmentPresenter.getDevicesList()
    }

    override fun onSuccess() {
        Log.d("success","devices")
    }

    override fun onError(error: String?) {
        Log.d("error","devices")
    }

    override fun showLoading() {
        Log.d("loading","devices")
    }
}