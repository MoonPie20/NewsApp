package com.comviva.mobiquity.newapp.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.comviva.mobiquity.newapp.MainActivity
import com.comviva.mobiquity.newapp.R
import com.comviva.mobiquity.newapp.registration.RegistrationActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        registerHereButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }
        handleLogin()

    }

    private fun handleLogin() {
        loginButton.setOnClickListener {
            if(etEmail.text.isNullOrEmpty() || etPassword.text.isNullOrEmpty())
            {
                Toast.makeText(this, R.string.login_empty_emailpassword,Toast.LENGTH_SHORT).show()
            }
            else
            {
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }

        }
    }
}