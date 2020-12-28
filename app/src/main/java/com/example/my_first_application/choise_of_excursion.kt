package com.example.my_first_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_choise_of_excursion.*
import java.time.chrono.JapaneseEra.values

class choise_of_excursion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choise_of_excursion)
        var name=findViewById<TextView>(R.id.Name)
        name.text="${testExcursion.name}"
        var cost=findViewById<TextView>(R.id.CostValue)
        cost.text="${testExcursion.cost}"
        var mark=findViewById<TextView>(R.id.MarkValue)
        mark.text="${testExcursion.mark}"
        var text=findViewById<TextView>(R.id.Short_Discription)
        text.text="${testExcursion.text}"
        button10.setOnClickListener{
            val intent= Intent(this, Main_map::class.java)
            startActivity(intent)
        }
    }
}