package com.example.my_first_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choise_excurshion_or_quest.*
import kotlinx.android.synthetic.main.activity_main.*

class choise_excurshion_or_quest : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choise_excurshion_or_quest)
        button2.setOnClickListener {
            val intent= Intent(this, excursion_choise_go_or_make::class.java)
            startActivity(intent)
        }
        button3.setOnClickListener {
            val intent= Intent(this, quest_choise_go_or_make::class.java)
            startActivity(intent)
        }
    }
}