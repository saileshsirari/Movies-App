package apps.sai.com.movieapp.ui.popular

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import apps.sai.com.movieapp.BaseViewModel
import apps.sai.com.movieapp.data.Movie
import apps.sai.com.movieapp.data.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(private val movieUseCase: MovieUseCase) :
    BaseViewModel() {
    private var currentNowPlayingResult: Flow<PagingData<Movie>>? = null
    fun popular(): Flow<PagingData<Movie>> {
        val newResult: Flow<PagingData<Movie>> =
            movieUseCase.popular().cachedIn(viewModelScope)
        currentNowPlayingResult = newResult
        return newResult
    }
}