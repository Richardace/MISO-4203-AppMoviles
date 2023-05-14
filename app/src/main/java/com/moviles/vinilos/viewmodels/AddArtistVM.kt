package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.repository.BandRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject

class AddArtistVM(application: Application) : AndroidViewModel(application) {
    private val _bands = MutableLiveData<List<BandModel>>()
    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    private val bandRepository = BandRepository(application)
    private var bandsList = emptyList<BandModel>()

    val bands: LiveData<List<BandModel>> get() = _bands
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val nameLD = MutableLiveData<String>();
    val imageLD = MutableLiveData<String>();
    val descriptionLD = MutableLiveData<String>();
    val birthdateLD = MutableLiveData<String>();


    init {
        refreshDataFromNetwork()
    }

    fun saveArtistOnApi() {

        var canCallApi = false
        val jsonObject = JSONObject()
        jsonObject.put("name", nameLD.value)
        jsonObject.put("image", imageLD.value)
        jsonObject.put("description", descriptionLD.value)
        jsonObject.put("birthDate", birthdateLD.value)


        if (nameLD.value?.isEmpty() != false) {
            Toast.makeText(getApplication(), "Campo Nombre vacío", Toast.LENGTH_LONG).show();
        } else if (checkNameCreated(nameLD.value ?: "")) {
            Toast.makeText(getApplication(), "El artista ya existe", Toast.LENGTH_LONG).show();
        } else if (imageLD.value?.isEmpty() != false) {
            Toast.makeText(getApplication(), "Campo imagen vacío", Toast.LENGTH_LONG).show();
        } else if (descriptionLD.value?.isEmpty() != false) {
            Toast.makeText(
                getApplication(),
                "Campo descripción vacío",
                android.widget.Toast.LENGTH_LONG
            ).show();
        } else if (birthdateLD.value?.isEmpty() != false) {
            Toast.makeText(
                getApplication(),
                "Campo fecha de nacimiento vacío",
                android.widget.Toast.LENGTH_LONG
            ).show();
        } else {
            canCallApi = true
        }

        if (canCallApi) {
            bandRepository.createBand(jsonObject, {
                Toast.makeText(
                    getApplication(),
                    "Artista creado con exito",
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