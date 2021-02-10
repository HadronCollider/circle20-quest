package com.example.my_first_application

import com.google.gson.Gson

public data class TestExcursionDataClass(val type:String,
                                  val id:Int,
                                  var name:String,
                                  var mark:Int,
                                  var cost:Any,
                                  var text:String,
                                  var InformationForApplication:String)

val ExcursionExampleJson="""{
  "type": "excursion",
  "id": 10000,
  "name": "Test",
  "mark": 5,
  "cost": 0,
  "text": "test-text",
  "Informatiom_for_application": [{"position": 1, "name_of_sight": "test_name_of_sight", "geolocation": "130.05 80.6", "text": "test_text"}]
}
    
""".trimIndent()
var testExcursion= Gson().fromJson(ExcursionExampleJson, TestExcursionDataClass::class.java)