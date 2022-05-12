package apps.sai.com.movieapp.ui.nowplaying

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import apps.sai.com.movieapp.databinding.FragmentNowPlayingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NowPlayingFragment : Fragment() {

    private var _binding: FragmentNowPlayingBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var  nowPlayingViewModel:NowPlayingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nowPlayingViewModel = ViewModelProvider(this)[NowPlayingViewModel::class.java]

        _binding = FragmentNowPlayingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        nowPlayingViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nowPlayingViewModel.nowPlayingRequest.value =true
        nowPlayingViewModel.nowPlayingResponseLiveData.observe(viewLifecycleOwner){
            it?.let {

            }
        }
        nowPlayingViewModel.errorLiveData.observe(viewLifecycleOwner){
            it?.let {

            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}