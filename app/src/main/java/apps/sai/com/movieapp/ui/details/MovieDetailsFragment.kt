package apps.sai.com.movieapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import apps.sai.com.movieapp.BaseFragment
import apps.sai.com.movieapp.databinding.FragmentMovieDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job


@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<MovieDetailsViewModel>() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private var job: Job? = null

    private val args: MovieDetailsFragmentArgs? by navArgs()
    override val viewModelClass: Class<MovieDetailsViewModel>
        get() = MovieDetailsViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args?.movieId?.let {
           loadMovieDetails()
        }
    }

    @OptIn(FlowPreview::class)
    fun loadMovieDetails() {
        job?.cancel()
        job = lifecycleScope.launchWhenStarted {
            args?.let {
                if (id != -1) {
                    viewModel.loadMovieDetails(it.movieId).collect {
                        viewModel.movieDetailResponse.value = it
                    }
                }
            }
        }
    }

}
