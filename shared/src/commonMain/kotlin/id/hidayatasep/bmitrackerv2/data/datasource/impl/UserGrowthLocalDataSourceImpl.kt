package id.hidayatasep.bmitrackerv2.data.datasource.impl

import id.hidayatasep.bmitrackerv2.data.datasource.GrowthLocalDataSource
import id.hidayatasep.bmitrackerv2.database.user_growth.UserGrowth
import id.hidayatasep.bmitrackerv2.database.user_growth.UserGrowthDao

class UserGrowthLocalDataSourceImpl(private val userGrowthDao: UserGrowthDao): GrowthLocalDataSource {
    override suspend fun insertGrowth(userGrowth: UserGrowth) {
        userGrowthDao.insertGrowth(userGrowth)
    }

    override suspend fun getAllGrowthRecords(): List<UserGrowth> {
       return userGrowthDao.getAllGrowthRecords()
    }

    override suspend fun deleteAllGrowthRecords() {
        return userGrowthDao.deleteAllGrowthRecords()
    }
}