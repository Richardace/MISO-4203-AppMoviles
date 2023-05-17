package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
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
import org.json.JSONObject

class CommentsVM (application: Application, val albumId: String) :  AndroidViewModel(application) {

    private val _comments = MutableLiveData<List<CommentModel>>()

    val comments: LiveData<List<CommentModel>> get() = _comments
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val albumRepository = ColeccionAlbumRepository(application)


    init {
        refreshDataFromNetwork(forced = false)
    }

    private fun refreshDataFromNetwork(forced: Boolean) {
        try{
            viewModelScope.launch(Dispatchers.Default) {
                withContext(Dispatchers.IO) {
                    var data = albumRepository.getComments(albumId = albumId, forced = forced)
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

    fun sendComment(comment: String) {
        val jsonObject1 = JSONObject()
        jsonObject1.put("id", 1)
        val jsonObject = JSONObject()
        jsonObject.put("description", comment)
        jsonObject.put("rating",4)
        jsonObject.put("collector", jsonObject1)
        albumRepository.sendComment(idAlbum = albumId,jsonObject, {
            Toast.makeText(
                getApplication(),
                "Comentario enviado con exito",
                android.widget.Toast.LENGTH_LONG
            ).show();
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
            refreshDataFromNetwork(forced = true)
        }, {
            Log.d("ER", it.message.toString());
            _eventNetworkError.value = true
        })
    }

    class Factory(val app: Application, val albumId: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CommentsVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CommentsVM(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}