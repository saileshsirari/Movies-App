package apps.sai.com.movieapp.data

import com.google.gson.annotations.SerializedName

data class GenreResponse(
    @SerializedName("genres") var genres: ArrayList<Genre> = arrayListOf(),
)

