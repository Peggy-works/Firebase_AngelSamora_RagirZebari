package com.example.firebase_angelsamora_ragirzebari

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val LoginFragment = LoginFragment()
        val SignupFragment = SignupFragment()

        findViewById<Button>(R.id.login).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fContainer, LoginFragment)
                commit()
            }
        }

        findViewById<Button>(R.id.signup).setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fContainer, SignupFragment)
                commit()
            }
        }
    }
}