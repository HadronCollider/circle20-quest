package com.example.my_first_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val intent= Intent(this, choise_excurshion_or_quest::class.java)
            startActivity(intent)
        }
        button8.setOnClickListener {
            val intent= Intent(this, CkeckingOfDataBase::class.java)
            startActivity(intent)
        }
    }
}