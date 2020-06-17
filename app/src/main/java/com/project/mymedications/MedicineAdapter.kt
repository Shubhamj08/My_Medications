package com.project.mymedications

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.project.mymedications.databinding.MedicationItemViewBinding


class MedicineAdapter(val clickListener: MedicineEntityListener, val allMeds:List<MedicineEntity>) :
    ListAdapter<MedicineEntity, MedicineAdapter.ViewHolder>(MedicineEntityDiffCallback()),
    Filterable {

    var medList = arrayListOf<MedicineEntity>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!, clickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder(val binding: MedicationItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(
            item: MedicineEntity,
            clickListener: MedicineEntityListener
        ) {
            binding.medicine = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =
                    MedicationItemViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: Filter.FilterResults
            ) {
                medList = filterResults.values as ArrayList<MedicineEntity>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence.toString().toLowerCase()
                val filterResults = Filter.FilterResults()
                filterResults.values =
                    if (queryString == null || queryString.isEmpty()) {
                        allMeds
                    } else medList.filter {
                        it.medName.toLowerCase().contains(queryString)
                    }
                return filterResults
            }
        }
    }


}

class MedicineEntityDiffCallback() : DiffUtil.ItemCallback<MedicineEntity>() {
    override fun areItemsTheSame(oldItem: MedicineEntity, newItem: MedicineEntity): Boolean {
        return oldItem.medId == newItem.medId
    }

    override fun areContentsTheSame(oldItem: MedicineEntity, newItem: MedicineEntity): Boolean {
        return oldItem == newItem
    }
}

class MedicineEntityListener(val clickListener: (medicineId: Long) -> Unit) {
    fun onClick(medicine: MedicineEntity) = clickListener(medicine.medId)
}