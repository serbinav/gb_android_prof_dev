package com.example.mytranslator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mytranslator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: MainContract.Presenter<MainContract.View>

    private var adapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter = MainPresenter()

        binding.searchBtn.setOnClickListener {
            clickButton()
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }

    override fun clickButton() {
        presenter.getData(binding.searchText.text.toString())
    }

    override fun showError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun showUsers(users: List<WordTranslate>) {
        if (adapter == null) {
            binding.recycler.layoutManager =
                LinearLayoutManager(applicationContext)
            binding.recycler.adapter =
                MainAdapter(users)
        } else {
            adapter!!.setData(users)
        }
    }
}