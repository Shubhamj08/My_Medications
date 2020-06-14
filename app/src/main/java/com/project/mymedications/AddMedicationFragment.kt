package com.project.mymedications

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
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

        binding.medicineDetails = this.medicineDetails



        val application = requireNotNull(this.activity).application
        val dataSource = MedicineDatabase.getInstance(application).medicineDatabaseDao

        val viewModelFactory = AddMedicationViewModelFactory(dataSource, application)

        val addMedicationViewModel =
            ViewModelProvider(this, viewModelFactory)
            .get(AddMedicationViewModel::class.java)

        binding.lifecycleOwner = this
        binding.addMedicationViewModel = addMedicationViewModel


        binding.doneButton.setOnClickListener {

            binding.medName.requestFocus()
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(binding.medName, 0)

            medicineDetails.medName = binding.medName.text.toString()

            if (medicineDetails.medName != "") {

                if(binding.medDescription.text.toString() != "") {
                    medicineDetails.medDesc = binding.medDescription.text.toString()
                }
                if (binding.dose1.text.toString() != "") {
                    medicineDetails.dose1 = binding.dose1.text.toString()
                }
                if (binding.timeDose1.text.toString() != "") {
                    medicineDetails.time1 = binding.timeDose1.text.toString()
                }
                if (binding.dose2.text.toString() != "") {
                    medicineDetails.dose2 = binding.dose2.text.toString()
                }
                if (binding.timeDose2.text.toString() != "") {
                    medicineDetails.time2 = binding.timeDose2.text.toString()
                }
                if (binding.dose3.text.toString() != "") {
                    medicineDetails.dose3 = binding.dose3.text.toString()
                }
                if (binding.timeDose3.text.toString() != "") {
                    medicineDetails.time3 = binding.timeDose3.text.toString()
                }
                if (binding.dose4.text.toString() != "") {
                    medicineDetails.dose4 = binding.dose4.text.toString()
                }
                if (binding.timeDose4.text.toString() != "") {
                    medicineDetails.time4 = binding.timeDose4.text.toString()
                }
                binding.invalidateAll()

                val immh = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                immh.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)

                addMedicationViewModel.onDonePressed(medicineDetails)
                this.findNavController()
                    .navigate(R.id.action_addMedicationFragment_to_viewMedicationFragment)

            }
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_add_medicine)

           binding.seeMedicines.setOnClickListener {

               val immh = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
               immh.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)

               this.findNavController()
                   .navigate(R.id.action_addMedicationFragment_to_viewMedicationFragment)
           }

        binding.addDose1.setOnClickListener {
            binding.apply{
                dose1Label.visibility = View.VISIBLE
                dose1.visibility = View.VISIBLE
                timeDose1.visibility = View.VISIBLE

                addDose2.visibility = View.VISIBLE
                addDoseText2.visibility =View.VISIBLE

                addDoseText1.visibility =View.GONE
                addDose1.visibility = View.GONE
            }
        }

        binding.addDose2.setOnClickListener {
            binding.apply{
                dose2Label.visibility = View.VISIBLE
                dose2.visibility = View.VISIBLE
                timeDose2.visibility = View.VISIBLE

                addDose3.visibility = View.VISIBLE
                addDoseText3.visibility =View.VISIBLE

                addDoseText2.visibility =View.GONE
                addDose2.visibility = View.GONE
            }
        }

        binding.addDose3.setOnClickListener {
            binding.apply{
                dose3Label.visibility = View.VISIBLE
                dose3.visibility = View.VISIBLE
                timeDose3.visibility = View.VISIBLE

                addDose4.visibility = View.VISIBLE
                addDoseText4.visibility =View.VISIBLE

                addDoseText3.visibility =View.GONE
                addDose3.visibility = View.GONE
            }
        }

        binding.addDose4.setOnClickListener {
            binding.apply{
                dose4Label.visibility = View.VISIBLE
                dose4.visibility = View.VISIBLE
                timeDose4.visibility = View.VISIBLE

                addDoseText4.visibility =View.GONE
                addDose4.visibility = View.GONE
            }
        }

        return binding.root
    }
}
