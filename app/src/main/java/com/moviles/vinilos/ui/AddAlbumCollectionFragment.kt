package com.moviles.vinilos.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.FragmentAddAlbumCollectionBinding
import com.moviles.vinilos.models.CatalogoAlbumModel
import com.moviles.vinilos.models.ColeccionAlbumModel
import com.moviles.vinilos.ui.adapters.CatalogoAdapter
import com.moviles.vinilos.ui.adapters.tapped
import com.moviles.vinilos.viewmodels.AddArtistVM
import com.moviles.vinilos.viewmodels.AddCollectionAlbumVM
import com.moviles.vinilos.viewmodels.CatalogoAlbumVM


class AddAlbumCollectionFragment : Fragment(), tapped{
    private var _binding: FragmentAddAlbumCollectionBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CatalogoAlbumVM
    private var viewModelAdapter: CatalogoAdapter? = null

    lateinit var viewModel2: AddCollectionAlbumVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAlbumCollectionBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CatalogoAdapter(callback = this)
        val myButton = view.findViewById<Button>(R.id.backButton)

        myButton.setOnClickListener {
            val action = AddAlbumCollectionFragmentDirections.actionHomeFragmentToCollectorAlbumListFragment()
            view.findNavController().navigate(action)
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
        viewModel = ViewModelProvider(this, CatalogoAlbumVM.Factory(activity.application))[CatalogoAlbumVM::class.java]
        viewModel.catalogos.observe(viewLifecycleOwner, Observer<List<CatalogoAlbumModel>> {
            it.apply {
                viewModelAdapter!!.catalogos = this
            }
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })

        viewModel2 = ViewModelProvider(this, AddCollectionAlbumVM.Factory(activity.application))[AddCollectionAlbumVM::class.java]

    }

    override fun onItemtapped(catalogo: CatalogoAlbumModel) {
        Log.i("TEST 2: ", catalogo.id.toString())
        viewModel2.saveCollectionAlbum(catalogo.id)
    }
}