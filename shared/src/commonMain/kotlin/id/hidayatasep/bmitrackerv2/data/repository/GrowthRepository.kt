package id.hidayatasep.bmitrackerv2.data.repository

import id.hidayatasep.bmitrackerv2.data.datamodel.BMIResult
import id.hidayatasep.bmitrackerv2.data.datamodel.UserGrowth

interface GrowthRepository {

    suspend fun insertGrowth(weight: Float, height: Float)

    suspend fun getAllGrowthRecords(): List<UserGrowth>

    fun calculateBMI(weight: Float, height: Float): BMIResult

}