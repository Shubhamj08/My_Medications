package com.project.mymedications

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("medicineNameFormatted")
fun TextView.setMedicineNameFormatted(item: MedicineEntity?) {
    item?.let {
        text = "NAME:\t${item?.medName}"
    }
}

@BindingAdapter("medicineDescriptionFormatted")
fun TextView.setMedicineDescriptionFormatted(item: MedicineEntity?) {
    item?.let {
        text = "DESCRIPTION:\t${item?.medDescription}"
    }
}

@BindingAdapter("firstDoseFormatted")
fun TextView.setFirstDoseFormatted(item: MedicineEntity?) {
    item?.let {
        text = "FIRST DOZE:\t${item?.dose1}"
    }
}

@BindingAdapter("firstTimeFormatted")
fun TextView.setFirstTimeFormatted(item: MedicineEntity?) {
    item?.let {
        text = "TIME:\t${item?.time1}"
    }
}

@BindingAdapter("secondDoseFormatted")
fun TextView.setSecondDoseFormatted(item: MedicineEntity?) {
    item?.let {
        text = "SECOND DOSE:\t${item?.dose2}"
    }
}

@BindingAdapter("secondTimeFormatted")
fun TextView.setSecondTimeFormatted(item: MedicineEntity?) {
    item?.let {
        text = "TIME:\t${item?.time2}"
    }
}

@BindingAdapter("thirdDoseFormatted")
fun TextView.setThirdDoseFormatted(item: MedicineEntity?) {
    item?.let {
        text = "THIRD DOSE:\t${item?.dose3}"
    }
}

@BindingAdapter("thirdTimeFormatted")
fun TextView.setThirdTimeFormatted(item: MedicineEntity?) {
    item?.let {
        text = "TIME:\t${item?.time3}"
    }
}

@BindingAdapter("fourthDoseFormatted")
fun TextView.setFourthDoseFormatted(item: MedicineEntity?) {
    item?.let {
        text = "FOURTH DOSE:\t${item?.dose4}"
    }
}

@BindingAdapter("fourthTimeFormatted")
fun TextView.setFourthTimeFormatted(item: MedicineEntity?) {
    item?.let {
        text = "TIME:\t${item?.time4}"
    }
}


