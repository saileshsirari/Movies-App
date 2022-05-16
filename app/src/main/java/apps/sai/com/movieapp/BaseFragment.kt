package apps.sai.com.movieapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.map
import apps.sai.com.movieapp.Utils.formatDate
import apps.sai.com.movieapp.Utils.getFormatedDate
import apps.sai.com.movieapp.Utils.safe
import apps.sai.com.movieapp.Utils.toDate
import apps.sai.com.movieapp.data.Movie
import apps.sai.com.movieapp.data.Movie.Companion.format
import apps.sai.com.movieapp.data.MovieAdapter
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip

abstract class BaseFragment<V : BaseViewModel> : Fragment() {
    private var job: Job? = null
    val viewModel: V by lazy { ViewModelProvider(this)[viewModelClass] }
    protected abstract val viewModelClass: Class<V>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.findItem(R.id.menu_search).isVisible = true
        menu.findItem(R.id.menu_favourite).isVisible = true
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), "Error " + it, Toast.LENGTH_LONG).show()
            }
        }
        findNavController().addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.navigation_details_fragment, R.id.navigation_search -> {

                }
                else -> {

                }
            }
        }
    }

    @OptIn(FlowPreview::class)
    fun loadMovies(flow: Flow<PagingData<Movie>>, adapter: MovieAdapter) {
        job?.cancel()
        job = lifecycleScope.launchWhenStarted {
            viewModel.genres().zip(flow) { genreResponse, pagingData ->
                pagingData.map { movie ->
                    genreResponse.genres.filter { genre ->
                        movie.genreIds.contains(genre.id)
                    }.map {
                        if( movie.genres.isNullOrEmpty()) {
                            movie.genres = arrayListOf()
                        }
                        movie.genres.add(it)
                    }
                    movie.format(requireContext())
                    movie
                }
            }
                .collect { value ->

                    adapter.submitData(value)
                }
        }
    }

}