package com.example.my_first_application

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class CkeckingOfDataBase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ckecking_of_data_base)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("test")
        var textOf =findViewById<TextView>(R.id.editTextTextPersonName)
        var text=textOf.getText().toString()
        myRef.setValue(text)
        myRef.addValueEventListener(object :ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue()
                Log.d("Tag", "value is: $value")
                var name=findViewById<TextView>(R.id.textView3)
                name.text="${value}"
            }
            override fun  onCancelled(error: DatabaseError) {
                Log.w("tag", "Failed to read value.", error.toException())

            }
            })

    }
}