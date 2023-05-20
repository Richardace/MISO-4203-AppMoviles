package com.moviles.vinilos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CommentModel
import com.moviles.vinilos.repository.BandRepository
import com.moviles.vinilos.repository.ColeccionAlbumRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CommentsVM (application: Application) :  AndroidViewModel(application) {

    private val _comments = MutableLiveData<List<CommentModel>>()

    val comments: LiveData<List<CommentModel>> get() = _comments
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val albumRepository = ColeccionAlbumRepository(application)

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        try{
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = albumRepository.getComments(albumId = "2")
                    _comments.postValue(data)
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
            if (modelClass.isAssignableFrom(CommentsVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CommentsVM(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}