package com.project.mymedications

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("medicineNameFormatted")
fun TextView.setMedicineNameFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.medName
    }
}

@BindingAdapter("medicineDescriptionFormatted")
fun TextView.setMedicineDescriptionFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.medDescription
    }
}

@BindingAdapter("firstDoseFormatted")
fun TextView.setFirstDoseFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.dose1
    }
}

@BindingAdapter("firstTimeFormatted")
fun TextView.setFirstTimeFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.time1
    }
}

@BindingAdapter("secondDoseFormatted")
fun TextView.setSecondDoseFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.dose2
    }
}

@BindingAdapter("secondTimeFormatted")
fun TextView.setSecondTimeFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.time2
    }
}

@BindingAdapter("thirdDoseFormatted")
fun TextView.setThirdDoseFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.dose3
    }
}

@BindingAdapter("thirdTimeFormatted")
fun TextView.setThirdTimeFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.time3
    }
}

@BindingAdapter("fourthDoseFormatted")
fun TextView.setFourthDoseFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.dose4
    }
}

@BindingAdapter("fourthTimeFormatted")
fun TextView.setFourthTimeFormatted(item: MedicineEntity?) {
    item?.let {
        text = item.time4
    }
}


