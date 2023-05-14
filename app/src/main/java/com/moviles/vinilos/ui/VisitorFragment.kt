package com.moviles.vinilos.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.moviles.vinilos.R
class VisitorFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_visitor, container, false)
        val myButton = view.findViewById<Button>(R.id.artistButton)
        val myButtonAlbum = view.findViewById<Button>(R.id.AlbumButton)
        val collectorButton = view.findViewById<Button>(R.id.collectorButton)

        myButton.setOnClickListener {
            // Toast.makeText(activity, "Button Clicked", Toast.LENGTH_SHORT).show()
            val action = VisitorFragmentDirections.actionVisitorFragmentToBandList()
            view.findNavController().navigate(action)
        }

        myButtonAlbum.setOnClickListener {
            // Toast.makeText(activity, "Button Clicked", Toast.LENGTH_SHORT).show()
            val action = VisitorFragmentDirections.fragmentCatalogoAlbum()
            view.findNavController().navigate(action)
        }

        collectorButton.setOnClickListener {
            val action = VisitorFragmentDirections.actionVisitorFragmentToCollectorListFragment()
            view.findNavController().navigate(action)
        }

        val backButton = view.findViewById<Button>(R.id.backButton)
        backButton.setPadding(40, 40, 40, 40)
        backButton.setOnClickListener {
            view.findNavController().navigateUp()
        }

        return view
    }
}