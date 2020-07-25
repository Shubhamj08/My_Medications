package com.project.mymedications

import android.annotation.SuppressLint
import android.view.View
import android.widget.LinearLayout
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
fun CardView.setDoseVisibility1(item: MedicineEntity?) {
    item?.let {
        if (item.dose1 != "" || item.time1 != "") {
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
        text = item.hours1 + ":" + item.mins1 + " " + item.meridiem1
    }
}

@BindingAdapter("firstHour")
fun TextView.hours(item: MedicineEntity?){
    item?.let{
        text = item.hours1
    }
}

@BindingAdapter("firstMin")
fun TextView.mins(item: MedicineEntity?){
    item?.let{
        text = item.mins1
    }
}

@BindingAdapter("firstMeridiem")
fun TextView.meridiem(item: MedicineEntity?){
    item?.let{
        text = item.meridiem1
    }
}

@BindingAdapter("doseVisibility2")
fun CardView.setDoseVisibility2(item: MedicineEntity?) {
    item?.let {
        if (item.dose2 != "" || item.time2 != "") {
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
        text = item.hours2 + ":" + item.mins2 + " " + item.meridiem2
    }
}

@BindingAdapter("secondHour")
fun TextView.hours2(item: MedicineEntity?){
    item?.let{
        text = item.hours2
    }
}

@BindingAdapter("secondMin")
fun TextView.mins2(item: MedicineEntity?){
    item?.let{
        text = item.mins2
    }
}

@BindingAdapter("secondMeridiem")
fun TextView.meridiem2(item: MedicineEntity?){
    item?.let{
        text = item.meridiem2
    }
}

@BindingAdapter("doseVisibility3")
fun CardView.setDoseVisibility3(item: MedicineEntity?) {
    item?.let {
        if (item.dose3 != "" || item.time3 != "") {
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
        text = item.hours3 + ":" + item.mins3 + " " + item.meridiem3
    }
}

@BindingAdapter("thirdHour")
fun TextView.hours3(item: MedicineEntity?){
    item?.let{
        text = item.hours3
    }
}

@BindingAdapter("thirdMin")
fun TextView.mins3(item: MedicineEntity?){
    item?.let{
        text = item.mins3
    }
}

@BindingAdapter("thirdMeridiem")
fun TextView.meridiem3(item: MedicineEntity?){
    item?.let{
        text = item.meridiem3
    }
}

@BindingAdapter("doseVisibility4")
fun CardView.setDoseVisibility4(item: MedicineEntity?) {
    item?.let {
        if (item.dose4 != "" || item.time4 != "") {
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
        text = item.hours4 + ":" + item.mins4 + " " + item.meridiem4
    }
}

@BindingAdapter("fourthHour")
fun TextView.hours4(item: MedicineEntity?){
    item?.let{
        text = item.hours4
    }
}

@BindingAdapter("fourthMin")
fun TextView.mins4(item: MedicineEntity?){
    item?.let{
        text = item.mins4
    }
}

@BindingAdapter("fourthMeridiem")
fun TextView.meridiem4(item: MedicineEntity?){
    item?.let{
        text = item.meridiem4
    }
}

@BindingAdapter("layoutVisibility1")
fun LinearLayout.setLayoutVisibility1(item: MedicineEntity?) {
    item?.let {
        if (item.dose1 != "" || item.time1 != "") {
            visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("layoutVisibility2")
fun LinearLayout.setLayoutVisibility2(item: MedicineEntity?) {
    item?.let {
        if (item.dose2 != "" || item.time2 != "") {
            visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("layoutVisibility3")
fun LinearLayout.setLayoutVisibility3(item: MedicineEntity?) {
    item?.let {
        if (item.dose3 != "" || item.time3 != "") {
            visibility = View.VISIBLE
        }
    }
}

@BindingAdapter("layoutVisibility4")
fun LinearLayout.setLayoutVisibility4(item: MedicineEntity?) {
    item?.let {
        if (item.dose4 != "" || item.time4 != "") {
            visibility = View.VISIBLE
        }
    }
}