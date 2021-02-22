package com.example.my_first_application

import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

public data class TestExcursionDataClass(val type:String,
                                  val id:Int,
                                  var name:String,
                                  var mark:Int,
                                  var cost:Any,
                                  var text:String,
                                  var informationForApplication:Array<Array<Array<Double>>>,
                                  var point:Array<Array<Double>>  )
//"Informatiom_for_application": [{"position": 1, "name_of_sight": "test_name_of_sight", "geolocation": "130.05 80.6", "text": "test_text"}]
val ExcursionExampleJson="""{
  "type": "excursion",
  "id": 10000,
  "name": "Test",
  "mark": 5,
  "cost": 0,
  "text": "test-text",
  "informationForApplication": [[[59.847231, 30.175601], [59.850724, 30.175894], [59.850538, 30.185042], [59.847084, 30.184838]],
  [[59.847588, 30.159045], [59.857740, 30.160697], [59.857546, 30.176146], [59.847243, 30.175460]],
  [[60.009765, 29.584000], [60.037726, 29.784158], [59.957554, 29.823983]]]
  "point":[[59.849258, 30.183274],[59.852602, 30.167372],[60.011581, 29.739532]]
}
""".trimIndent()
var testExcursion= Gson().fromJson(ExcursionExampleJson, TestExcursionDataClass::class.java)