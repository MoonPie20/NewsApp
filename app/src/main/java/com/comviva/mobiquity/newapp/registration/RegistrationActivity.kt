package com.comviva.mobiquity.newapp.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.comviva.mobiquity.newapp.R
import com.comviva.mobiquity.newapp.login.LoginActivity
import com.comviva.mobiquity.newapp.news.ArtilceDetails
import kotlinx.android.synthetic.main.activity_register.*

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        loginHereButton.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val intent = Intent(this@RegistrationActivity, LoginActivity::class.java)
                startActivity(intent)
            }

        })

    }

}