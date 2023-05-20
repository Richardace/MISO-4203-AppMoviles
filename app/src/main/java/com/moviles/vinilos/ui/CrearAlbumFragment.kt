package com.moviles.vinilos.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.moviles.vinilos.R
import com.moviles.vinilos.viewmodels.crearAlbumVM


class CrearAlbumFragment : Fragment() {

    lateinit var viewModel: crearAlbumVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crear_album, container, false)
        val myButton = view.findViewById<Button>(R.id.SaveAlbumbutton)
        val name = view.findViewById<EditText>(R.id.nombreAlbum)
        val cover = view.findViewById<EditText>(R.id.cover)
        val description = view.findViewById<EditText>(R.id.descripcionAlbum)
        val releaseDate = view.findViewById<EditText>(R.id.fechaLazamiento)
        val genre = view.findViewById<EditText>(R.id.genero)
        val recordLabel = view.findViewById<EditText>(R.id.selloDiscografico)

        name.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.name.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        cover.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.cover.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        description.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.description.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        releaseDate.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.releaseDate.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        releaseDate.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.releaseDate.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        genre.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.genre.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        recordLabel.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.recordLabel.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        myButton.setOnClickListener {
            viewModel.saveAlbumOnApi()
        }

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setPadding(40, 40, 40, 40)
        backButton.setOnClickListener {

            val action = CrearAlbumFragmentDirections.actionHomeFragmentToCollectorAlbumListFragment()
            view.findNavController().navigate(action)
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel =
            ViewModelProvider(this, crearAlbumVM.Factory(activity.application))[crearAlbumVM::class.java]

    }
}