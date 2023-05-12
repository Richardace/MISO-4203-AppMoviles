package com.moviles.vinilos.repository

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.network.CacheManager
import com.moviles.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class BandRepository (val application: Application){

   suspend fun getData():List<BandModel> {
       var potentialResp = CacheManager.getInstance(application.applicationContext).getBands(nameList = "listBands")
       if(potentialResp.isEmpty()){
           Log.d("Cache decision", "get from network")
           var bans = NetworkServiceAdapter.getInstance(application).getBands()
           CacheManager.getInstance(application.applicationContext).addBands(nameList = "listBands",bans)
           return bans
       }
       else{
           Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
           return potentialResp
       }
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