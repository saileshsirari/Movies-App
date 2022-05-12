package apps.sai.com.movieapp.ui.nowplaying

import apps.sai.com.movieapp.api.MovieRepository
import apps.sai.com.movieapp.data.MovieResponse
import kotlinx.coroutines.flow.Flow

class MovieUseCase constructor(private val repository: MovieRepository) {

    fun nowPlaying(
    ): Flow<MovieResponse> {
        return repository.nowPlaying(1)
    }
}