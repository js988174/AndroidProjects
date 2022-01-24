package com.mandeum.dessert39.Main.Order.dialog

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mandeum.dessert39.Login.ServerApi.Model.StoreModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.Order.OrderFragment
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderFindShopBinding
import java.io.IOException
import kotlin.concurrent.thread


class OrderFindShopFragment : BottomSheetDialogFragment() {

    private var _binding : FragmentOrderFindShopBinding? = null
    private val binding get() = _binding!!

    lateinit var rvAdapter: FindShopAdapter

    private val findShopModel = ArrayList<FindShopModel>()

    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var count: Int = 0
    lateinit var thread : HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CustomAlertDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOrderFindShopBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        if (ContextCompat.checkSelfPermission(
                requireContext().applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

        } else {
            val locationManager: LocationManager =
                requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isNetworkEnabled: Boolean =
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
            val isGPSEnabled: Boolean =
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
            val locationListener = LocationListener { p0 ->
                latitude = p0.latitude
                longitude = p0.longitude
                count += 1
            }
            if (isNetworkEnabled) {
                val location: Location? =
                    locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
                if (location != null) {
                    latitude = location.latitude
                    longitude = location.longitude
                } else {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        1L,
                        1F,
                        locationListener
                    )
                    locationManager.removeUpdates(locationListener)
                }
            } else if (isGPSEnabled) {
                val location: Location? =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                if (location != null) {
                    latitude = location.latitude
                    longitude = location.longitude
                } else {
                    locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        1L,
                        1F,
                        locationListener
                    )
                    locationManager.removeUpdates(locationListener)
                }
            }
        }



        rvAdapter = FindShopAdapter(requireContext(), findShopModel)

        thread(start = true) {
            val storeList = ArrayList<FindShopModel>()
            val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
            val token = shared.getString("LoginToken", "")
            if (latitude == 0.0 || longitude == 0.0) {
                storeList.clear()
                thread.runOnUiThread {
                    Toast.makeText(requireContext(), "sds", Toast.LENGTH_SHORT).show()
                }
            } else {
                val storeData: StoreModel = ServerApi.nearbyStore(token = token.toString() ,latitude, longitude)
                if (storeData.connection) {
                    if (storeData.errCode == "0000") {
                        for (i in storeData.list) {
                            thread.runOnUiThread {
                                val rv: RecyclerView = binding.locationRe
                                rv.adapter = FindShopAdapter(
                                    requireContext(), storeData.list
                                )
                                rv.layoutManager = LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                                storeList.add(i)
                            }
                        }
                    } else {
                        thread.runOnUiThread {
                            Toast.makeText(
                                requireContext(),
                                "storeData.errCode = ${storeData.errCode}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    thread.runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "storeData.isConnect = ${storeData.connection}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


        binding.cancelBtn.setOnClickListener {
            dialog?.dismiss()
        }

//        binding.findShop.setOnClickListener {
//            val action =
//                OrderFindShopFragmentDirections.actionOrderFindShopFragmentToOrderFragment()
//            val navHostFragment =
//                requireActivity().supportFragmentManager
//                    .findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
//            navHostFragment.navController.navigate(action)
//        }


        binding.findShop.setOnClickListener {
            val action = OrderFragmentDirections.actionOrderFragmentToOrderSelectShopFragment()
            findNavController().navigate(action)
            dialog?.dismiss()

        }





        return binding.root
    }

    private fun showDialogToGetPermission() {
        AlertDialog.Builder(requireContext())
            .setMessage("권한 거절로 인해 일부기능이 제한됩니다.")
            .setPositiveButton("권한 설정하러 가기") { _, _ ->
                try {
                    val appSetIntent: Intent =
                        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            .setData(Uri.parse("package:" + requireActivity().packageName))
                    startActivity(appSetIntent)
                } catch (e: IOException) {
                    e.printStackTrace()
                    val appManageIntent =
                        Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
                    startActivity(appManageIntent)
                }
            }
            .setNegativeButton("취소") { _, _ ->
            }
            .create().show()
    }

    override fun onStart() {
        super.onStart()
        //this forces the sheet to appear at max height even on landscape
        val behavior = BottomSheetBehavior.from(requireView().parent as View)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}