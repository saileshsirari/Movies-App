package apps.sai.com.movieapp.ui.toprated

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
class TopRatedViewModel @Inject constructor(useCase: MovieUseCase) :
    BaseViewModel(useCase) {
    private var currentNowPlayingResult: Flow<PagingData<Movie>>? = null
    fun topRated(): Flow<PagingData<Movie>> {
        val newResult: Flow<PagingData<Movie>> =
            movieUseCase.topRated().cachedIn(viewModelScope)
        currentNowPlayingResult = newResult
        return newResult
    }
}