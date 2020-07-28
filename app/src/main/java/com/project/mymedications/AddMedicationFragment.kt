package com.project.mymedications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.mymedications.databinding.FragmentAddMedicationBinding


/**
 * A simple [Fragment] subclass.
 */
class AddMedicationFragment : Fragment() {

    val medicineDetails: MedicineDetails = MedicineDetails("")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentAddMedicationBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_medication, container, false)

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

            if (medicineDetails.medName.isNotEmpty()) {

                if (binding.medDescription.text.toString().isNotEmpty()) medicineDetails.medDesc =
                    binding.medDescription.text.toString()

                if (binding.dose1.text.toString().isNotEmpty()) medicineDetails.dose1 =
                    binding.dose1.text.toString()
                if (binding.timeDose1.text.toString().isNotEmpty()) medicineDetails.hours1 =
                    binding.timeDose1.text.toString()
                if (binding.timeDose1b.text.toString().isNotEmpty()) medicineDetails.mins1 =
                    binding.timeDose1b.text.toString()
                if (binding.timeDose1c.text.toString().isNotEmpty()) medicineDetails.meridiem1 =
                    binding.timeDose1c.text.toString()

                if (binding.dose2.text.toString().isNotEmpty()) medicineDetails.dose2 =
                    binding.dose2.text.toString()
                if (binding.timeDose2.text.toString().isNotEmpty()) medicineDetails.hours2 =
                    binding.timeDose2.text.toString()
                if (binding.timeDose2b.text.toString().isNotEmpty()) medicineDetails.mins2 =
                    binding.timeDose2b.text.toString()
                if (binding.timeDose2c.text.toString().isNotEmpty()) medicineDetails.meridiem2 =
                    binding.timeDose2c.text.toString()

                if (binding.dose3.text.toString().isNotEmpty()) medicineDetails.dose3 =
                    binding.dose3.text.toString()
                if (binding.timeDose3.text.toString().isNotEmpty()) medicineDetails.hours3 =
                    binding.timeDose3.text.toString()
                if (binding.timeDose3b.text.toString().isNotEmpty()) medicineDetails.mins3 =
                    binding.timeDose3b.text.toString()
                if (binding.timeDose3c.text.toString().isNotEmpty()) medicineDetails.meridiem3 =
                    binding.timeDose3c.text.toString()

                if (binding.dose4.text.toString().isNotEmpty()) medicineDetails.dose4 =
                    binding.dose4.text.toString()
                if (binding.timeDose4.text.toString().isNotEmpty()) medicineDetails.hours4 =
                    binding.timeDose4.text.toString()
                if (binding.timeDose4b.text.toString().isNotEmpty()) medicineDetails.mins4 =
                    binding.timeDose4b.text.toString()
                if (binding.timeDose4c.text.toString().isNotEmpty()) medicineDetails.meridiem4 =
                    binding.timeDose4c.text.toString()

                binding.invalidateAll()

                val immh =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                immh.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)

                addMedicationViewModel.onDonePressed(medicineDetails)
                this.findNavController()
                    .navigate(R.id.action_addMedicationFragment_to_viewMedicationFragment)

            }
        }

        binding.seeMedicines.setOnClickListener {

            val immh = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            immh.hideSoftInputFromWindow(binding.doneButton.windowToken, 0)

            this.findNavController()
                .navigate(R.id.action_addMedicationFragment_to_viewMedicationFragment)
        }

        binding.addDose.setOnClickListener {
            binding.apply {

                when {
                    dose1Card.visibility == View.GONE -> {
                        dose1Card.visibility = View.VISIBLE
                    }
                    dose2Card.visibility == View.GONE -> {
                        dose2Card.visibility = View.VISIBLE
                    }
                    dose3Card.visibility == View.GONE -> {
                        dose3Card.visibility = View.VISIBLE
                    }
                    dose4Card.visibility == View.GONE -> {
                        dose4Card.visibility = View.VISIBLE

                        addDose.visibility = View.GONE
                        addDoseText.visibility = View.GONE
                    }
                }

            }
        }

        createChannel(
            getString(R.string.notification_channel_ID),
            getString(R.string.notification_channel_name)
        )

        return binding.root
    }

    private fun createChannel(channelId: String, channelName: String){

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.BLUE
            notificationChannel.enableVibration(true)
            notificationChannel.description  = "Time for medicine"

            val notificationManager = requireActivity().getSystemService(
                NotificationManager::class.java
            )

            notificationManager?.createNotificationChannel(notificationChannel)
        }
    }
}
