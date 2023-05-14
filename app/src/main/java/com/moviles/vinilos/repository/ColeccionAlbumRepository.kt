package com.moviles.vinilos.repository
import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.models.ColeccionAlbumModel
import com.moviles.vinilos.network.CacheManager
import com.moviles.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class ColeccionAlbumRepository (val application: Application){

   suspend fun getData() : List<ColeccionAlbumModel> {
       var potentialResp = CacheManager.getInstance(application.applicationContext).getColeccionAlbum(nameList = "listColeccionAlbum")
       if(potentialResp.isEmpty()){
           Log.d("Cache decision", "get from network")
           var CollecionAlbum = NetworkServiceAdapter.getInstance(application).getColeccionAlbum()
           CacheManager.getInstance(application.applicationContext).addColeccionAlbum(nameList = "listColeccionAlbum",CollecionAlbum)
           return CollecionAlbum
       }
       else{
           Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
           return potentialResp
       }
    }

    fun createColeccionAlbum(data: JSONObject, idAlbum:Int, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).createAlbumCollecion(data, idAlbum,{
            Log.d("REPO2", it.toString())
            callback(it)
        },
            onError
        )
    }
}