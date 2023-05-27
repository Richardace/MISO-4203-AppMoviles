package com.moviles.vinilos.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
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

        myButton.setOnClickListener {
            viewModel.saveAlbumOnApi()
        }

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setPadding(40, 40, 40, 40)
        backButton.setOnClickListener {

            val action = CrearAlbumFragmentDirections.actionHomeFragmentToCollectorAlbumListFragment()
            view.findNavController().navigate(action)
        }

        val spinner = view.findViewById<Spinner>(R.id.spinnerGenero)
        val genres = arrayOf("Classical", "Salsa", "Rock", "Folk")

        if (spinner != null) {
            val adapter = ArrayAdapter(view.context,
                android.R.layout.simple_spinner_item, genres)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    viewModel.genre.value = genres[position].toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }

        val spinnerLabel = view.findViewById<Spinner>(R.id.spinnerLabel)
        val labels = arrayOf("Sony Music", "EMI", "Discos Fuentes", "Elektra", "Fania Records")

        if (spinnerLabel != null) {
            val adapter = ArrayAdapter(view.context,
                android.R.layout.simple_spinner_item, labels)
            spinnerLabel.adapter = adapter
            spinnerLabel.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    viewModel.recordLabel.value = labels[position].toString()
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
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