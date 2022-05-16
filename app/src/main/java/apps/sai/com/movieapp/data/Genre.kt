package apps.sai.com.movieapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class Genre(
    @PrimaryKey @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null
)


