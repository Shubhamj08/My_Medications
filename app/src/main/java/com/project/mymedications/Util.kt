package com.project.mymedications

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
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

@BindingAdapter("doseVisibility1")
fun CardView.setDoseVisibility1(item: MedicineEntity?){
    item?.let {
        if(item.dose1 != ""){
            visibility = View.VISIBLE
        }
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

@BindingAdapter("doseVisibility2")
fun CardView.setDoseVisibility2(item: MedicineEntity?){
    item?.let {
        if(item.dose2 != ""){
            visibility = View.VISIBLE
        }
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

@BindingAdapter("doseVisibility3")
fun CardView.setDoseVisibility3(item: MedicineEntity?){
    item?.let {
        if(item.dose3 != ""){
            visibility = View.VISIBLE
        }
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

@BindingAdapter("doseVisibility4")
fun CardView.setDoseVisibility4(item: MedicineEntity?){
    item?.let {
        if(item.dose4 != ""){
            visibility = View.VISIBLE
        }
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


