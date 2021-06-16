package ui

import com.example.apptest.App
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.apptest.R
import com.example.apptest.databinding.ActivityLoginBinding
import es.dmoral.toasty.Toasty
import viewmodel.LoginViewModel
import viewmodel.factory.LoginVMFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    @Inject
    lateinit var factory: LoginVMFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appCompoment.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonSend.setOnClickListener {
            validateDataLogin(binding.inputEmail.text.toString(), binding.inputPassword.text.toString())
        }
    }

    private fun validateDataLogin(email: String, password: String) {
        if(email.isNotBlank() && password.isNotBlank()) {
            doLogin()
        } else {
            Toasty.error(this, "El campo Email o Pasword no deben quedar vacios").show()
        }
    }

    private fun doLogin() {
        viewModel.login()
    }
}