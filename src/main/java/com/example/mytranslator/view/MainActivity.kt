package com.example.mytranslator.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytranslator.R
import com.example.mytranslator.databinding.ActivityMainBinding
import com.example.mytranslator.view_model.AppState
import com.example.mytranslator.view_model.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by lazy {
        ViewModelProvider.NewInstanceFactory().create(MainViewModel::class.java)
    }
    private val observer = Observer<AppState> { renderData(it) }

    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener {
            model.getData(binding.searchText.text.toString()).observe(this@MainActivity, observer)
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                val data = appState.data
                if (data == null || data.isEmpty()) {
                    showErrorScreen(getString(R.string.empty_server_response))
                } else {
                    if (adapter == null) {
                        binding.recycler.layoutManager =
                            LinearLayoutManager(applicationContext)
                        binding.recycler.adapter =
                            MainAdapter(data)
                    } else {
                        adapter!!.setData(data)
                    }
                }
            }
            is AppState.Loading -> {

            }
            is AppState.Error -> {
                showErrorScreen(appState.error.message)
            }
        }
    }

    private fun showErrorScreen(error: String?) {
        binding.errorText.text = error ?: getString(R.string.undefined_error)
        binding.reloadBtn.setOnClickListener {
            model.getData("test").observe(this, observer)
        }
    }
}