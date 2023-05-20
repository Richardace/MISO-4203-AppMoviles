package com.moviles.vinilos.repository
import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.network.CacheManager
import com.moviles.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class CatalogoRepository (val application: Application){

    suspend fun getData():List<CatalogoAlbumModel> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getCatalogoAlbum(nameList = "listCatalogoAlbum")
        if(potentialResp.isEmpty()){
            Log.d("Cache decision", "get from network")
            var catalogos = NetworkServiceAdapter.getInstance(application).getCatalogoAlbum()
            CacheManager.getInstance(application.applicationContext).addCatalogoAlbum(nameList = "listCatalogoAlbum",catalogos)
            return catalogos
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }
    }

    suspend fun getAlbum(idAlbum: Int): CatalogoAlbumModel {
        var albumMusical = NetworkServiceAdapter.getInstance(application).getAlbum(idAlbum)
        return albumMusical
    }
}