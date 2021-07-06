package com.givekesh.batmovies.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.givekesh.batmovies.BR
import com.givekesh.batmovies.data.entities.Search
import com.givekesh.batmovies.databinding.ItemMovieBinding

class MovieListPagerAdapter : PagingDataAdapter<Search, MovieListViewHolder>(DiffCallback) {

    object DiffCallback : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(
            oldItem: Search, newItem: Search
        ): Boolean = oldItem.imdbID == newItem.imdbID

        override fun areContentsTheSame(
            oldItem: Search, newItem: Search
        ): Boolean = oldItem == newItem
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null)
            holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieListViewHolder(binding)
    }
}

class MovieListViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: Search) {
        binding.setVariable(BR.movie, movie)
    }
}