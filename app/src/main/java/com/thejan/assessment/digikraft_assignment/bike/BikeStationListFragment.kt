package com.thejan.assessment.digikraft_assignment.bike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.thejan.assessment.digikraft_assignment.R
import com.thejan.assessment.digikraft_assignment.data.model.Features
import com.thejan.assessment.digikraft_assignment.databinding.FragmentBikeStationListBinding
import com.thejan.assessment.digikraft_assignment.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BikeStationListFragment : Fragment(), StationListAdapter.OnItemActionClick {

    // Use the 'by activityViewModels()' Kotlin property delegate from the fragment-ktx artifact
    private val sharedViewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentBikeStationListBinding
    lateinit var adapter: StationListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBikeStationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupRecyclerView()
        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        sharedViewModel.getServices()
    }

    /**
     * Observer the response from viewmodel
     */
    private fun observeViewModel() {
        sharedViewModel.loadList.observe(viewLifecycleOwner) {
            adapter.setItems(ArrayList(it))
        }
        sharedViewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                getString(R.string.data_fetching_error),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    /**
     * Initiate recyclerview
     */
    private fun setupRecyclerView() {
        adapter = StationListAdapter(this)
        binding.rvStations.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvStations.adapter = adapter
    }

    /**
     * Recyclerview pass the selected item
     */
    override fun onItemClick(features: Features) {
        val bundle = bundleOf("features" to features)
        findNavController().navigate(R.id.action_startFragment_to_detailsFragment, bundle)
    }
}