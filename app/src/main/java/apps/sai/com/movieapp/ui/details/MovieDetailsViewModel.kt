package apps.sai.com.movieapp.ui.details

import androidx.lifecycle.MutableLiveData
import apps.sai.com.movieapp.BaseViewModel
import apps.sai.com.movieapp.data.Movie
import apps.sai.com.movieapp.data.MovieDetailsResponse
import apps.sai.com.movieapp.data.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val useCase: MovieUseCase) :
    BaseViewModel(useCase) {
    var movieDetailResponse =  MutableLiveData<MovieDetailsResponse>()

    fun loadMovieDetails(id: Int): Flow<MovieDetailsResponse> {
        return useCase.movieDetails(id)
    }
}

