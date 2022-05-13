package apps.sai.com.movieapp.ui.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import apps.sai.com.movieapp.BaseFragment
import apps.sai.com.movieapp.MobileNavigationDirections
import apps.sai.com.movieapp.data.MovieAdapter
import apps.sai.com.movieapp.databinding.FragmentNowPlayingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job

@AndroidEntryPoint
class TopRatedFragment : BaseFragment<TopRatedViewModel>() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!
    override val viewModelClass: Class<TopRatedViewModel>
        get() = TopRatedViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = MovieAdapter{
            it.id?.let {
                findNavController().navigate(
                    MobileNavigationDirections.actionBaseFragmentToDetails(
                        it
                    )
                )
            }
        }
        binding.includedLayout.movieList.adapter = adapter
        loadMovies(viewModel.topRated(), adapter)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}