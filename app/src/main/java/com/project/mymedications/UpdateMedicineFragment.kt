package com.project.mymedications

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.mymedications.databinding.UpdateMedicineFragmentBinding
import kotlinx.android.synthetic.main.fragment_medicine_detail.*

class UpdateMedicineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: UpdateMedicineFragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.update_medicine_fragment, container, false)

        val addMedicationFragment = AddMedicationFragment()

        val medUpDetails = addMedicationFragment.medicineDetails

        binding.medicineDetails = medUpDetails

        val application = requireNotNull(this.activity).application
        val arguments = UpdateMedicineFragmentArgs.fromBundle(arguments)

        val dataSource = MedicineDatabase.getInstance(application).medicineDatabaseDao
        val viewModelFactory =
            UpdateMedicineViewModelFactory(arguments.medicineEntityKey, dataSource)

        val updateMedicineViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(UpdateMedicineViewModel::class.java)

        binding.updateMedicineViewModel = updateMedicineViewModel

        binding.updateButton.setOnClickListener {

            binding.medName.requestFocus()
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.medName, 0)

            medUpDetails.medName = binding.medName.text.toString()

            if (medUpDetails.medName != "") {

                if (binding.medDescription.text.toString() != "") {
                    medUpDetails.medDesc = binding.medDescription.text.toString()
                }
                if (binding.dose1.text.toString() != "") {
                    medUpDetails.dose1 = binding.dose1.text.toString()
                }
                if (binding.timeDose1.text.toString() != "") {
                    medUpDetails.time1 = binding.timeDose1.text.toString()
                }
                if (binding.dose2.text.toString() != "") {
                    medUpDetails.dose2 = binding.dose2.text.toString()
                }
                if (binding.timeDose2.text.toString() != "") {
                    medUpDetails.time2 = binding.timeDose2.text.toString()
                }
                if (binding.dose3.text.toString() != "") {
                    medUpDetails.dose3 = binding.dose3.text.toString()
                }
                if (binding.timeDose3.text.toString() != "") {
                    medUpDetails.time3 = binding.timeDose3.text.toString()
                }
                if (binding.dose4.text.toString() != "") {
                    medUpDetails.dose4 = binding.dose4.text.toString()
                }
                if (binding.timeDose4.text.toString() != "") {
                    medUpDetails.time4 = binding.timeDose4.text.toString()
                }
                binding.invalidateAll()

                val immh =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                immh.hideSoftInputFromWindow(binding.updateButton.windowToken, 0)

                updateMedicineViewModel.onUpdateButtonPressed(medUpDetails)
                this.findNavController()
                    .navigate(R.id.action_updateMedicineFragment_to_viewMedicationFragment)

            }
        }

        fun addButtonVisibility(){
            binding.apply {
                if (layout1.visibility == View.VISIBLE && layout2.visibility == View.VISIBLE &&
                    layout3.visibility == View.VISIBLE && layout4.visibility == View.VISIBLE
                ){
                    addDose.visibility = View.GONE
                    addDoseText.visibility = View.GONE
                }
                else{
                    addDose.visibility = View.VISIBLE
                    addDoseText.visibility = View.VISIBLE
                }
            }
        }

        addButtonVisibility()

        binding.addDose.setOnClickListener {
            binding.apply {
                when {
                    layout1.visibility == View.GONE -> {
                        layout1.visibility = View.VISIBLE
                    }
                    layout2.visibility == View.GONE -> {
                        layout2.visibility = View.VISIBLE
                    }
                    layout3.visibility == View.GONE -> {
                        layout3.visibility = View.VISIBLE
                    }
                    else -> {
                        layout4.visibility = View.VISIBLE
                    }
                }
            }
            addButtonVisibility()
        }

        binding.lifecycleOwner = this

        return binding.root
    }

}