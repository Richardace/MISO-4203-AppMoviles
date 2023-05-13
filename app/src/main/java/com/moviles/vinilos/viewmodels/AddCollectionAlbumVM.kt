package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.models.ColeccionAlbumModel
import com.moviles.vinilos.repository.BandRepository
import com.moviles.vinilos.repository.ColeccionAlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AddCollectionAlbumVM(application: Application) : AndroidViewModel(application) {
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    private val bandRepository = ColeccionAlbumRepository(application)
    val coleccionRepository = ColeccionAlbumRepository(application)
    private val _catalogos = MutableLiveData<List<ColeccionAlbumModel>>()
    val catalogos: LiveData<List<ColeccionAlbumModel>> get() = _catalogos
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown


    init {
        refreshDataFromNetwork()
    }

    fun saveCollectionAlbum(idAlbum: Int) {

        val jsonObject = JSONObject()
        jsonObject.put("price", 25000)
        jsonObject.put("status", "Active")

        bandRepository.createColeccionAlbum(jsonObject,
            idAlbum,
            {
            Toast.makeText(
                getApplication(),
                "Album Añadido con exito",
                android.widget.Toast.LENGTH_LONG
            ).show();
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false

        }, {
            Log.d("ER", it.message.toString());
            _eventNetworkError.value = true
        })
    }


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    private fun refreshDataFromNetwork() {
        coleccionRepository.getData({
            _catalogos.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        }, {
            Log.d("ER", it.message.toString());
            _eventNetworkError.value = true
        })
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddCollectionAlbumVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddCollectionAlbumVM(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}