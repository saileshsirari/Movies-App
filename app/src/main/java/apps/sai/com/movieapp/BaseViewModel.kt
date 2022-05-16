package apps.sai.com.movieapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import apps.sai.com.movieapp.data.GenreResponse
import apps.sai.com.movieapp.data.Movie
import apps.sai.com.movieapp.data.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(val movieUseCase: MovieUseCase) : ViewModel() {
    val errorLiveData = MutableLiveData<String>()

    suspend fun genres(): Flow<GenreResponse> {
        return movieUseCase.genres().shareIn(
            viewModelScope,
            replay = 1,
            started = SharingStarted.WhileSubscribed(5000)
        )
    }

    fun favouritesMovies(): Flow<PagingData<Movie>> {
        return movieUseCase.favouritesMovies()
    }

    fun favourite(id: Int): Flow<Movie?> {
        return movieUseCase.favourite(id)
    }
}