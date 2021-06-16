package com.example.apptest.ui

import adapters.MovementsAdapter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.apptest.App
import com.example.apptest.R
import com.example.apptest.databinding.ActivityMainBinding
import com.example.apptest.model.Movement
import com.example.apptest.viewmodel.MainViewModel
import com.example.apptest.viewmodel.factory.MainVMFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MovementsAdapter

    private var movementsList = ArrayList<Movement>()

    @Inject
    lateinit var factory: MainVMFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as App).appCompoment.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        binding.viewModel = viewModel

        initRecyclerView()
        initData()
    }

    private fun initRecyclerView() {
        binding.rvMovements.layoutManager = LinearLayoutManager(this)
        adapter = MovementsAdapter(movementsList)
        binding.rvMovements.adapter = adapter

    }

    private fun initData() {
        viewModel.getMovements().observe(this, {
            it?.let {
                movementsList.addAll(it)
            }
        })
    }


}