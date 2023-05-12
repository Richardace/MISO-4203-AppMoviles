package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.repository.CatalogoRepository

class CatalogoAlbumVM(application: Application) :  AndroidViewModel(application) {

    private val _catalogos = MutableLiveData<List<CatalogoAlbumModel>>()

    val catalogos: LiveData<List<CatalogoAlbumModel>> get() = _catalogos
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val catalogoRepository = CatalogoRepository(application)

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        catalogoRepository.getData({
            _catalogos.postValue(it)
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

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CatalogoAlbumVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CatalogoAlbumVM(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
