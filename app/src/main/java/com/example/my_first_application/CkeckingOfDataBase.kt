package com.example.my_first_application

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import android.content.ContentValues.TAG
import kotlinx.android.synthetic.main.activity_ckecking_of_data_base.*


class CkeckingOfDataBase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ckecking_of_data_base)
        //val database = FirebaseDatabase.getInstance()
        //val myRef = database.getReference("test")


        //button9.setOnClickListener {
            //var textOf =findViewById<TextView>(R.id.editTextTextPersonName)
            //var text=textOf.getText().toString()
            //myRef.setValue(text)
        //}


        //myRef.addValueEventListener(object : ValueEventListener {
            //override fun onDataChange(dataSnapshot: DataSnapshot) {
                //val value = dataSnapshot.getValue()
               // Log.d("Tag", "value is: $value")
               // var name = findViewById<TextView>(R.id.textView3)
                //name.text = "${value}"
            //}

            //override fun onCancelled(error: DatabaseError) {
                //Log.w("tag", "Failed to read value.", error.toException())

            //}
        //})

        val database2 = FirebaseFirestore.getInstance()
        val storage = Firebase.storage
        val storageRef = storage.reference
        val db = Firebase.firestore

        val testNote = hashMapOf(
                "text" to "note"
        )
        //val test1="string"

// Add a new document with a generated ID
        db.collection("testing")
                .add(testNote)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        val getting=db.collection("testing").get()
        var name = findViewById<TextView>(R.id.textView3)
        name.text = "${getting}"



        db.collection("testing")
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            Log.d(TAG, document.id + " => " + document.data)
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.exception)
                    }
                }
        button9.setOnClickListener {
            var textOf =findViewById<TextView>(R.id.editTextTextPersonName)
            var text=textOf.getText().toString()
            val TestText = hashMapOf(
                    "text" to text
            )
            db.collection("testing")
                    .add(TestText)
        }

    }
}