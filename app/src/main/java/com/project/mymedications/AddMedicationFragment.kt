package com.project.mymedications

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.mymedications.databinding.FragmentAddMedicationBinding


/**
 * A simple [Fragment] subclass.
 */
class AddMedicationFragment : Fragment() {

    private val medicineDetails: MedicineDetails = MedicineDetails("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding:FragmentAddMedicationBinding= DataBindingUtil.inflate(inflater,R.layout.fragment_add_medication, container, false)

        binding?.medicineDetails = this.medicineDetails



        val application = requireNotNull(this.activity).application
        val dataSource = MedicineDatabase.getInstance(application).medicineDatabaseDao

        val viewModelFactory = AddMedicationViewModelFactory(dataSource, application)

        val addMedicationViewModel =
            ViewModelProvider(this, viewModelFactory)
            .get(AddMedicationViewModel::class.java)

        binding.setLifecycleOwner(this)
        binding.addMedicationViewModel = addMedicationViewModel


        binding.doneButton.setOnClickListener {
            medicineDetails.medName = binding.medName.text.toString()

            if (medicineDetails.medName != "") {

            medicineDetails.medDesc = binding.medDescription.text.toString()
            medicineDetails.dose1 = binding.dose1.text.toString()
            medicineDetails.time1 = binding.timeDose1.text.toString()
            medicineDetails.dose2 = binding.dose2.text.toString()
            medicineDetails.time2 = binding.timeDose2.text.toString()
            medicineDetails.dose3 = binding.dose3.text.toString()
            medicineDetails.time3 = binding.timeDose3.text.toString()
            medicineDetails.dose4 = binding.dose4.text.toString()
            medicineDetails.time4 = binding.timeDose4.text.toString()

                addMedicationViewModel.onDonePressed(medicineDetails)

                this.findNavController()
                    .navigate(R.id.action_addMedicationFragment_to_viewMedicationFragment)
            }
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_add_medicine)

           binding.seeMedicines.setOnClickListener {
               this.findNavController()
                   .navigate(R.id.action_addMedicationFragment_to_viewMedicationFragment)
           }

        return binding.root
    }
}
