package com.thejan.assessment.digikraft_assignment.bike

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.thejan.assessment.digikraft_assignment.R
import com.thejan.assessment.digikraft_assignment.data.model.Features
import com.thejan.assessment.digikraft_assignment.databinding.FragmentDetailsBinding
import com.thejan.assessment.digikraft_assignment.main.MainViewModel


class DetailsFragment : Fragment(), OnMapReadyCallback {

    private lateinit var binding: FragmentDetailsBinding
    private lateinit var location: LatLng
    private lateinit var mapFragment: SupportMapFragment
    private val sharedViewModel: MainViewModel by activityViewModels()
    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap
    private lateinit var features: Features

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        loadMap(savedInstanceState)
    }

    private fun init() {
        arguments?.getParcelable<Features>("features")?.let {
            features = it
        }

        binding.tvLabel.text = features.properties.label
        binding.tvBikeCount.text = features.properties.bikes.toString()
        binding.tvPlacesCount.text = features.properties.freeRacks.toString()
        binding.tvDistance.text = "800m" //TODO:Distance should be calculated
    }

    private fun loadMap(savedInstanceState: Bundle?) {
        mapView = MapView(requireContext())
        mapView.onCreate(savedInstanceState)
        binding.map.addView(mapView)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        mapView.onResume()
        val latlng = LatLng(
            features.geometry.coordinates[0],
            features.geometry.coordinates[1]
        )

        googleMap.addMarker(
            MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_bike))
                .position(
                    latlng
                )
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 8f))
    }
}