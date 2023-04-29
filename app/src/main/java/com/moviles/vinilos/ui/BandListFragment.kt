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
import com.moviles.vinilos.databinding.FragmentBandListBinding
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.ui.adapters.BandAdapter
import com.moviles.vinilos.viewmodels.BandVM

class BandListFragment : Fragment() {
    private var _binding: FragmentBandListBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: BandVM
    private var viewModelAdapter: BandAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBandListBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = BandAdapter()
        val addArtistButton = view.findViewById<Button>(R.id.addartistbutton)
        addArtistButton.setOnClickListener {
            val action = BandListFragmentDirections.actionBandListToAddArtistFragment()
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
        viewModel = ViewModelProvider(this, BandVM.Factory(activity.application))[BandVM::class.java]
        viewModel.bands.observe(viewLifecycleOwner, Observer<List<BandModel>> {
            it.apply {
                viewModelAdapter!!.bands = this
            }
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
}