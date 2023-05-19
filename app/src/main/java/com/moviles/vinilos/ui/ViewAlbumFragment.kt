package com.moviles.vinilos.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.moviles.vinilos.R
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.ui.adapters.BandAdapter
import com.moviles.vinilos.ui.adapters.CatalogoAdapter
import com.moviles.vinilos.ui.adapters.tapped
import com.moviles.vinilos.viewmodels.AddArtistVM
import com.moviles.vinilos.viewmodels.BandVM
import com.moviles.vinilos.viewmodels.CatalogoAlbumVM
import org.w3c.dom.Text

class ViewAlbumFragment : Fragment(), tapped {

    lateinit var viewModel: CatalogoAlbumVM
    private var viewModelAdapter: CatalogoAdapter? = null
    var albumId = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_album, container, false)
        val myButton = view.findViewById<Button>(R.id.SaveArtistbutton)
        viewModelAdapter = CatalogoAdapter(callback = this)

        albumId =  arguments?.getString("albumId").toString()
        Log.i("ALBUMID: ","Prueba llega view album: "+albumId)

        val tv = view.findViewById<TextView>(R.id.nameAlbum)
        tv.text = arguments?.getString("albumId").toString()


        val name = view.findViewById<EditText>(R.id.nombreArtista)
        val image = view.findViewById<EditText>(R.id.imagenArtista)
        val description = view.findViewById<EditText>(R.id.descripcionArtista)
        val birthdate = view.findViewById<EditText>(R.id.birthDateArtista)


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
        viewModel = ViewModelProvider(this, CatalogoAlbumVM.Factory(activity.application))[CatalogoAlbumVM::class.java]
        viewModel.getAlbum(albumId.toInt())
        viewModel.catalogo.observe(viewLifecycleOwner, Observer<CatalogoAlbumModel> {
            it.apply {
                viewModelAdapter!!.catalogo = this
            }
        })


    }

    override fun onItemtapped(catalogo: CatalogoAlbumModel) {
        TODO("Not yet implemented")
    }
}