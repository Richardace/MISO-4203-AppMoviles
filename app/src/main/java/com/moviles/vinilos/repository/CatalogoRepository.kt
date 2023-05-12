package com.moviles.vinilos.repository
import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.network.NetworkServiceAdapter
import org.json.JSONObject

class CatalogoRepository (val application: Application){

    fun getData(callback: (List<CatalogoAlbumModel>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getCatalogoAlbum({
            val orderedList = it.sortedBy { bandModel ->  bandModel.name}
            callback(orderedList)
        },
            onError
        )
    }
}