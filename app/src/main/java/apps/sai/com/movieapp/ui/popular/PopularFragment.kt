package apps.sai.com.movieapp.ui.popular

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
class PopularFragment : BaseFragment<PopularViewModel>() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val binding get() = _binding!!
    override val viewModelClass: Class<PopularViewModel>
        get() = PopularViewModel::class.java

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
        loadMovies(viewModel.popular(),adapter)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}