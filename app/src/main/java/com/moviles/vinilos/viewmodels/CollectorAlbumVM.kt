package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.models.ColeccionAlbumModel
import com.moviles.vinilos.repository.CatalogoRepository
import com.moviles.vinilos.repository.ColeccionAlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CollectorAlbumVM(application: Application) :  AndroidViewModel(application) {

    private val _catalogos = MutableLiveData<List<ColeccionAlbumModel>>()

    val catalogos: LiveData<List<ColeccionAlbumModel>> get() = _catalogos
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val coleccionRepository = ColeccionAlbumRepository(application)

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try {
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = coleccionRepository.getData()
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
            if (modelClass.isAssignableFrom(CollectorAlbumVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorAlbumVM(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
