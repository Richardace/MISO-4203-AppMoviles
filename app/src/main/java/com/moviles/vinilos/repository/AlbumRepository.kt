package com.moviles.vinilos.repository

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.moviles.vinilos.models.AlbumModel
import com.moviles.vinilos.network.CacheManager
import com.moviles.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class AlbumsRepository (val application: Application){

    suspend fun getData():List<AlbumModel> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getListAlbum(nameList = "listAlbum")
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var albums = NetworkServiceAdapter.getInstance(application).getAlbum()
            CacheManager.getInstance(application.applicationContext).addAlbum(nameList = "listAlbum",albums)
            return albums
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }
    }
    fun createAlbum(data: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).createAlbum(data,{
            Log.d("REPO2", it.toString())
            callback(it)
        },
            onError
        )
    }
}