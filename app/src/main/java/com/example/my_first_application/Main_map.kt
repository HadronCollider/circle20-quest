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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
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
                for (location in locationResult.locations){
                    Toast.makeText(this@Main_map, "cicle: $location", Toast.LENGTH_SHORT).show()
                }
            }
        }

        mLocationRequest = LocationRequest.create();
        mLocationRequest.interval = 5000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.fastestInterval = 3000



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
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.requestLocationUpdates(
                mLocationRequest,
                locationCallback,
                Looper.getMainLooper()
        )
    }


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

        val SPB = LatLng(59.57, 30.19)
        mMap.addMarker(MarkerOptions().position(SPB).title("Marker in SPB"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SPB))
        map.isMyLocationEnabled = true
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this)

        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    Log.d("TESTEST", "$location")
                    Toast.makeText(this, "Loc $location", Toast.LENGTH_LONG).show()
                    // Got last known location. In some rare situations this can be null.
                }

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
            spb.latitude = 59.57
            spb.longitude = 30.19
            val loc = fusedLocationClient.lastLocation.addOnSuccessListener { res_loc: Location? ->
                if (res_loc != null) {
                    Toast.makeText(this, "Rasstoyanie is ${res_loc.distanceTo(spb) / 1000} km", Toast.LENGTH_LONG).show()
                }
            }
        }


    }
    //val  triangleCoords = arrayListOf(
    // [25.774, 80.19],
    //[18.466, 66.118],
    //[32.321, 64.757])

    // val quadro= Polygon(triangleCoords)
    //val polygon = Polygon(PolygonOptions()
            //.add(LatLng(0.0,0.0), LatLng(0.0, 60.0), LatLng(60.0, 0.0), LatLng(60.0, 60.0))
            //.strokeColor(Color.RED)
            //.fillColor(Color.BLUE))


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
            spb.latitude = 59.57
            spb.longitude = 30.19
            val loc = fusedLocationClient.lastLocation.addOnSuccessListener { res_loc: Location? ->
                if (res_loc != null) {
                    Toast.makeText(this, "Rasstoyanie is ${res_loc.distanceTo(spb) / 1000} km", Toast.LENGTH_LONG).show()
                    //if (containsLocation(res_loc.longitude, res_loc.latitude, polygon)) Toast.makeText(this, "You are in polygon", Toast.LENGTH_LONG).show()
                    //else Toast.makeText(this, "You arent in polygon", Toast.LENGTH_LONG).show()
                }
            }
        }
        return false
    }



}

