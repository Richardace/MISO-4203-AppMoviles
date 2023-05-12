package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CollectorModel
import com.moviles.vinilos.repository.BandRepository
import com.moviles.vinilos.repository.CollectorRepository

class CollectorVM(application: Application) :  AndroidViewModel(application) {

    private val _collectors = MutableLiveData<List<CollectorModel>>()

    val collectors: LiveData<List<CollectorModel>> get() = _collectors
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val collectorRepository = CollectorRepository(application)

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        collectorRepository.getData({
            _collectors.postValue(it)
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
            if (modelClass.isAssignableFrom(CollectorVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CollectorVM(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
