package id.hidayatasep.bmitrackerv2.database.user_growth

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_growth")
data class UserGrowth(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val height: Float,  // Height in cm or meters
    val weight: Float,  // Weight in kg
    val date: Long      // Date in timestamp of the growth record
)