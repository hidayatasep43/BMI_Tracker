package id.hidayatasep.bmitrackerv2.database.user_growth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserGrowthDao {

    @Insert
    suspend fun insertGrowth(userGrowth: UserGrowth)

    @Query("SELECT * FROM user_growth ORDER BY date DESC")
    suspend fun getAllGrowthRecords(): List<UserGrowth>

    @Query("DELETE FROM user_growth")
    suspend fun deleteAllGrowthRecords()

}