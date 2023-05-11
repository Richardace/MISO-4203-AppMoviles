package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.repository.BandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BandVM (application: Application) :  AndroidViewModel(application) {

    private val _bands = MutableLiveData<List<BandModel>>()

    val bands: LiveData<List<BandModel>> get() = _bands
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val bandsRepository = BandRepository(application)

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try{
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = bandsRepository.getData()
                    _bands.postValue(data)
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
            if (modelClass.isAssignableFrom(BandVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return BandVM(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}