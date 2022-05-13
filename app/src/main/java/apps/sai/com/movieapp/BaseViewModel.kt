package apps.sai.com.movieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import apps.sai.com.movieapp.data.Genre
import apps.sai.com.movieapp.data.GenreResponse
import apps.sai.com.movieapp.data.Movie
import apps.sai.com.movieapp.data.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(val movieUseCase: MovieUseCase) : ViewModel() {
    val errorLiveData = MutableLiveData<String>()

    fun genres(): Flow<GenreResponse> {
        return movieUseCase.genres().shareIn(
            viewModelScope,
            replay = 1,
            started = SharingStarted.WhileSubscribed(5000)
        )
    }
}