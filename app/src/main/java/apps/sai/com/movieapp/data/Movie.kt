package apps.sai.com.movieapp.data

import android.content.Context
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import apps.sai.com.movieapp.R
import apps.sai.com.movieapp.Utils
import apps.sai.com.movieapp.Utils.format2Places
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

@Entity
data class Movie(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("adult") val adult: Boolean? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("genres") @Nullable var genres: ArrayList<Genre> = arrayListOf(),
    @SerializedName("genre_ids") @Nullable var genreIds: ArrayList<Int> = arrayListOf(),
    @SerializedName("original_language") val originalLanguage: String? = null,
    @SerializedName("original_title") val originalTitle: String? = null,
    @SerializedName("overview") val overview: String? = null,
    @SerializedName("popularity") val popularity: Double? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("video") val video: Boolean? = null,
    @SerializedName("vote_average") val voteAverage: Double? = null,
    @SerializedName("vote_count") val voteCount: Int? = null
){
    var releaseDateFormatted: String? = null
    var spokenLanguagesFormatted:String? =null
    var genresFormatted:String? =null
    var voteAverageFormatted:String ? =null
    companion object {
        fun Movie.format(context: Context) {
            releaseDate?.let {
                releaseDateFormatted =
                    "${context.getString(R.string.release_date)} ${Utils.getFormatedDate(it)}"
            }

            originalLanguage?.let {
                spokenLanguagesFormatted =
                    "${context.getString(R.string.language)} ${Locale(it).displayName}"
            }
            voteCount?.let {
                if(it>0) {
                    voteAverageFormatted = "${context.getString(R.string.ratings)} " +
                            "${voteAverage?.format2Places()} ( $voteCount ${context.getString(R.string.votes)} )"
                }
            }

            genresFormatted =""
            if(!genres.isNullOrEmpty()) {
                genres.forEach {
                    genresFormatted += it.name + " "
                }
            }

            if (genreIds.isNullOrEmpty()) {
                genreIds = arrayListOf()
            }
        }
    }
}


