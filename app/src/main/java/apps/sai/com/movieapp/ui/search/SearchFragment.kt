package apps.sai.com.movieapp.ui.search

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import apps.sai.com.movieapp.BaseFragment
import apps.sai.com.movieapp.MobileNavigationDirections
import apps.sai.com.movieapp.R
import apps.sai.com.movieapp.data.MovieAdapter
import apps.sai.com.movieapp.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel>() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val args: SearchFragmentArgs? by navArgs()
    override val viewModelClass: Class<SearchViewModel>
        get() = SearchViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = MovieAdapter {
            it.id?.let {
                findNavController().navigate(
                    MobileNavigationDirections.actionBaseFragmentToDetails(
                        it
                    )
                )
            }
        }
        binding.includedLayout.movieList.adapter = adapter
        args?.query?.let {
            loadMovies(viewModel.search(it), adapter)
        }

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

}