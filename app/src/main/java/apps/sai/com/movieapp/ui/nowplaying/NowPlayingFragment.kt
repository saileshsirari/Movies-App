package apps.sai.com.movieapp.ui.nowplaying

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

@AndroidEntryPoint
class NowPlayingFragment : BaseFragment<NowPlayingViewModel>() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!
    override val viewModelClass: Class<NowPlayingViewModel>
        get() = NowPlayingViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val adapter = initMovieAdapter()
        binding.includedLayout.movieList.adapter = adapter
        loadMovies(viewModel.nowPlaying(), adapter)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}