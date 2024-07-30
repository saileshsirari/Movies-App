package apps.sai.com.movieapp.data

import android.view.ViewGroup
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import apps.sai.com.movieapp.ui.MovieItem


class MovieAdapter(private val onSelect: (Movie) -> Unit) :
    PagingDataAdapter<Movie, MovieAdapter.GalleryViewHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            ComposeView(parent.context)
        )
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie, onSelect)
        }
    }

    class GalleryViewHolder(
          private val composeView: ComposeView
    ) : RecyclerView.ViewHolder(composeView.rootView) {

        fun bind(item: Movie, onSelect: (Movie) -> Unit) {
            composeView.setContent {
                MovieItem(movie = item, modifier = Modifier, onClick ={
                    onSelect(item)
                })
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
