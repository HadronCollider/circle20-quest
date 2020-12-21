package com.example.my_first_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choise_excurshion_or_quest.*
import kotlinx.android.synthetic.main.activity_excursion_choise_go_or_make.*

class excursion_choise_go_or_make : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excursion_choise_go_or_make)
        button4.setOnClickListener {
            val intent= Intent(this, choise_of_excursion::class.java)
            startActivity(intent)
        }
    }
}