package id.hidayatasep.bmitrackerv2.data.repository.impl

import id.hidayatasep.bmitrackerv2.data.datamodel.BMIResult
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

    override fun calculateBMI(weight: Float, height: Float): BMIResult {
        if (height <= 0) {
            throw IllegalArgumentException("Height must be greater than 0")
        }
        if (weight <= 0) {
            throw IllegalArgumentException("Weight must be greater than 0")
        }
        val heightInM = height / 100
        // Calculate BMI
        val bmi = weight / (heightInM * heightInM)

        // Determine category
        val category = when {
            bmi < 18.5 -> "Underweight: You might need to gain a little weight. Consider consulting with a healthcare provider."
            bmi in 18.5..24.9 -> "Normal weight: Great job! Maintain your current lifestyle to stay healthy."
            bmi in 25.0..29.9 -> "Overweight: You may want to look at your diet and exercise routine. Consulting a professional could help."
            else -> "Obesity: It's important to take action for your health. Reach out to a healthcare provider for advice."
        }

        // Return result
        return BMIResult(bmi = bmi, category = category)
    }


}