package com.project.mymedications

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.mymedications.databinding.FragmentViewMedicationsBinding

/**
 * A simple [Fragment] subclass.
 */
class ViewMedicationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentViewMedicationsBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_view_medications, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = MedicineDatabase.getInstance(application).medicineDatabaseDao

        val viewModelFactory = ViewMedicationViewModelFactory(dataSource, application)

        val viewMedicationViewModel =
            ViewModelProvider(this, viewModelFactory)
                .get(ViewMedicationViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewMedicationViewModel = viewMedicationViewModel

        val adapter = MedicineAdapter(MedicineEntityListener { medId ->
            viewMedicationViewModel.onMedicineEntityClicked(medId)
        }, allMeds = mutableListOf(MedicineEntity()))

        viewMedicationViewModel.navigateToMedicineDetail.observe(
            viewLifecycleOwner,
            Observer { medicine ->
                medicine?.let {
                    this.findNavController().navigate(
                        ViewMedicationFragmentDirections
                            .actionViewMedicationFragmentToMedicineDetailFragment(medicine)
                    )
                    viewMedicationViewModel.onMedicineDetailNavigated()
                }
            })

        binding.medicineList.adapter = adapter

        viewMedicationViewModel.medicines.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.searchMedicine.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
                adapter.filter.filter(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

        })

        (activity as AppCompatActivity).supportActionBar?.title =
            getString(R.string.title_medicine_list)

        binding.floatingActionButton.setOnClickListener {
            this.findNavController()
                .navigate(R.id.action_viewMedicationFragment_to_addMedicationFragment)
        }

        return binding.root
    }
}

