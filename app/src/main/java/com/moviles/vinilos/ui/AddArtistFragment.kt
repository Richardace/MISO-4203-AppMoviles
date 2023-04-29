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
import com.moviles.vinilos.viewmodels.AddArtistVM
import com.moviles.vinilos.viewmodels.BandVM
import org.w3c.dom.Text

class AddArtistFragment : Fragment() {

    lateinit var viewModel: AddArtistVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_add_artist, container, false)
        val myButton = view.findViewById<Button>(R.id.SaveArtistbutton)
        val name = view.findViewById<EditText>(R.id.nombreArtista)
        val image = view.findViewById<EditText>(R.id.imagenArtista)
        val description = view.findViewById<EditText>(R.id.descripcionArtista)
        val birthdate = view.findViewById<EditText>(R.id.birthDateArtista)
        name.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.nameLD.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        description.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.descriptionLD.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        image.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.imageLD.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        birthdate.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {
                viewModel.birthdateLD.value = s.toString()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        });

        myButton.setOnClickListener {
            viewModel.saveArtistOnApi()
        }

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setPadding(40, 40, 40, 40)
        backButton.setOnClickListener {
            view.findNavController().navigateUp()
        }
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel =
            ViewModelProvider(this, AddArtistVM.Factory(activity.application))[AddArtistVM::class.java]

    }
}