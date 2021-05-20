package com.example.apptest.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.apptest.R
import com.example.apptest.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setup()
    }

    private fun setup() {
        dataBinding.buttonSend.setOnClickListener {
            val intent = Intent(this, TransitionsActivity::class.java)
            startActivity(intent)
        }
    }




}