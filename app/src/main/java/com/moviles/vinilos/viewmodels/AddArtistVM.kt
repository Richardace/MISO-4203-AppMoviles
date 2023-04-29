package com.moviles.vinilos.viewmodels

import android.app.Application
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.volley.Response
import com.google.gson.Gson
import com.moviles.vinilos.R
import com.moviles.vinilos.brokers.VolleyBroker
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.repository.BandRepository
import com.moviles.vinilos.ui.BandListFragment
import org.json.JSONObject

class AddArtistVM(application: Application) : AndroidViewModel(application) {

    private var _eventNetworkError = MutableLiveData<Boolean>(false)
    val eventNetworkError: LiveData<Boolean> get() = _eventNetworkError
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    val isNetworkErrorShown: LiveData<Boolean> get() = _isNetworkErrorShown
    val nameLD = MutableLiveData<String>();
    val imageLD = MutableLiveData<String>();
    val descriptionLD = MutableLiveData<String>();
    val birthdateLD = MutableLiveData<String>();
    private val bandRepository = BandRepository(application)

    init {}

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
                _eventNetworkError.value = true
            })
        }
    }

    private fun checkNameCreated(name: String): Boolean {
        val bandVM = ViewModelProvider(getApplication())[BandVM::class.java]
        return bandVM.bands.value?.map { it.name }?.contains(name) ?: false
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
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