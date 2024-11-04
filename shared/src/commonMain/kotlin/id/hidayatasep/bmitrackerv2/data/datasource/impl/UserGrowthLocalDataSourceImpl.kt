package id.hidayatasep.bmitrackerv2.data.datasource.impl

import id.hidayatasep.bmitrackerv2.data.datasource.GrowthLocalDataSource
import id.hidayatasep.bmitrackerv2.database.user_growth.UserGrowthEntity
import id.hidayatasep.bmitrackerv2.database.user_growth.UserGrowthDao

class UserGrowthLocalDataSourceImpl(private val userGrowthDao: UserGrowthDao): GrowthLocalDataSource {
    override suspend fun insertGrowth(entity: UserGrowthEntity) {
        userGrowthDao.insertGrowth(entity)
    }

    override suspend fun getAllGrowthRecords(): List<UserGrowthEntity> {
       return userGrowthDao.getAllGrowthRecords()
    }

    override suspend fun deleteAllGrowthRecords() {
        return userGrowthDao.deleteAllGrowthRecords()
    }
}