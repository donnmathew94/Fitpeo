package com.don.fitpeo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.don.fitpeo.adapters.CustomAdapter
import com.don.fitpeo.adapters.ItemActionListener
import com.don.fitpeo.constants.Constants
import com.don.fitpeo.databinding.ActivityMainBinding
import com.don.fitpeo.models.ResponseItem
import com.don.fitpeo.ui.ItemDetailActivity
import com.don.fitpeo.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Collections.addAll

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ItemActionListener {
    private val TAG = "MainActivity"
    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private var mList: ArrayList<ResponseItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        recyclerView = binding.myRecycler
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter(mList, this)
        recyclerView.adapter = adapter
    }

    private fun initViewModel() {
        mainViewModel.fetchNetworkData()
        mainViewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.INVISIBLE
                    mList.clear()
                    mList.addAll(response.data!!)
                    recyclerView.adapter?.notifyDataSetChanged()

                }

                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.INVISIBLE

                }

                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    override fun onClickItem(responseItem: ResponseItem) {
        startActivity(Intent(this, ItemDetailActivity::class.java).putExtra(Constants.DATA_KEY,responseItem))
    }
}