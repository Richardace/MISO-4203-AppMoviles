package com.moviles.vinilos.ui

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.moviles.vinilos.R
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.ui.adapters.CatalogoAdapter
import com.moviles.vinilos.ui.adapters.tapped
import com.moviles.vinilos.viewmodels.CatalogoAlbumVM
import com.squareup.picasso.Picasso
import java.io.IOException
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL


class ViewAlbumFragment : Fragment(), tapped {

    lateinit var viewModel: CatalogoAlbumVM
    private var viewModelAdapter: CatalogoAdapter? = null
    var albumId = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_view_album, container, false)
        val myButton = view.findViewById<Button>(R.id.SaveArtistbutton)
        viewModelAdapter = CatalogoAdapter(callback = this)

        albumId =  arguments?.getString("albumId").toString()

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

                val textNameAlbum = view?.findViewById<TextView>(R.id.nameAlbum)
                textNameAlbum?.setText(this.name)

                val textFecha = view?.findViewById<TextView>(R.id.fecha)
                textFecha?.setText(this.releaseDate)

                val textGenero = view?.findViewById<TextView>(R.id.genero)
                textGenero?.setText(this.genre)

                val textSello = view?.findViewById<TextView>(R.id.sello)
                textSello?.setText(this.recordLabel)

                val textDescripcion = view?.findViewById<TextView>(R.id.descripcion)
                textDescripcion?.setText(this.description)

                val imagenAlbum = view?.findViewById<ImageView>(R.id.imagenAlbum)

                Picasso.get()
                    .load(this.cover)
                    .error(R.mipmap.ic_launcher_round)
                    .resize(600, 600)
                    .into(imagenAlbum)

                Log.i("Prueba name: ", this.name)
                Log.i("Prueba cover: ", this.cover)
                Log.i("Prueba releaseDate: ", this.releaseDate)
                Log.i("Prueba description: ", this.description)
                Log.i("Prueba genre: ", this.genre)
                Log.i("Prueba recordLabel: ", this.recordLabel)

            }
        })


    }

    override fun onItemtapped(catalogo: CatalogoAlbumModel) {
        TODO("Not yet implemented")
    }
}