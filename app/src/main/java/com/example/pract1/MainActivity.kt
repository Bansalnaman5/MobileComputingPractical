package com.example.pract1

import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {
    lateinit var lat:TextView
    lateinit var lon:TextView
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lat=findViewById(R.id.lat)
        lon=findViewById(R.id.lon)
        fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(this)
        getLoc();
    }
    private fun getLoc(){
//        val locationManager:LocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this
//                ,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
//            req()
//            return
//        }
//        req()
//        fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){task->
//            val location:Location?=task.result
//            if(location==null){
//                Toast.makeText(this,"No location recieved",Toast.LENGTH_SHORT).show()
//            }
//            else{
//                Toast.makeText(this,"Locatiuon aa gayi",Toast.LENGTH_SHORT).show()
//                lat.text="Latitude : "+location.latitude
//                lon.text="Longitude : "+location.longitude
//            }
//
//        }
        if(checkP()){
            val locationManager:LocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
            if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)!=PackageManager.PERMISSION_GRANTED){
                    req()
                    return
                }
                fusedLocationProviderClient.lastLocation.addOnCompleteListener(this){task->
            val location:Location?=task.result
            if(location==null){
                Toast.makeText(this,"No location recieved",Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(this,"Location aa gayi",Toast.LENGTH_SHORT).show()
                lat.text="Latitude : "+location.latitude
                lon.text="Longitude : "+location.longitude
            }

        }
            }
        }
        else{
            Toast.makeText(this,"hai hi nahi",Toast.LENGTH_SHORT).show()
        }
    }
    private fun req(){
        ActivityCompat.requestPermissions(this,
        arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),100
        )
    }
    private fun checkP():Boolean{
        if(ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false;
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==100){
            if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                getLoc()
            }
        }
    }

}