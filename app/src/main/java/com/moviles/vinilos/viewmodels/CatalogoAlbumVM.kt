package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.repository.CatalogoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatalogoAlbumVM(application: Application) :  AndroidViewModel(application) {

    private val _catalogos = MutableLiveData<List<CatalogoAlbumModel>>()
    private val _catalogo = MutableLiveData<CatalogoAlbumModel>()

    val catalogos: LiveData<List<CatalogoAlbumModel>> get() = _catalogos
    val catalogo: LiveData<CatalogoAlbumModel> get() = _catalogo
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val catalogoRepository = CatalogoRepository(application)

    init {
        refreshDataFromNetwork()
    }

    public fun getAlbum(iAlbum: Int) {
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = catalogoRepository.getAlbum(iAlbum)
                    Log.i("A", data.toString())
                    _catalogo.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }catch (e:Exception){
            _eventNetworkError.value = true
        }
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = catalogoRepository.getData()
                    _catalogos.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }catch (e:Exception){
            _eventNetworkError.value = true
        }
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
