package apps.sai.com.movieapp.di

import apps.sai.com.movieapp.api.MovieApi
import apps.sai.com.movieapp.api.MovieRepository
import apps.sai.com.movieapp.api.MovieRepositoryImpl
import apps.sai.com.movieapp.data.MovieUseCase
import apps.sai.com.movieapp.db.FavDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepository(api: MovieApi,favDao:FavDao): MovieRepository =
        MovieRepositoryImpl(api,favDao)

    @Provides
    fun provideMovieUseCase(repository: MovieRepository) =
        MovieUseCase(repository)
}

