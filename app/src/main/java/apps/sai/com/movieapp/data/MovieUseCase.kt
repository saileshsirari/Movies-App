package apps.sai.com.movieapp.data

import apps.sai.com.movieapp.api.MovieRepository

class MovieUseCase constructor(private val repository: MovieRepository) {
    fun nowPlaying() = repository.nowPlaying()
    fun popular() = repository.popular()
    fun topRated() = repository.topRated()
    fun upcoming() = repository.upcoming()
}