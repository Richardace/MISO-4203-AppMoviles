package com.moviles.vinilos.repository
import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.models.ColeccionAlbumModel
import com.moviles.vinilos.models.CommentModel
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

    suspend fun getComments(albumId: String, forced: Boolean) : List<CommentModel> {
        var potentialResp = CacheManager.getInstance(application.applicationContext).getComments(id = albumId)
        if(potentialResp.isEmpty() || forced){
            Log.d("Cache decision", "get from network")
            var data = NetworkServiceAdapter.getInstance(application).getCommentsOnAlbum(albumId = albumId)
            CacheManager.getInstance(application.applicationContext).addComments(id = albumId, data = data)
            return data
        }
        else{
            Log.d("Cache decision", "return ${potentialResp.size} elements from cache")
            return potentialResp
        }
    }

    fun sendComment(idAlbum: String, jsonObject: JSONObject,callback: (JSONObject)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).sendCommentOnAlbum(jsonObject, idAlbum,{
            Log.d("REPO2", it.toString())
            callback(it)
        },
            onError
        )
    }
}