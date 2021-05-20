package com.example.apptest.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptest.R
import com.example.apptest.databinding.ActivityTransitionsBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import model.ResponseTransations
import model.Transaction
import rest.RestApi
import rest.RetrofitClienteInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransitionsActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var dataBindign: ActivityTransitionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBindign = DataBindingUtil.setContentView(this, R.layout.activity_transitions)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        askForPermission()
        getTransactions()
    }

    private fun askForPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation()
                } else
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            askForPermission()
            return
        }
        fusedLocationClient.lastLocation.addOnSuccessListener {
            val strLocation = " (${it.latitude}, ${it.longitude})"
            dataBindign.txtLocation.text = strLocation
        }

    }

    private fun getTransactions() {
        val instance: RestApi = RetrofitClienteInstance.getInstance().create(RestApi::class.java)
        val call: Call<ResponseTransations> = instance.getTransactions()

        call.enqueue(object : Callback<ResponseTransations> {
            override fun onFailure(call: Call<ResponseTransations>, t: Throwable) {
                Log.e("Response", t.message!!)
                Log.e("Response", "error inesperado")
            }

            override fun onResponse(call: Call<ResponseTransations>, response: Response<ResponseTransations>) {
                when(response.code()) {
                    200 -> {
                        if(response.body() != null) {
                            setUpList(response.body()!!.transactions)
                        }
                    }
                    400 -> {
                        Toast.makeText(this@TransitionsActivity, "Ups: something is missing", Toast.LENGTH_LONG).show()
                    }
                    422-> {
                        Toast.makeText(this@TransitionsActivity, "Ups: information could not be retrieved", Toast.LENGTH_LONG).show()
                    }
                }

            }

        })
    }

    private fun setUpList(list: ArrayList<Transaction>) {
        val adapter = TransactionsAdapter(list)
        dataBindign.transactionsList.adapter = adapter
        dataBindign.transactionsList.layoutManager = LinearLayoutManager(this)
    }
}