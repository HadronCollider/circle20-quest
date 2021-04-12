package com.example.my_first_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_map.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val intent= Intent(this, choise_excurshion_or_quest::class.java)
            startActivity(intent)
        }
        var bottom_sheet= BottomSheetDialogFragment()
        button14.setOnClickListener {
            bottom_sheet.show(supportFragmentManager, "BottomSheets")
        }

    }
}