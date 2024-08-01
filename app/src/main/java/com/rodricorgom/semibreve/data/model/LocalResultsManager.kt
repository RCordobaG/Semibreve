package com.rodricorgom.semibreve.data.model

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rodricorgom.semibreve.data.RuntimeData.RuntimeSettings
import java.io.File
import java.io.IOException

class LocalResultsManager(context: Context){
    private lateinit var results : MutableList<TestResult>

    val gson = Gson()
    val path = "results.json"

    val sessionContext : Context = context

    val file = File(sessionContext.filesDir,"results.json")

    init {

        try {
            if (!file.exists()){
                file.createNewFile()
            }
            if (file.length().toInt() == 0){
                results = emptyList<TestResult>().toMutableList()
            }
            else{
                val type = object : TypeToken<MutableList<TestResult>>(){}.type
                this.results = gson.fromJson(file.readText(), type)
            }

            Log.d("Manager","Error thrown: ${this.results}")
        }
        catch (e: IOException){
            Log.d("Manager","Error thrown: ${e}")
        }
    }

    //CRUD: Create
    fun createResult(result: TestResult){
        val list = readResults()
        if(list.count() > RuntimeSettings.maxEntries){
            list.removeLast()
        }
        list.add(0,result)
        val json = gson.toJson(list)
        file.writeText(json)
        return
    }

    //CRUD: Read
    fun readResults(): MutableList<TestResult> {
        return this.results
    }

    fun resultAt(index: Int):TestResult{
        val list = readResults()
        val result = list.elementAt(index)
        return result
    }

    fun elementCount():Int{
        val list = readResults()
        val count = list.count()
        return count
    }

    //CRUD: Update
    fun updateResults(list : MutableList<TestResult>){
        val json = gson.toJson(list)
        file.writeText(json)
        return
    }

    //CRUD: Delete
    fun deleteResults(){
        val list = emptyList<TestResult>().toMutableList()
        val json = gson.toJson(list)
        file.writeText(json)
    }

    fun deleteResultAt(index: Int){
        val list = readResults()
        list.removeAt(index)
        val json = gson.toJson(list)
        file.writeText(json)
    }
}