package apps.sai.com.movieapp.api

import apps.sai.com.movieapp.data.MovieResponse
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MovieApi {
    @GET("now_playing")
    suspend fun nowPlaying(
        @Query("page") page: Int,
    ): MovieResponse

    @GET("popular")
    suspend fun popular(
        @Query("page") page: Int,
    ): MovieResponse

    @GET("top_rated")
    suspend fun topRated(
        @Query("page") page: Int,
    ): MovieResponse

    @GET("upcoming")
    suspend fun upcoming(
        @Query("page") page: Int,
    ): MovieResponse

    companion object {
        private const val BASE_URL =
            "https://api.themoviedb.org/3/movie/"

        fun create(apiKey: String): MovieApi {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val okHttpClient = OkHttpClient().newBuilder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(provideAccessTokenInterceptor(apiKey))
                .build()

            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
                .create(MovieApi::class.java)
        }

        private fun provideAccessTokenInterceptor(apiKey: String): Interceptor =
            object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

                    chain.run {
                        val builder = request().newBuilder()
                        val url =
                            request().url.newBuilder().addQueryParameter("api_key", apiKey).build()
                        return proceed(builder.url(url).build())
                    }
                }
            }
    }
}
