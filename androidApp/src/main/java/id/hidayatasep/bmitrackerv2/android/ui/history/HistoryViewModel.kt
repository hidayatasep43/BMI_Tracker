package id.hidayatasep.bmitrackerv2.android.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hidayatasep.bmitrackerv2.data.datamodel.UserGrowth
import id.hidayatasep.bmitrackerv2.data.repository.GrowthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HistoryViewModel(private val growthRepository: GrowthRepository) : ViewModel() {

    private val _userGrowthList = MutableStateFlow<List<UserGrowth>>(emptyList())
    val userGrowthList: StateFlow<List<UserGrowth>> = _userGrowthList

    fun getUserGrowthList() {
        viewModelScope.launch {
            val data = growthRepository.getAllGrowthRecords()
            _userGrowthList.value = data
        }
    }


}