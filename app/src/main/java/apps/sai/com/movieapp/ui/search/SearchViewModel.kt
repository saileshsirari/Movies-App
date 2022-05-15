package apps.sai.com.movieapp.ui.search

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
class SearchViewModel @Inject constructor(useCase: MovieUseCase) :
    BaseViewModel(useCase) {
    private var currentResult: Flow<PagingData<Movie>>? = null
    fun search(query:String): Flow<PagingData<Movie>> {
        val newResult: Flow<PagingData<Movie>> =
            movieUseCase.search(query)
        currentResult = newResult
        return newResult
    }
}