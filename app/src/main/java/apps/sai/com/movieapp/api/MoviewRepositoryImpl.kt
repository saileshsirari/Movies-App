package apps.sai.com.movieapp.api

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import apps.sai.com.movieapp.data.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val api: MovieApi) : MovieRepository {
    override fun nowPlaying(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(api, MovieType.NOW_PLAYING) }
        ).flow
    }

    override fun popular(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(api, MovieType.POPULAR) }
        ).flow
    }

    override fun topRated(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(api, MovieType.TOP_RATED) }
        ).flow
    }

    override fun upcoming(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { MoviePagingSource(api, MovieType.UPCOMING) }
        ).flow
    }

    override fun genres(): Flow<GenreResponse> {
        return flow {
            emit(api.genres())
        }
    }

    override fun movieDetails(id: Int): Flow<MovieDetailsResponse> {
        return flow {
            emit(api.movieDetails(id))
        }
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}