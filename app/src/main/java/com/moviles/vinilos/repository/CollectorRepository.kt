package com.moviles.vinilos.repository

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CollectorModel
import com.moviles.vinilos.network.CacheManager
import com.moviles.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class CollectorRepository (val application: Application){
   suspend fun getData():List<CollectorModel> {
       var potentialResp = CacheManager.getInstance(application.applicationContext).getCollectors(nameList = "listCollectors")
       if(potentialResp.isEmpty()){
           Log.d("Cache decision", "get from network")
           var collector = NetworkServiceAdapter.getInstance(application).getCollectors()
           CacheManager.getInstance(application.applicationContext).addCollectors(nameList = "listCollectors",collector)
           return collector;
       }else{
           Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
           return potentialResp
       }

    }
}