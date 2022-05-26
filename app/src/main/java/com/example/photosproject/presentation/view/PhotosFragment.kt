package com.example.photosproject.presentation.view

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.photosproject.MyApplication
import com.example.photosproject.databinding.FragmentPhotosBinding
import com.example.photosproject.di.ViewModelProviderFactory
import com.example.photosproject.presentation.view.adapter.PhotosAdapter
import com.example.photosproject.presentation.viewmodel.PhotosViewModel
import com.example.photosproject.presentation.viewstate.PhotosViewState
import javax.inject.Inject

class PhotosFragment : Fragment() {
    private var _binding: FragmentPhotosBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<PhotosFragmentArgs>()

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel: PhotosViewModel by viewModels { viewModelFactory }

    private lateinit var adapter: PhotosAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MyApplication).provideAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPhotos(albumId = args.album.id)

        binding.albumNameTextView.text = args.album.title
        setUpRecyclerView()
        observeOnViewState()

        setUpSearchBar()
    }

    private fun setUpSearchBar() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                val text = p0.toString()
                if (text.isNotEmpty()) {
                    viewModel.searchPhotos(binding.searchEditText.text.toString())
                } else {
                    viewModel.getPhotos(args.album.id)
                }
            }
        })
    }

    private fun observeOnViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            binding.photosProgressBar.isVisible = viewState is PhotosViewState.Loading
            binding.photosRecyclerView.isVisible = viewState is PhotosViewState.Success
            when (viewState) {
                PhotosViewState.Error -> {}
                PhotosViewState.Loading -> {}
                is PhotosViewState.Success -> {
                    adapter.submitList(viewState.photos)
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        adapter = PhotosAdapter { photoUrl ->
            val action = PhotosFragmentDirections.actionPhotosFragmentToPhotoDialog(photoUrl)
            binding.root.findNavController().navigate(action)
        }
        binding.photosRecyclerView.adapter = adapter
    }
}