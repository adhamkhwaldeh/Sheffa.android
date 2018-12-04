package com.joyBox.shefaa.activities

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.location.places.ui.PlacePicker
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.joyBox.shefaa.di.component.DaggerPharmacySearchMapComponent
import com.joyBox.shefaa.di.module.PharmacyModule
import com.joyBox.shefaa.di.ui.PharmacyContract
import com.joyBox.shefaa.di.ui.PharmacyPresenter
import com.joyBox.shefaa.entities.Pharmacy
import com.joyBox.shefaa.entities.PointEntity
import com.joyBox.shefaa.fragments.bottomSheetFragments.PharmacySnippetBottomFragment
import com.joyBox.shefaa.networking.NetworkingHelper
import javax.inject.Inject

class PharmacySearchMapActivity : BaseActivity(), GoogleMap.OnMarkerClickListener, OnMapReadyCallback,
        PharmacyContract.View {

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
        private const val REQUEST_CHECK_SETTINGS = 2
        private const val PLACE_PICKER_REQUEST = 3
    }

    private lateinit var locationCallback: LocationCallback

    private lateinit var locationRequest: LocationRequest

    private var lastLocation: Location? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var locationUpdateState: Boolean = false

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar


//    @BindView(R.id.tagSearchView)
//    lateinit var tagSearchView: TagSearchView

    lateinit var mMap: GoogleMap

    var firstLocation = true

    private fun initToolBar() {
        toolbar.setTitle(R.string.Pharmacies)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                super.onLocationResult(p0)
                if (p0?.lastLocation != null) {
                    lastLocation = p0.lastLocation
                    if (firstLocation) {
                        firstLocation = false
                        placeMarkerOnMap(LatLng(lastLocation!!.latitude, lastLocation!!.longitude))
                        pharmacyPresenter.loadPharmacyList(NetworkingHelper.Pharmacy_Search_Url)
//                                pointEntity =
//                        PointEntity(lat = lastLocation!!.latitude, lng = lastLocation!!.longitude))
                    }
                }


            }
        }

    }

    private fun placeMarkerOnMap(location: LatLng) {

        val markerOptions = MarkerOptions().position(location)
//        val titleStr = getAddress(location)  // add these two lines
        markerOptions.title(resources.getString(R.string.Your))
//        currentcity = CityModel(titleStr, location, CitiesManager.getCitiesSize() == 0)
//        mMap.clear()
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f))
        mMap.addMarker(markerOptions)
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
//        mMap.isMyLocationEnabled = true

        // 2
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            // Got last known location. In some rare situations this can be null.
            // 3
            if (location != null) {
                lastLocation = location
                val currentLatLng = LatLng(location.latitude, location.longitude)
                placeMarkerOnMap(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
            }
        }

    }


    /*GPS Track Started */
    private fun startLocationUpdates() {
        //1
        if (ActivityCompat.checkSelfPermission(this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    LOCATION_PERMISSION_REQUEST_CODE)
            return
        }
        //2
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null /* Looper */)
    }

    private fun createLocationRequest() {
        // 1
        locationRequest = LocationRequest()
        // 2
        locationRequest.interval = 10000
        // 3
        locationRequest.fastestInterval = 5000
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

        // 4
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        // 5
        task.addOnSuccessListener {
            locationUpdateState = true
            startLocationUpdates()
        }
        task.addOnFailureListener { e ->
            // 6
            if (e is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    e.startResolutionForResult(this@PharmacySearchMapActivity,
                            REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    /*GPS Track Ended */


    @Inject
    lateinit var pharmacyPresenter: PharmacyPresenter


    private fun initDI() {
        val component = DaggerPharmacySearchMapComponent.builder()
//                .tagsCollectionModule(TagsCollectionModule(this))
                .pharmacyModule(PharmacyModule(this))
                .build()
        component.inject(this)

        pharmacyPresenter.attachView(this)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pharmacy_search_map_layout)
        ButterKnife.bind(this)

        initToolBar()


        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

//        businessGuideRecyclerView.adapter = BusinessGuideRecyclerViewAdapter(this
//                , DummyDataRepositories.getBusinessGuideList())

        initLocation()
        createLocationRequest()
        initDI()

    }


    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()
    }


    // 1
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            if (resultCode == Activity.RESULT_OK) {
                locationUpdateState = true
                startLocationUpdates()
            }
        }
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                val place = PlacePicker.getPlace(this, data)
                var addressText = place.name.toString()
                addressText += "\n" + place.address.toString()

                placeMarkerOnMap(place.latLng)
            }
        }
    }

    // 2
    override fun onPause() {
        super.onPause()
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // 3
    public override fun onResume() {
        super.onResume()
        if (!locationUpdateState) {
            startLocationUpdates()
        }
    }
    /*GPS Track Ended*/

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        createLocationRequest()
        Log.v("", "")

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        if (p0 != null) {
            if (p0.tag is Pharmacy) {
                val pharmacySnippetBottomFragment = PharmacySnippetBottomFragment.getNewInstance(p0.tag as Pharmacy)
                pharmacySnippetBottomFragment.show(supportFragmentManager,
                        PharmacySnippetBottomFragment.PharmacySnippetBottomFragment_Tag)
            }
        }
        return false
    }

    /*Business Guides Presenter started*/
    override fun showProgress(show: Boolean) {

    }

    override fun showEmptyView(visible: Boolean) {
        super.showEmptyView(visible)
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        super.showLoadErrorMessage(visible)
    }

    override fun onPharmacyListLoadedSuccessfully(pharmacyList: MutableList<Pharmacy>) {
        pharmacyList.forEach {
            try {
                addMarker(it, it.Pharmacy_name, PointEntity(it.latitude.toDouble(), it.longitude.toDouble()))
            } catch (ex: Exception) {

            }
        }
    }


    //    override fun showBusinessGuideProgress(show: Boolean) {
