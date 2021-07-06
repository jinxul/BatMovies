package com.givekesh.batmovies.presentation.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.givekesh.batmovies.databinding.FragmentMovieListBinding
import com.givekesh.batmovies.util.MovieIntent
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

        setupMovieList()
        requestInitialData()
        subscribeObservers()
    }

    private fun setupMovieList() {
        binding.apply {
            pagerAdapter = MovieListPagerAdapter()
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
    }

    private fun subscribeObserverForPager() {
        lifecycleScope.launch {
            viewModel.moviePager.collect {
                pagerAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        pagerJob?.cancel()
    }
}