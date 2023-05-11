package com.moviles.vinilos.network

import android.content.Context
import com.moviles.vinilos.models.BandModel

class CacheManager(context: Context) {
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }
    private var bands: HashMap<String, List<BandModel>> = hashMapOf()

    fun addBands(nameList: String, band: List<BandModel>){
        if (!bands.containsKey(nameList)){
            bands[nameList] = band
        }
    }
    fun getBands(nameList: String) : List<BandModel>{
        return if (bands.containsKey(nameList)) bands[nameList]!! else listOf<BandModel>()
    }
}