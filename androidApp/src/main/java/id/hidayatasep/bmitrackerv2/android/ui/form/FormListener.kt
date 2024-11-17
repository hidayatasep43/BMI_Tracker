package id.hidayatasep.bmitrackerv2.android.ui.form

interface FormListener {

    fun onAgeChange(newAge: String)

    fun onWeightChange(newWeight: String)

    fun onHeightChange(newHeight: String)

    fun calculateBMI()

}