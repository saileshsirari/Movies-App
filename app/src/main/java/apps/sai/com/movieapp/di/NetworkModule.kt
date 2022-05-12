package apps.sai.com.movieapp.di

import apps.sai.com.movieapp.api.ApiKey
import apps.sai.com.movieapp.api.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideMovieService(@ApiKey apiKey: String): MovieApi {
        return MovieApi.create(apiKey)
    }

    @Singleton
    @Provides
    @ApiKey
    fun provideApiKey():String {
        return "0e7274f05c36db12cbe71d9ab0393d47"
    }
}
