package com.example.my_first_application


import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

data class TestQuestDataClass(val id:Int=0,//choise
                              var name:String="ghghjgfhj",//choise
                              var mark:Long=0,//choise
                              var cost:Long=0,//choise
                              var text:String="klh",//choise
                              var riddles:Array<String> = arrayOf("a"),//map
                              var helps:Array<String> = arrayOf("a"),//map
                              var point:Array<LatLng> = arrayOf(LatLng(59.0, 80.0)),//map
                              var locations:Array<Array<LatLng>> = arrayOf(arrayOf(LatLng(59.0, 80.0))))

//val QuestExampleJson="""{
  //"type": "quest",
  //"id": 10001,
  //"name": "Big Quest Test",
 // "mark": 5,
 // "cost": 0,
 // "text": "a lot of test text",
//}
    
//""".trimIndent()
var testQuest=TestQuestDataClass()//Gson().fromJson(QuestExampleJson, TestExcursionDataClass::class.java)