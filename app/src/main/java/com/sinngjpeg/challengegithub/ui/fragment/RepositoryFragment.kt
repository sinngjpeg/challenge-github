package com.sinngjpeg.challengegithub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sinngjpeg.challengegithub.R
import com.sinngjpeg.challengegithub.ui.adapter.RepositoryAdapter
import com.sinngjpeg.challengegithub.databinding.FragmentListRepositoryBinding
import com.sinngjpeg.challengegithub.viewmodel.RepositoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class RepositoryFragment  : Fragment() {


    private var _binding: FragmentListRepositoryBinding? = null
    private val binding: FragmentListRepositoryBinding get() = _binding ?: throw Exception("")

    private val viewModel: RepositoryViewModel by viewModels()

    private val snackbar: Snackbar by lazy {
        Snackbar.make(
            binding.root,
            R.string.error,
            Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("ok") { requireActivity().finish() }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentListRepositoryBinding.inflate(inflater, container, false).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    private fun initRecyclerView() {

        val adapterRepo = onClick()

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.repositoryList.collectLatest {
                adapterRepo.submitData(it)
            }
        }
        viewModel.error.observe(viewLifecycleOwner) {
            binding.errorMessage.text = it.message
        }

        binding.recyclerViewRepository.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = adapterRepo
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            adapterRepo.loadStateFlow.collectLatest {
                when (it.refresh) {
                    is LoadState.Loading -> binding.progressBarLoading.visibility = View.VISIBLE
                    is LoadState.NotLoading -> {
                        binding.progressBarLoading.visibility = View.GONE
                    }
                    is LoadState.Error -> snackbar.show()
                }
            }
        }
    }

    private fun onClick(): RepositoryAdapter {
        return RepositoryAdapter {
            val action =
                RepositoryFragmentDirections.actionRepositoryFragmentToPullRequestFragment(
                    it.name,
                    it.owner.login
                )
            findNavController().navigate(action)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}