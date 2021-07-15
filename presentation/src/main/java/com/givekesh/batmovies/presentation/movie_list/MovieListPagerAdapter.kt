package com.givekesh.batmovies.presentation.movie_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.givekesh.batmovies.BR
import com.givekesh.batmovies.databinding.ItemMovieBinding
import com.givekesh.batmovies.domain.entities.Movie
import com.givekesh.batmovies.presentation.util.ItemClickListener

class MovieListPagerAdapter : PagingDataAdapter<Movie, MovieListViewHolder>(DiffCallback) {
    private lateinit var listener: ItemClickListener

    object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(
            oldItem: Movie, newItem: Movie
        ): Boolean = oldItem.imdbId == newItem.imdbId

        override fun areContentsTheSame(
            oldItem: Movie, newItem: Movie
        ): Boolean = oldItem == newItem
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            holder.itemView.setOnClickListener {
                listener.onMovieClickListener(item.imdbId)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return MovieListViewHolder(binding)
    }

    fun setOnClickListener(listener: ItemClickListener) {
        this.listener = listener
    }
}

class MovieListViewHolder(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(Movie: Movie) {
        binding.setVariable(BR.movie, Movie)
    }
}