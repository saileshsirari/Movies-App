package apps.sai.com.movieapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Favourite(
    @PrimaryKey val id: Int,
)