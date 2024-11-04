package id.hidayatasep.bmitrackerv2.data.datasource

import id.hidayatasep.bmitrackerv2.database.user_growth.UserGrowthEntity

interface GrowthLocalDataSource {

    suspend fun insertGrowth(entity: UserGrowthEntity)
    suspend fun getAllGrowthRecords(): List<UserGrowthEntity>
    suspend fun deleteAllGrowthRecords()

}