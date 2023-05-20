package com.moviles.vinilos.network

import android.content.Context
import com.moviles.vinilos.models.*

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
    private var collectors: HashMap<String, List<CollectorModel>> = hashMapOf()
    private var catalogoAlbums: HashMap<String, List<CatalogoAlbumModel>> = hashMapOf()
    private var coleccionAlbum: HashMap<String, List<ColeccionAlbumModel>> = hashMapOf()
    private var albums: HashMap<String, List<AlbumModel>> = hashMapOf()
    fun addBands(nameList: String, band: List<BandModel>){
        if (!bands.containsKey(nameList)){
            bands[nameList] = band
        }
    }
    fun getBands(nameList: String) : List<BandModel>{
        return if (bands.containsKey(nameList)) bands[nameList]!! else listOf<BandModel>()
    }

    fun addCollectors(nameList: String, collector: List<CollectorModel>){
        if (!collectors.containsKey(nameList)){
            collectors[nameList] = collector
        }
    }
    fun getCollectors(nameList: String): List<CollectorModel>{
        return  if (collectors.containsKey(nameList)) collectors[nameList]!! else listOf<CollectorModel>()
    }

    fun addCatalogoAlbum(nameList: String, catalogos: List<CatalogoAlbumModel>){
        if(!catalogoAlbums.contains(nameList)){
            catalogoAlbums[nameList] = catalogos
        }
    }

    fun getCatalogoAlbum(nameList: String): List<CatalogoAlbumModel>{
        return if (catalogoAlbums.containsKey(nameList)) catalogoAlbums[nameList]!! else listOf<CatalogoAlbumModel>()
    }

    fun addColeccionAlbum(nameList: String, coleccions: List<ColeccionAlbumModel>){
        if(!coleccionAlbum.contains(nameList)){
            coleccionAlbum[nameList] = coleccions
        }
    }
    fun getColeccionAlbum(nameList: String): List<ColeccionAlbumModel>{
        return if (coleccionAlbum.containsKey(nameList)) coleccionAlbum[nameList]!! else listOf<ColeccionAlbumModel>()
    }

    fun getListAlbum(nameList: String): List<AlbumModel>{
        return if (albums.containsKey(nameList)) albums[nameList]!! else listOf<AlbumModel>()
    }

    fun addAlbum(nameList: String, album: List<AlbumModel>){
        if(!albums.contains(nameList)){
            albums[nameList] = album
        }
    }

}