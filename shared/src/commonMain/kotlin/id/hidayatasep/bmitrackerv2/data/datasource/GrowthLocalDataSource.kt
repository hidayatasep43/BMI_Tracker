package id.hidayatasep.bmitrackerv2.data.datasource

import id.hidayatasep.bmitrackerv2.database.user_growth.UserGrowth

interface GrowthLocalDataSource {

    suspend fun insertGrowth(userGrowth: UserGrowth)
    suspend fun getAllGrowthRecords(): List<UserGrowth>
    suspend fun deleteAllGrowthRecords()

}