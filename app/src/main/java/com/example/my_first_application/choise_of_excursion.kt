package com.example.my_first_application

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_choise_of_excursion.*
import java.time.chrono.JapaneseEra.values
import java.util.function.LongFunction

class choise_of_excursion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choise_of_excursion)
        val db = Firebase.firestore
        var name=findViewById<TextView>(R.id.Name)
        var cost=findViewById<TextView>(R.id.CostValue)
        var mark=findViewById<TextView>(R.id.MarkValue)
        var text=findViewById<TextView>(R.id.Short_Discription)
        db.collection("Excursions")
                .document("Test1")
                .get()
                .addOnCompleteListener { task ->
                    Log.d("testDB", task.toString())
                    if (task.isSuccessful) {
                        Log.d("testDB", "success")
                        val test = task.result.data
                        if (test != null) {
                            Log.d("testDB",test.toString())
                            testExcursion.name=test["name"] as String
                            testExcursion.text = test["discription"] as String
                            testExcursion.cost=test["cost"] as Long
                            testExcursion.mark=test["mark"] as Long
                            Log.d("testDB",test["cost"].toString())
                            name.text="${testExcursion.name}"
                            cost.text="${testExcursion.cost}"
                            mark.text="${testExcursion.mark}"
                            text.text="${testExcursion.text}"
                        }
                    }
                    else{
                        Log.d("testDB", "not success")
                    }
                }


        button10.setOnClickListener{
            val intent= Intent(this, Main_map::class.java)
            startActivity(intent)
        }
    }
}