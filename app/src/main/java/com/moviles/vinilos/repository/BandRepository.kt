package com.moviles.vinilos.repository

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.google.gson.Gson
import com.moviles.vinilos.brokers.VolleyBroker
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class BandRepository (val application: Application){

    fun getData(callback: (List<BandModel>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getBands({
            Log.d("REPO1", it.toString())
            val orderedList = it.sortedBy { bandModel ->  bandModel.name}
            callback(orderedList)
        },
            onError
        )
    }
    fun createBand(data: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).createBand(data,{
            Log.d("REPO2", it.toString())
            callback(it)
        },
            onError
        )
    }
}