package apps.sai.com.movieapp.ui.favourite

import android.os.Bundle
import android.view.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.paging.map
import apps.sai.com.movieapp.BaseFragment
import apps.sai.com.movieapp.MobileNavigationDirections
import apps.sai.com.movieapp.R
import apps.sai.com.movieapp.data.Movie
import apps.sai.com.movieapp.data.MovieAdapter
import apps.sai.com.movieapp.databinding.FragmentFavBinding
import apps.sai.com.movieapp.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.zip

@AndroidEntryPoint
class FavouriteFragment : BaseFragment<SearchViewModel>() {

    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    private var job: Job? = null

    override val viewModelClass: Class<SearchViewModel>
        get() = SearchViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = MovieAdapter {
            it.id.let {
                findNavController().navigate(
                    MobileNavigationDirections.actionBaseFragmentToDetails(
                        it
                    )
                )
            }
        }
        loadFavourites(adapter)
        binding.includedLayout.movieList.adapter = adapter

        return root
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.menu_search).isVisible = false
        menu.findItem(R.id.menu_favourite).isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    @OptIn(FlowPreview::class)
    fun loadFavourites( adapter: MovieAdapter) {
        job?.cancel()
        job = lifecycleScope.launchWhenStarted {
            viewModel.favouritesMovies().collect {
                adapter.submitData(it)
            }
        }
    }

}