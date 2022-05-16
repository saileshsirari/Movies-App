package apps.sai.com.movieapp.data

import apps.sai.com.movieapp.api.MovieRepository

class MovieUseCase(private val repository: MovieRepository) {
    fun nowPlaying() = repository.nowPlaying()
    fun popular() = repository.popular()
    fun topRated() = repository.topRated()
    fun upcoming() = repository.upcoming()
    fun search(query:String) = repository.search(query)
    suspend fun genres() = repository.genres()
    fun movieDetails(id:Int) = repository.movieDetails(id)
    fun favouritesMovies() =repository.favouritesMovies()
    fun favourite(id:Int) = repository.isFavourite(id)
    suspend fun setFavourite(movie: Movie) = repository.favourite(movie)
    suspend fun removeFavourite(movie: Movie) = repository.removeFavourite(movie)

}