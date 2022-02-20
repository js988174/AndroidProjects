package com.mandeum.dessert39.Main.Order.selectShop.select

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.Order.OrderShopMenuModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuAdapter
import com.mandeum.dessert39.Main.Order.sub.Adapter.OrderMenuModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentAidBinding
import com.mandeum.dessert39.databinding.FragmentNearShopBinding
import com.mandeum.dessert39.databinding.FragmentOrderSelectShopBinding
import kotlin.concurrent.thread


class NearShopFragment : Fragment() {

    private var _binding: FragmentNearShopBinding? = null
    private val binding get() = _binding!!
    lateinit var thread : HomeActivity
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0
    private var count: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNearShopBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val selectShopModel: ArrayList<SelectShopModel> = ArrayList()


        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

        val S : String ?= null

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

        thread(start = true) {
            val storeList = ArrayList<SelectShopModel>()
            if (latitude == 0.0 || longitude == 0.0) {
                storeList.clear()
                thread.runOnUiThread {
                    Toast.makeText(requireContext(), "성공", Toast.LENGTH_SHORT).show()
                }
            } else {
                val shopList: OrderShopMenuModel = ServerApi.AllStore(token = token.toString(), latitude=latitude , longitude = longitude, part = S.toString())
                if (shopList.connection) {
                    if (shopList.errCode == "0000") {
                        for (i in shopList.list) {
                            thread.runOnUiThread {
                                val rv: RecyclerView = binding.nearRecyclerView
                                rv.adapter = SelectShopAdapter(
                                    selectShopModel = shopList.list, requireContext()
                                )
                                rv.layoutManager =
                                    LinearLayoutManager(
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
                                "storeData.errCode = ${shopList.errCode}",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                } else {
                    thread.runOnUiThread {
                        Toast.makeText(
                            requireContext(),
                            "shopList.isConnect = ${shopList.connection}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}