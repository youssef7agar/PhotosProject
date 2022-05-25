package com.example.photosproject.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.photosproject.MyApplication
import com.example.photosproject.databinding.FragmentUserBinding
import com.example.photosproject.di.ViewModelProviderFactory
import com.example.photosproject.presentation.view.adapter.AlbumsAdapter
import com.example.photosproject.presentation.viewmodel.UserViewModel
import com.example.photosproject.presentation.viewstate.UserViewState
import javax.inject.Inject

class UserFragment : Fragment() {
    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    private val viewModel: UserViewModel by viewModels { viewModelFactory }

    private lateinit var adapter: AlbumsAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as MyApplication).provideAppComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeOnViewState()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        adapter = AlbumsAdapter()
        binding.albumsRecyclerView.adapter = adapter
    }

    private fun observeOnViewState() {
        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            binding.UserProgressBar.isVisible = viewState is UserViewState.Loading
            binding.dataGroup.isVisible = viewState is UserViewState.Success
            when (viewState) {
                UserViewState.Error -> {}
                UserViewState.Loading -> {}
                is UserViewState.Success -> {
                    binding.userNameTextView.text = viewState.user.name
                    binding.userAddressTextView.text = viewState.user.address
                    adapter.submitList(viewState.albums)
                }
            }
        }
    }
}