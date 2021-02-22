package com.example.my_first_application

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.firestore.GeoPoint
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main_map.*


class Main_map : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {

    private lateinit var mMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var mLocationRequest: LocationRequest


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_map)
        Log.d("TESTEST", "onCreate")
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        if (ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ){
            val permissions = arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            )
            ActivityCompat.requestPermissions(this, permissions, 0)
        }
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                println("locationCallback")
                locationResult ?: return

            }
        }

        mLocationRequest = LocationRequest.create();
        mLocationRequest.interval = 5000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.fastestInterval = 3000

        //firestore on
        //val db = Firebase.firestore
        //val getting=db.collection("testing").document("excursionTest")
                //.get()
                //.addOnCompleteListener { task ->
                   // if (task.isSuccessful) {
                       // val test = task.result.data
                      //  if (test != null) {
                       //     val lst = test["Latlng1"] as ArrayList<GeoPoint>
                           // testExcursion.informationForApplication=lst.map { LatLng(it.latitude, it.longitude) }.toCollection(ArrayList())

                            //testExcursion.informationForApplication = test["Latlng1"] as ArrayList<LatLng>
                           // testExcursion.point=test["point"] as ArrayList<Double>



                        //}
                   // }
                //}



    }
    fun amIConnected(): Boolean {
        val connectivityManager = this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<out String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            return
        }
        fusedLocationClient.requestLocationUpdates(
                mLocationRequest,
                locationCallback,
                Looper.getMainLooper()
        )
    }

    var i=0
    override fun onMapReady(map: GoogleMap) {
        Log.d("TESTEST", "tetette")
        mMap = map
        //if we have permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
            return
        }

        val SPB = LatLng(testExcursion.point[i][0], testExcursion.point[i][1])
        mMap.addMarker(MarkerOptions().position(SPB).title("Marker in SPB"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SPB))
        button11.setOnClickListener {
            i-=1
            mMap.clear()
            val SPB = LatLng(testExcursion.point[i][0], testExcursion.point[i][1])
            mMap.addMarker(MarkerOptions().position(SPB).title("Marker in SPB"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(SPB))

        }
        button13.setOnClickListener {
            i+=1
            mMap.clear()
            val SPB = LatLng(testExcursion.point[i][0], testExcursion.point[i][1])
            mMap.addMarker(MarkerOptions().position(SPB).title("Marker in SPB"))
            mMap.moveCamera(CameraUpdateFactory.newLatLng(SPB))
        }

        map.isMyLocationEnabled = true
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this)



        startLocationUpdates()
    }


    //if press on button in icon of user
    override fun onMyLocationClick(location: Location) {

        //if we have permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        if(!amIConnected()){
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        }
        else {
            val spb = Location("")
            spb.latitude = testExcursion.point[i][0]
            spb.longitude = testExcursion.point[i][0]
            val builder = LatLngBounds.Builder()

            for (el in testExcursion.informationForApplication[i])
             {
                builder.include(LatLng(el[0], el[1]))
            }
            val bounds = builder.build()
            val loc = fusedLocationClient.lastLocation.addOnSuccessListener { res_loc: Location? ->
                if (res_loc != null) {
                    Toast.makeText(this, "Rasstoyanie is ${res_loc.distanceTo(spb) / 1000} km", Toast.LENGTH_LONG).show()
                    val my_loc = LatLng(res_loc.latitude, res_loc.longitude)
                    val res = bounds.contains(my_loc)
                    Toast.makeText(this, "$res", Toast.LENGTH_LONG).show()
                }
            }
        }


    }



    //if press on button in top right corner
    override fun onMyLocationButtonClick(): Boolean {
        //if we have permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            return false
        }
        if(!amIConnected()){//if user have internete
            Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
        }
        else {//
            val spb = Location("")
            spb.latitude = testExcursion.point[i][0]
            spb.longitude = testExcursion.point[i][1]
            val builder = LatLngBounds.Builder()
            for (el in testExcursion.informationForApplication[i])
            {
                builder.include(LatLng(el[0], el[1]))
            }
            val bounds = builder.build()
            val loc = fusedLocationClient.lastLocation.addOnSuccessListener { res_loc: Location? ->
                if (res_loc != null) {
                    Toast.makeText(this, "Rasstoyanie is ${res_loc.distanceTo(spb) / 1000} km", Toast.LENGTH_LONG).show()
                    val my_loc = LatLng(res_loc.latitude, res_loc.longitude)
                    val res = bounds.contains(my_loc)
                    Toast.makeText(this, "$res", Toast.LENGTH_LONG).show()
                }
            }
        }
        return false
    }



}

