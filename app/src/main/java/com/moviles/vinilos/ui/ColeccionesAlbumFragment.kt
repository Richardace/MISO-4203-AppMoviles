package com.moviles.vinilos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.FragmentCatalogoAlbumBinding
import com.moviles.vinilos.databinding.FragmentCollectorAlbumListBinding
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.models.ColeccionAlbumModel
import com.moviles.vinilos.ui.adapters.CatalogoAdapter
import com.moviles.vinilos.ui.adapters.CollectorAlbumAdapter
import com.moviles.vinilos.viewmodels.CatalogoAlbumVM
import com.moviles.vinilos.viewmodels.CollectorAlbumVM


class ColeccionesAlbumFragment : Fragment() {
    private var _binding: FragmentCollectorAlbumListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorAlbumVM
    private var viewModelAdapter: CollectorAlbumAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectorAlbumListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CollectorAlbumAdapter()
        val addAlbumColeccion = view.findViewById<Button>(R.id.addalbumcoleccion)
        addAlbumColeccion.setOnClickListener {
            val action = ColeccionesAlbumFragmentDirections.fragmentAddAlbum()
            view.findNavController().navigate(action)
        }
        val myButton = view.findViewById<Button>(R.id.backButton)
        myButton.setOnClickListener {
            view.findNavController().navigateUp()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.bandRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        viewModel = ViewModelProvider(this, CollectorAlbumVM.Factory(activity.application))[CollectorAlbumVM::class.java]
        viewModel.catalogos.observe(viewLifecycleOwner, Observer<List<ColeccionAlbumModel>> {
            it.apply {
                viewModelAdapter!!.catalogos = this
            }
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
}