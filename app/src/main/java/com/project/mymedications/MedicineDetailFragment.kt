package com.project.mymedications

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.project.mymedications.databinding.FragmentMedicineDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class MedicineDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMedicineDetailBinding>(
            inflater, R.layout.fragment_medicine_detail, container, false
        )

        val application = requireNotNull(this.activity).application
        val arguments = MedicineDetailFragmentArgs.fromBundle(arguments)

        val dataSource = MedicineDatabase.getInstance(application).medicineDatabaseDao
        val viewModelFactory =
            MedicineDetailViewModelFactory(arguments.medicineEntityKey, dataSource)

        val medicineDetailViewModel =
            ViewModelProvider(
                this, viewModelFactory
            ).get(MedicineDetailViewModel::class.java)

        binding.medicineDetailViewModel = medicineDetailViewModel

        medicineDetailViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_LONG
                ).show()
                medicineDetailViewModel.doneShowingSnackbar()
                this.findNavController().navigate(R.id.action_medicineDetailFragment_to_homeFragment)
            }
        })

        binding.updateButton.setOnClickListener { medicine ->
            medicine?.let {
                this.findNavController().navigate(
                    MedicineDetailFragmentDirections
                        .actionMedicineDetailFragmentToUpdateMedicineFragment(arguments.medicineEntityKey)
                )
            }
        }

        binding.lifecycleOwner = this

        return binding.root
    }


}


