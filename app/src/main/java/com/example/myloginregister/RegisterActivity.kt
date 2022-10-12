package com.example.myloginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.myloginregister.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: UserDataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = UserDataStoreManager(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        setupView()
    }

    private fun setupView() {
        binding.apply {
            btnRegister.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()

                viewModel.setUser(username, password)
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
            }
        }
    }
}