//        if (show) {
//            progressBar.visibility = View.VISIBLE
//            refreshBtn.visibility = View.GONE
//        } else {
//            progressBar.visibility = View.GONE
//            refreshBtn.visibility = View.GONE
//        }
//    }
//
//    override fun showBusinessGuideLoadErrorMessage(visible: Boolean) {
//        if (visible) {
//            progressBar.visibility = View.GONE
//            refreshBtn.visibility = View.VISIBLE
//            Toast.makeText(baseContext, R.string.NoInternetConnectionTryRefreshData, Toast.LENGTH_LONG).show()
//        } else {
//            progressBar.visibility = View.GONE
//            refreshBtn.visibility = View.GONE
//        }
//    }
//
//    override fun showBusinessGuideEmptyView(visible: Boolean) {
//        Toast.makeText(baseContext, R.string.NoDataFound, Toast.LENGTH_LONG).show()
//    }
//
//    override fun onLoadBusinessGuideListSuccessfully(businessGuideList: MutableList<BusinessGuide>) {
//        businessGuideRecyclerView.adapter = BusinessGuideRecyclerViewAdapter(this, businessGuideList)
//        findViewById<CompoundButton>(R.id.viewTypeToggle).isChecked = true
//        businessGuideList.forEach {
//            addMarker(it, it.getName(), it.locationPoint)
//        }
//        Log.v("", "")
//    }

    private fun addMarker(pharmacy: Pharmacy, title: String, pointEntity: PointEntity) {
        try {
            val marker = mMap.addMarker(MarkerOptions()
                    .position(LatLng(pointEntity.lat, pointEntity.lng))
                    .title(title)
                    .icon(BitmapDescriptorFactory
                            .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
            marker.tag = pharmacy
        } catch (ex: Exception) {
            Log.v("", "'")
        }

    }

    /*Business Guides Presenter ended*/

}
