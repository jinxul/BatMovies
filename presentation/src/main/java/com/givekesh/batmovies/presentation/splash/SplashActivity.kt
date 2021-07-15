package com.givekesh.batmovies.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.givekesh.batmovies.presentation.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(
            Intent(
                this,
                MainActivity::class.java
            )
        )
        finish()
    }
}
