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

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val myButton = view.findViewById<Button>(R.id.visitorButton)
        myButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToVisitorFragment()
            view.findNavController().navigate(action)
        }

        val collectorButton = view.findViewById<Button>(R.id.SaveArtistbutton)
        collectorButton.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToCollectorListFragment()
            view.findNavController().navigate(action)
        }

        return view
    }
}