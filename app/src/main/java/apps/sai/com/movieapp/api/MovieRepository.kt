package apps.sai.com.movieapp.api

import androidx.paging.PagingData
import apps.sai.com.movieapp.data.*
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface MovieRepository {
    fun nowPlaying(): Flow<PagingData<Movie>>
    fun topRated(): Flow<PagingData<Movie>>
    fun upcoming(): Flow<PagingData<Movie>>
    fun popular(): Flow<PagingData<Movie>>
    fun genres(): Flow<GenreResponse>
    fun movieDetails(id:Int):Flow<MovieDetailsResponse>

}