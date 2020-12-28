package com.example.my_first_application


import com.google.gson.Gson

data class TestQuestDataClass(val type:String,
                                  val id:Int,
                                  var name:String,
                                  var mark:Int,
                                  var cost:Int,
                                  var text:String,
                                  var InformationForApplication:String)

val QuestExampleJson="""{
  "type": "quest",
  "id": 10001,
  "name": "Big Quest Test",
  "mark": 5,
  "cost": 0,
  "text": "a lot of test text",
  "InformatiomForApplication": [{"position": 1, "radius": 5, "geolocation": "130.05 80.6", "text": "test_text"}]
}
    
""".trimIndent()
var testQuest= Gson().fromJson(QuestExampleJson, TestExcursionDataClass::class.java)