package id.hidayatasep.bmitrackerv2

import id.hidayatasep.bmitrackerv2.data.repository.GrowthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GrowthRepositoryHelper: KoinComponent {

    private val growthRepository: GrowthRepository by inject()
    fun getGrowthRepository(): GrowthRepository = growthRepository

}