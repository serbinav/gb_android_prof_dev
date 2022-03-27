package com.example.mytranslator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mytranslator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presenter: MainContract.Presenter<MainContract.View>

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
        TODO("Not yet implemented")
    }
}