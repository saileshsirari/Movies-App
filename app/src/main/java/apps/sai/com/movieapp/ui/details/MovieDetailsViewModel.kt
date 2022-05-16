package apps.sai.com.movieapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import apps.sai.com.movieapp.BaseViewModel
import apps.sai.com.movieapp.data.Movie
import apps.sai.com.movieapp.data.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val useCase: MovieUseCase) :
    BaseViewModel(useCase) {
    var movieDetailResponse = MutableLiveData<Movie>()
    var favourite = false

    fun loadMovieDetails(id: Int): Flow<Movie> {
        return useCase.movieDetails(id)
    }

    fun favClicked(id: Int) {
        viewModelScope.launch {
            if (favourite) {
                movieDetailResponse.value?.let { useCase.removeFavourite(it) }
            } else {
                movieDetailResponse.value?.let { useCase.setFavourite(it) }
            }
        }
    }
}

