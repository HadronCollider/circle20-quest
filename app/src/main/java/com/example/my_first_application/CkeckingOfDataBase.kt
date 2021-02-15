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
import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ckecking_of_data_base.*


class CkeckingOfDataBase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ckecking_of_data_base)

        val database2 = FirebaseFirestore.getInstance()
        val storage = Firebase.storage
        val storageRef = storage.reference
        val db = Firebase.firestore
        var name = findViewById<TextView>(R.id.textView3)
        //name.text = "${getting}"
        val getting=db.collection("testing").document("TestExcursion1")
                .get()
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                            val test= task.result.data
                        if (test != null) {

                                testExcursion.name=test["name"].toString()


                        }
                        }

                }

        //db.collection("testing")
          //      .get()
            //    .addOnCompleteListener { task ->
              //      if (task.isSuccessful) {
                //        for (document in task.result) {
                  //          Log.d(TAG, document.id + " => " + document.data)
                    //        val testExcursion= document.data
                      //      for (i in testExcursion){
                        //        name.text="${i}"
                          //  }
                        //}
                //}

        fun amIConnected(): Boolean {
            val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
        button9.setOnClickListener {
            if (!amIConnected()) {
                Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
            } else {
                var textOf = findViewById<TextView>(R.id.editTextTextPersonName)
                var text = textOf.getText().toString()
                val TestText = hashMapOf(
                        "text" to text
                )
            }
        }
    }
}