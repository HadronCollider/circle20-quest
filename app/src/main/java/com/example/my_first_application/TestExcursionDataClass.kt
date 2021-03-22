package com.example.my_first_application

import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

data class TestExcursionDataClass(
        val id:Int=0,//choise
        var name:String="ghghjgfhj",//choise
        var mark:Long=0,//choise
        var cost:Long=0,//choise
        var text:String="klh",//choise
        var sightDiscriptions:Array<String> = arrayOf("a"),//map
        var sightName:Array<String> =arrayOf("a"),//map
        var point:Array<LatLng>  = arrayOf(LatLng(59.0, 80.0)))//map
        //var informationForApplication:Array<Array<LatLng>>,
//"Informatiom_for_application": [{"position": 1, "name_of_sight": "test_name_of_sight", "geolocation": "130.05 80.6", "text": "test_text"}]
//val ExcursionExampleJson="""{
//  "type": "excursion",
//  "id": 10000,
//  "name": "Test",
//  "mark": 5,
//  "cost": 0,
//  "text": "test-text"
//}
//""".trimIndent()

var testExcursion= TestExcursionDataClass()
// "informationForApplication": [[[59.847231, 30.175601], [59.850724, 30.175894], [59.850538, 30.185042], [59.847084, 30.184838]],[[59.847588, 30.159045], [59.857740, 30.160697], [59.857546, 30.176146], [59.847243, 30.175460]],[[60.009765, 29.584000], [60.037726, 29.784158], [59.957554, 29.823983]]],
//"point":[[59.849258, 30.183274],[59.852602, 30.167372],[60.011581, 29.739532]]
//var testExcursion = TestExcursionDataClass()
