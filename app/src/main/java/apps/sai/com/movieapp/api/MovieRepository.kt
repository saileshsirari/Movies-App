package apps.sai.com.movieapp.api

import apps.sai.com.movieapp.data.MovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface MovieRepository {
    fun nowPlaying(@Query("page") page: Int): Flow<MovieResponse>
}