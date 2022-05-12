package apps.sai.com.movieapp.ui.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import apps.sai.com.movieapp.data.MovieAdapter
import apps.sai.com.movieapp.databinding.FragmentNowPlayingBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null
    private val adapter = MovieAdapter()
    private var job: Job? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var viewModel:PopularViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[PopularViewModel::class.java]
        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.includedLayout.movieList.adapter = adapter
        load()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //nowPlayingViewModel.nowPlayingRequest.value =true

        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            it?.let {

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun load() {
        // Make sure we cancel the previous job before creating a new one
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.popular().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}