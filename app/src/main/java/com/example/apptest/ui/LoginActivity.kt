package com.example.apptest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.apptest.R
import com.example.apptest.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth : FirebaseAuth
    private lateinit var binding: ActivityLoginBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        checkSession()

        binding.buttonSend.setOnClickListener {
            doLogin()
        }
    }

    private fun doLogin() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        if(username.isNotBlank() && password.isNotBlank()) {
            auth.signInWithEmailAndPassword(username, password)
                .addOnSuccessListener {
                    checkSession()
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Algo va mal, intente de nuevo", Toast.LENGTH_LONG).show()
                }
        }
    }

    private fun checkSession() {
        if(auth.currentUser != null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}