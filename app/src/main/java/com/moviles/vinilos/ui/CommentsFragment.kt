package com.moviles.vinilos.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moviles.vinilos.R
import com.moviles.vinilos.databinding.FragmentBandListBinding
import com.moviles.vinilos.databinding.FragmentCommentsBinding
import com.moviles.vinilos.models.BandModel
import com.moviles.vinilos.models.CommentModel
import com.moviles.vinilos.ui.adapters.BandAdapter
import com.moviles.vinilos.ui.adapters.CommentAdapter
import com.moviles.vinilos.viewmodels.BandVM
import com.moviles.vinilos.viewmodels.CommentsVM

class CommentsFragment : Fragment() {
    private var _binding: FragmentCommentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CommentsVM
    private var viewModelAdapter: CommentAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommentsBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = CommentAdapter()
        val myButton = view.findViewById<Button>(R.id.backButton)
        myButton.setOnClickListener {
            view.findNavController().navigateUp()
        }
        val sendCommentButton = view.findViewById<Button>(R.id.sendCommentButton)
        sendCommentButton.setOnClickListener {
            sendComment()
        }
        return view
    }

    private fun sendComment() {
        val commentEditText = view?.findViewById<EditText>(R.id.commentEditText)
        if(commentEditText?.text?.isNotEmpty()!!) {
            viewModel.sendComment(comment = commentEditText.text.toString())
            commentEditText.setText("")
        } else {
            Toast.makeText(activity, "Debes introducir tu comentario", Toast.LENGTH_LONG).show()
        }
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
        viewModel = ViewModelProvider(this, CommentsVM.Factory(activity.application))[CommentsVM::class.java]
        viewModel.comments.observe(viewLifecycleOwner, Observer<List<CommentModel>> {
            it.apply {
                viewModelAdapter!!.comments = this
            }
        })

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }
}