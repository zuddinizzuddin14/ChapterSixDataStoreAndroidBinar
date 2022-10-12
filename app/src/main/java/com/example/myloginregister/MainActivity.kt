package com.example.myloginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myloginregister.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: UserDataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        pref = UserDataStoreManager(this@MainActivity)
        viewModel = ViewModelProvider(this@MainActivity, ViewModelFactory(pref))[MainViewModel::class.java]

        viewModel.getSession().observe(this@MainActivity) {
            if (!it) {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        }
        getData()
    }

    private fun getData() {
        viewModel.apply {
            getUsername().observe(this@MainActivity) {
                binding.tvUsername.text = it.toString()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onDestroy()
    }
}