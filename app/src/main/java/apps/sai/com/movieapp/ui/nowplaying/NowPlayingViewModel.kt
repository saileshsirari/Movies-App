package apps.sai.com.movieapp.ui.nowplaying

import androidx.lifecycle.*
import apps.sai.com.movieapp.BaseViewModel
import apps.sai.com.movieapp.data.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class NowPlayingViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    BaseViewModel() {
    val nowPlayingRequest = MutableLiveData<Boolean>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text

    val nowPlayingResponseLiveData: LiveData<MovieResponse> =
        nowPlayingRequest.switchMap {
            liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                try {
                    movieUseCase.nowPlaying().collect {
                        emit(it)
                    }
                } catch (e: Exception) {
                    errorLiveData.postValue(e.toString())
                }
            }
        }
}