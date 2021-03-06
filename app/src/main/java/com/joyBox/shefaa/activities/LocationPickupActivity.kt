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
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import android.widget.ProgressBar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnCheckedChanged
import butterknife.OnClick
import com.JoyBox.Shefaa.R
//import com.brainsocket.globalpages.R
//import com.brainsocket.globalpages.adapters.BusinessGuideRecyclerViewAdapter
//import com.brainsocket.globalpages.data.entities.BusinessGuide
//import com.brainsocket.globalpages.data.entities.PointEntity
//import com.brainsocket.globalpages.di.component.DaggerBusinessGuideSearchComponent
//import com.brainsocket.globalpages.di.module.BusinessGuidesModule
//import com.brainsocket.globalpages.di.ui.BusinessGuidesContract
//import com.brainsocket.globalpages.di.ui.BusinessGuidesPresenter
//import com.brainsocket.globalpages.dialogs.bottomSheetFragments.BusinessGuideSnippetBottomFragment
//import com.brainsocket.globalpages.dialogs.bottomSheetFragments.SubCategoryBottomSheet
//import com.brainsocket.globalpages.eventsBus.EventActions
//import com.brainsocket.globalpages.eventsBus.MessageEvent
//import com.brainsocket.globalpages.eventsBus.RxBus
//import com.brainsocket.mainlibrary.SupportViews.RecyclerViewDecoration.GridDividerDecoration
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
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
import com.joyBox.shefaa.entities.PointEntity
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import javax.inject.Inject


class LocationPickupActivity : BaseActivity(), GoogleMap.OnMarkerClickListener, OnMapReadyCallback, GoogleMap.OnMapClickListener {

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
                    e.startResolutionForResult(this@LocationPickupActivity,
                            REQUEST_CHECK_SETTINGS)
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }
        }
    }

    /*GPS Track Ended */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pickup_location_layout)
        ButterKnife.bind(this)

        initToolBar()

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        initLocation()
        createLocationRequest()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.location_pickup_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.location_pickup_save -> {
                if (lastLocation != null) {
                    RxBus.publish(MessageEvent(EventActions.LocationPickupActivity_Tag,
                            PointEntity(lastLocation!!.latitude, lastLocation!!.longitude)))
                    finish()

                } else {
                    Toast.makeText(baseContext, R.string.pleaseSelectLocation, Toast.LENGTH_LONG).show()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMapClick(p0: LatLng?) {
        if (p0 != null) {
            mMap.clear()
            placeMarkerOnMap(p0)
        }
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        mMap = googleMap!!
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        setUpMap()

        mMap.setOnMapClickListener(this)

    }


    /*GPS Track Started*/
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
//        if (p0 != null) {
//            if (p0.tag is BusinessGuide) {
//                val businessGuideSnippetBottomFragment = BusinessGuideSnippetBottomFragment.getNewInstance(p0.tag as BusinessGuide)
//                businessGuideSnippetBottomFragment.show(supportFragmentManager,
//                        BusinessGuideSnippetBottomFragment.BusinessGuideSnippetBottomFragment_Tag)
//            }
//        }
        return false
    }

}
