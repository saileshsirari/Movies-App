package apps.sai.com.movieapp.data

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import apps.sai.com.movieapp.databinding.ListItemMovieBinding


class MovieAdapter :
    PagingDataAdapter<Movie, MovieAdapter.GalleryViewHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            ListItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val photo = getItem(position)
        if (photo != null) {
            holder.bind(photo)
        }
    }

    class GalleryViewHolder(
        private val binding: ListItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.movie?.let { movie ->
                    val uri = Uri.parse(movie.backdropPath)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    view.context.startActivity(intent)
                }
            }
        }

        fun bind(item: Movie) {
            binding.apply {
                movie = item
                executePendingBindings()
            }
        }
    }
}

private class GalleryDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}
