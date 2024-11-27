package id.hidayatasep.bmitrackerv2.android.ui.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.hidayatasep.bmitrackerv2.data.repository.GrowthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FormViewModel(private val growthRepository: GrowthRepository) : ViewModel(), FormListener {

    private val _formState = MutableStateFlow(FormState())
    val formState: StateFlow<FormState> = _formState

    override fun onAgeChange(newAge: String) {
        _formState.value = _formState.value.copy(age = newAge)
    }

    override fun onWeightChange(newWeight: String) {
        _formState.value = _formState.value.copy(weight = newWeight)
    }

    override fun onHeightChange(newHeight: String) {
        _formState.value = _formState.value.copy(height = newHeight)
    }

    override fun calculateBMI() {
        if (!validateInputs()) return
        val weight = _formState.value.weight.toFloat()
        val height = _formState.value.height.toFloat()

        viewModelScope.launch {
            val bmiResult = growthRepository.calculateBMI(weight, height)
            _formState.value = _formState.value.copy(message = bmiResult.category, bmiResult = bmiResult.bmi)
            growthRepository.insertGrowth(weight, height)
        }
    }

    private fun validateInputs(): Boolean {
        val age = _formState.value.age.toIntOrNull()
        val weight = _formState.value.weight.toFloatOrNull()
        val height = _formState.value.height.toFloatOrNull()

        return if (age == null || weight == null || height == null || height == 0f) {
            _formState.value = _formState.value.copy(
                errorMessage = "Please enter valid numbers for age, weight, and height."
            )
            false
        } else {
            _formState.value = _formState.value.copy(
                errorMessage = null
            )
            true
        }
    }

}

data class FormState(
    val age: String = "",
    val weight: String = "",
    val height: String = "",
    val errorMessage: String? = null,
    val message: String? = null,
    val bmiResult: Float? = null
)
