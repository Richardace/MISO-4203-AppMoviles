package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.repository.BandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class crearAlbumVM(application: Application) : AndroidViewModel(application){
    private val _bands = MutableLiveData<List<BandModel>>()
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    private val bandRepository = BandRepository(application)
    private var bandsList = emptyList<BandModel>()

    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val name = MutableLiveData<String>();
    val cover = MutableLiveData<String>();
    val releaseDate = MutableLiveData<String>();
    val description = MutableLiveData<String>();
    val genre = MutableLiveData<String>();
    val recordLabel = MutableLiveData<String>();

    init {
        refreshDataFromNetwork()
    }

    fun saveAlbumOnApi() {
        var canCallApi = false
        val jsonObject = JSONObject()
        jsonObject.put("name", name.value)
        jsonObject.put("cover", cover.value)
        jsonObject.put("releaseDate", releaseDate.value)
        jsonObject.put("description", description.value)
        jsonObject.put("genre", genre.value)
        jsonObject.put("recordLabel", recordLabel.value)

        if (name.value?.isEmpty() != false) {
            Toast.makeText(getApplication(), "Campo Nombre vacío", Toast.LENGTH_LONG).show();
        } else if (checkNameCreated(name.value ?: "")) {
            Toast.makeText(getApplication(), "El Album ya existe", Toast.LENGTH_LONG).show();
        } else if (cover.value?.isEmpty() != false) {
            Toast.makeText(getApplication(), "Campo imagen vacío", Toast.LENGTH_LONG).show();
        } else if (releaseDate.value?.isEmpty() != false) {
            Toast.makeText(getApplication(),"Campo fecha de lazamiento vacío",android.widget.Toast.LENGTH_LONG
            ).show();
        } else if (description.value?.isEmpty() != false) {
            Toast.makeText(
                getApplication(),
                "Campo descripción vacio",
                android.widget.Toast.LENGTH_LONG
            ).show();
        }else if (recordLabel.value?.isEmpty() != false) {
            Toast.makeText(
                getApplication(),
                "Campo sello discografico vacio",
                android.widget.Toast.LENGTH_LONG
            ).show();
        } else {
            canCallApi = true
        }

        if (canCallApi) {
            bandRepository.createBand(jsonObject, {
                Toast.makeText(
                    getApplication(),
                    "Album creado con exito",
                    android.widget.Toast.LENGTH_LONG
                ).show();
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false

            }, {
                Log.d("ER", it.message.toString());
                _eventNetworkError.value = true
            })
        }
    }

    private fun checkNameCreated(name: String): Boolean {
        var valor = false
        valor = bandsList.map { it2 -> it2.name }.contains(name)

        return  valor;
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }
    private fun refreshDataFromNetwork() {
        try{
            viewModelScope.launch(Dispatchers.Default){
                withContext(Dispatchers.IO){
                    var data = bandRepository.getData()
                    _bands.postValue(data)
                }
                _eventNetworkError.postValue(false)
                _isNetworkErrorShown.postValue(false)
            }
        }catch (e:Exception){
            _eventNetworkError.value = true
        }
    }


    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddArtistVM::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddArtistVM(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }


}