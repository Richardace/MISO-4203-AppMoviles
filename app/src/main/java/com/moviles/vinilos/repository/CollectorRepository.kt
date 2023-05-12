package com.moviles.vinilos.repository

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CollectorModel
import com.moviles.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class CollectorRepository (val application: Application){
    fun getData(callback: (List<CollectorModel>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getCollectors({
            val orderedList = it.sortedBy { bandModel ->  bandModel.name}
            callback(orderedList)
        },
            onError
        )
    }
}