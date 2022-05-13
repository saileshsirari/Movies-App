package apps.sai.com.movieapp.di

import apps.sai.com.movieapp.api.MovieApi
import apps.sai.com.movieapp.api.MovieRepository
import apps.sai.com.movieapp.api.MovieRepositoryImpl
import apps.sai.com.movieapp.data.MovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {
    @Provides
    fun provideMovieRepository(api: MovieApi): MovieRepository =
        MovieRepositoryImpl(api)

    @Provides
    fun provideMovieUseCase(repository: MovieRepository) =
        MovieUseCase(repository)
}

