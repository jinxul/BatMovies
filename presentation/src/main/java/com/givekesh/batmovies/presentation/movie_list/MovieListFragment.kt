package com.givekesh.batmovies.presentation.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.givekesh.batmovies.databinding.FragmentMovieListBinding
import com.givekesh.batmovies.domain.util.DataState
import com.givekesh.batmovies.presentation.util.MovieIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel by viewModels()

    private lateinit var pagerAdapter: MovieListPagerAdapter
    private var pagerJob: Job? = null
    private var movieDetailsJob: Job? = null

    private var shouldNavigate = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPagerAdapter()
        requestInitialData()
        subscribeObservers()
    }

    private fun setupPagerAdapter() {
        binding.apply {
            pagerAdapter = MovieListPagerAdapter()
            pagerAdapter.setOnClickListener { movieId ->
                shouldNavigate = true
                lifecycleScope.launch {
                    viewModel.channel.send(
                        MovieIntent.GetMovieDetails(movieId)
                    )
                }
            }
            pagerAdapter.addLoadStateListener { loadState ->
                if (loadState.refresh is LoadState.Loading) {
                    animationView.visibility = View.VISIBLE
                } else {
                    animationView.visibility = View.GONE
                    val error = when {
                        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                        else -> null
                    }
                    error?.let {
                        Toast.makeText(
                            requireContext(),
                            it.error.message, Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
            movieList.adapter = pagerAdapter
        }
    }

    private fun requestInitialData() {
        lifecycleScope.launch {
            viewModel.channel.send(
                MovieIntent.GetInitialData
            )
        }
    }

    private fun subscribeObservers() {
        subscribeObserverForPager()
        subscribeObserverForMovieDetails()
    }

    private fun subscribeObserverForPager() {
        pagerJob = lifecycleScope.launch {
            viewModel.movieResponsePager.collect {
                pagerAdapter.submitData(it)
            }
        }
    }

    private fun subscribeObserverForMovieDetails() {
        movieDetailsJob = lifecycleScope.launch {
            viewModel.movieDetailsResponseDataState.collect { dataState ->
                when (dataState) {
                    DataState.Idle -> Unit
                    DataState.Loading -> Unit
                    is DataState.Success -> {
                        if (shouldNavigate) {
                            val movieDetails = dataState.data
                            val action = MovieListFragmentDirections.actionListToDetails(
                                movie = movieDetails
                            )
                            findNavController().navigate(action)
                        }
                    }
                    is DataState.Failed -> Toast.makeText(
                        requireContext(),
                        dataState.exception.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        pagerJob?.cancel()
        movieDetailsJob?.cancel()
        shouldNavigate = false
    }
}