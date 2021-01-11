package com.example.my_first_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_choise_of_excursion.*
import kotlinx.android.synthetic.main.activity_quest_choise_go_or_make.*

class quest_choise_go_or_make : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quest_choise_go_or_make)
        button6.setOnClickListener {
            val intent = Intent(this, ChoiseOfQuest::class.java)
            startActivity(intent)
        }
    }
}