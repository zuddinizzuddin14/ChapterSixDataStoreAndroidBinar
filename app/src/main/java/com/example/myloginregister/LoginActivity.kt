package com.example.myloginregister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.ViewModelProvider
import com.example.myloginregister.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: UserDataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = UserDataStoreManager(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        setupView()
    }

    private fun setupView() {
        binding.apply {
            btnLogin.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()

                var prefUsername = ""
                var prefPassword = ""
                viewModel.getUsername().observe(this@LoginActivity) {
                    prefUsername = it
                }
                
                viewModel.getPassword().observe(this@LoginActivity) {
                    prefPassword = it
                }
                
                if (username == prefUsername && password == prefPassword) {
                    viewModel.setSession(true)
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(this@LoginActivity, "no same", LENGTH_SHORT).show()
                }
            }

            tvRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        onDestroy()
    }
}