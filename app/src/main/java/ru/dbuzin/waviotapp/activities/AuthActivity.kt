package ru.dbuzin.waviotapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ProgressBar
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import ru.dbuzin.waviotapp.databinding.ActivityAuthBinding
import ru.dbuzin.waviotapp.presenters.AuthPresenter
import ru.dbuzin.waviotapp.views.AuthView

class AuthActivity : MvpAppCompatActivity(), AuthView {
    lateinit var mBinding : ActivityAuthBinding
    lateinit var loginText : TextInputEditText
    lateinit var passwordText : TextInputEditText
    lateinit var loginButton : MaterialButton
    lateinit var recoveryButton : MaterialButton
    lateinit var registrationButton : MaterialButton
    lateinit var progressBar : ProgressBar

    @InjectPresenter
    lateinit var authPresenter : AuthPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityAuthBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        authPresenter.isAuthorized()
        loginText = mBinding.login
        passwordText = mBinding.password
        loginButton = mBinding.loginButton
        recoveryButton = mBinding.recoveryPasswordButton
        registrationButton = mBinding.registrationButton
        progressBar = mBinding.authProgressBar

        loginButton.setOnClickListener {
            if (authPresenter.textInputCheck(loginText, passwordText, this)) {
                loginButton.isEnabled = true
                authPresenter.authentication(
                    loginText.text.toString(),
                    passwordText.text.toString()
                )
            }
            else
                loginButton.isEnabled = false
        }
    }

    override fun onSuccess() {
        progressBar.isActivated = false
        progressBar.visibility = GONE
        val intent = Intent(this@AuthActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onSuccessRecovery(message: String?) {
        TODO("Not yet implemented")
    }

    override fun onError(error: String?) {
        progressBar.isActivated = false
        progressBar.visibility = GONE
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        progressBar.isActivated = true
        progressBar.visibility = VISIBLE
    }
}