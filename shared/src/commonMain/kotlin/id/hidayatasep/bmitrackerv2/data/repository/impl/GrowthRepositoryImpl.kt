package id.hidayatasep.bmitrackerv2.data.repository.impl

import id.hidayatasep.bmitrackerv2.data.datamodel.UserGrowth
import id.hidayatasep.bmitrackerv2.data.datasource.GrowthLocalDataSource
import id.hidayatasep.bmitrackerv2.data.repository.GrowthRepository
import id.hidayatasep.bmitrackerv2.database.user_growth.UserGrowthEntity
import kotlinx.datetime.Clock

class GrowthRepositoryImpl(private val growthLocalDataSource: GrowthLocalDataSource) : GrowthRepository {

    override suspend fun insertGrowth(weight: Float, height: Float) {
        growthLocalDataSource.insertGrowth(
            UserGrowthEntity(
                id = 0,
                height = height,
                weight = weight,
                date = Clock.System.now().epochSeconds
            )
        )
    }

    override suspend fun getAllGrowthRecords(): List<UserGrowth> {
        return growthLocalDataSource.getAllGrowthRecords().map {
            UserGrowth(it.height, it.weight, it.date)
        }
    }


}