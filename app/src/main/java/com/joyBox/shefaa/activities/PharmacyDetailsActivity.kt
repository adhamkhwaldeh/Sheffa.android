package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import com.joyBox.shefaa.adapters.OpenHoursAdapter
import com.joyBox.shefaa.adapters.SpecializationAdapter
import com.joyBox.shefaa.configurations.GlideApp
import com.joyBox.shefaa.dialogs.RatingDialog
import com.joyBox.shefaa.entities.Pharmacy
import com.joyBox.shefaa.entities.models.MessageReplayModel
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.views.SimpleRatingBar

class PharmacyDetailsActivity : BaseActivity() {
    companion object {
        const val PharmacyDetailsActivity_Tag = "PharmacyDetailsActivity_Tag"
    }


    lateinit var pharmacy: Pharmacy

    @BindView(R.id.OpenHoursRecyclerView)
    lateinit var OpenHoursRecyclerView: RecyclerView

    @BindView(R.id.Address)
    lateinit var Address: TextView

    @BindView(R.id.ratingBar)
    lateinit var ratingBar: SimpleRatingBar

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.ratebtn)
    lateinit var ratebtn: Button

    @BindView(R.id.image_Person)
    lateinit var image_Person: ImageView

    lateinit var mgoogleMap: GoogleMap


    fun initToolBar() {
        toolbar.setTitle(R.string.Doctor)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }


    private fun bindData() {
//        SpecializationRecyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
//        SpecializationRecyclerView.setAdapter(SpecializationAdapter(applicationContext, pharmacy.spedoctor_specialization))

        OpenHoursRecyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
        OpenHoursRecyclerView.setAdapter(OpenHoursAdapter(applicationContext, pharmacy.Pharmacy_open_hours))

        toolbar.setTitle(pharmacy.getName())
        Address.setText(pharmacy.fullAddress)
//        Price.setText(pharmacy.costs)
        ratingBar.setRating(pharmacy.rating)
        try {
            val url = pharmacy.pictureUrl
            GlideApp.with(applicationContext).load(url)/*.resize(240, 170).centerCrop().fit()*/
                    .placeholder(R.drawable.userprofile).error(R.drawable.userprofile).into(image_Person
//            , object : Callback() {
//                fun onSuccess() {
//                    Log.v("Success", "Load Image")
//                }
//
//                fun onError() {
//                    Log.v("Fail", "Load Image")
//                }
//            }
            )
        } catch (ex: Exception) {

        }

        try {
            initailMap()
        } catch (ex: Exception) {
        }

    }

    public fun initailMap() {
        val status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
        if (status != ConnectionResult.SUCCESS) { // Google Play Services are not available
            val requestCode = 10
            val dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode)
            dialog.show()
        } else {
            val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

            mapFragment.getMapAsync(OnMapReadyCallback {
                mgoogleMap = it
                mgoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                /*if (ContextCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getBaseContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    mgoogleMap.setMyLocationEnabled(true);
                }*/
                mgoogleMap.getUiSettings().setZoomControlsEnabled(true);
                mgoogleMap.getUiSettings().setCompassEnabled(true);
                mgoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
                mgoogleMap.getUiSettings().setZoomGesturesEnabled(true);
                mgoogleMap.getUiSettings().setRotateGesturesEnabled(true);
                val cameraPosition = CameraPosition.Builder()
                        .target(LatLng(pharmacy.latitude.toDouble(), pharmacy.longitude.toDouble())).zoom(12.0f).build()
                mgoogleMap.animateCamera(CameraUpdateFactory
                        .newCameraPosition(cameraPosition));
                var name = pharmacy.getName()
                if (name == null)
                    name = ""
                drawMarker(LatLng((pharmacy.latitude.toDouble()), pharmacy.longitude.toDouble()), name)

            })

        }
    }

    private fun drawMarker(cPosition: LatLng, name: String) {
        val currentPosition = cPosition
        mgoogleMap.addMarker(MarkerOptions()
                .position(currentPosition)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                .title(name))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pharmacy_details_layout)
        ButterKnife.bind(this)
        initToolBar()
        val json = intent.getStringExtra(PharmacyDetailsActivity_Tag)
        pharmacy = Gson().fromJson(json, Pharmacy::class.java)

        bindData()
    }

    @OnClick(R.id.ratebtn)
    fun onRatingButtonClick(view: View) {
        val dialog = RatingDialog.newInstance(pharmacy.pid)
        dialog.show(supportFragmentManager, RatingDialog.RatingDialog_Tag)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.doctor_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.messages -> {
                var targetId = pharmacy.Pid
                targetId = targetId.removeSuffix(",")
                IntentHelper.startReplayMessageActivity(baseContext, MessageReplayModel(targetId, pharmacy.name))
            }
        }
        return super.onOptionsItemSelected(item)
    }


}