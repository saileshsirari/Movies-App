package apps.sai.com.movieapp.api

import apps.sai.com.movieapp.data.MovieResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val api: MovieApi) : MovieRepository {
    override fun nowPlaying(page: Int): Flow<MovieResponse> {
        return flow { emit(api.nowPlaying(page)) }
    }
}