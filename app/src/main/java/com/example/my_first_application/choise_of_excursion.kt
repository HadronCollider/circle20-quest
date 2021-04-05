package com.example.my_first_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_choise_of_excursion.*

class choise_of_excursion : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choise_of_excursion)
        val db = Firebase.firestore
        var name=findViewById<TextView>(R.id.Nameq)
        var cost=findViewById<TextView>(R.id.CostValue)
        var mark=findViewById<TextView>(R.id.MarkValueq)
        var text=findViewById<TextView>(R.id.Short_Discriptionq)
        name.text="${testExcursion.name}"
        cost.text="${testExcursion.cost}"
        mark.text="${testExcursion.mark}"
        text.text="${testExcursion.text}"

        button10.setOnClickListener{
            val db = Firebase.firestore
            val getting=db.collection("Quests").document("Test2")
                    .get()
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val test = task.result.data
                            if (test != null) {
                                val lst = test["geopoints"] as ArrayList<GeoPoint>
                                testQuest.point=lst.map { LatLng(it.latitude, it.longitude) }.toTypedArray()
                                val referenc= test["referenceToLocations"] as String
                                db.collection("locations").document("$referenc")
                                        .get()
                                        .addOnCompleteListener { task2 ->
                                            if (task2.isSuccessful) {
                                                val test2 = task2.result.data
                                                if (test2 != null) {
                                                    val lst2:MutableList<ArrayList<GeoPoint>> = mutableListOf()
                                                    for(values in test2){
                                                        Log.d("testDB", "values: ${values.value}")
                                                        try {
                                                            val try_cast = values.value  as ArrayList<GeoPoint>
                                                            lst2.add(try_cast)
                                                        }
                                                        catch (e:Exception){
                                                            Log.d("testDB", e.toString())
                                                        }
                                                    }
                                                    testQuest.locations=lst2.map { it.map { LatLng(it.latitude, it.longitude) }.toTypedArray() }.toTypedArray()

                                                }    }                         }
                            }
                        }
                    }
            val intent= Intent(this, ExcursionMap::class.java)
            startActivity(intent)
        }
    }
}