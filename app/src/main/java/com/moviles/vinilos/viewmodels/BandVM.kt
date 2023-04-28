package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.moviles.vinilos.brokers.VolleyBroker
import com.moviles.vinilos.models.BandModel

class BandVM (application: Application) :  AndroidViewModel(application) {

    private val _bands = MutableLiveData<List<BandModel>>()

    val bands: LiveData<List<BandModel>>
        get() = _bands

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        VolleyBroker(getApplication()).instance.add(
            VolleyBroker.getRequest("musicians",
            { response ->
                val bands = Gson().fromJson(response, Array<BandModel>::class.java).toList()
                _bands.postValue(bands)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            },
            {
                Log.d("TAG", it.toString())
                _eventNetworkError.value = true
            }
        ))
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