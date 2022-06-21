package com.mandeum.dessert39.Main.Order.sub

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.Order.CategoryModel
import com.mandeum.dessert39.Login.ServerApi.Model.Order.DessertListModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.Main.Order.sub.Adapter.SubMenuAdapter
import com.mandeum.dessert39.databinding.FragmentDessertBinding
import kotlin.concurrent.thread


class DessertFragment : Fragment() {

       private var _binding: FragmentDessertBinding? = null
       private val binding get() = _binding!!
       lateinit var thread : HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
       _binding = FragmentDessertBinding.inflate(layoutInflater)
        thread = context as HomeActivity

        val dessert : RecyclerView = binding.dessertRecyclerView

        val sub_menu_recyclerView: RecyclerView = binding.subMenuRecyclerView

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken",  "")

        thread(start = true) {
            val categoryModel: CategoryModel = ServerApi.Category()

            if (categoryModel.connection) {
                if (categoryModel.errCode == "0000") {
                    val dessertListModel: DessertListModel = ServerApi.dessertList(token.toString())
                    if (dessertListModel.errCode == "0000") {
                        thread.runOnUiThread {
                            sub_menu_recyclerView.adapter = SubMenuAdapter(
                                requireContext(),
                                subMenuItem = categoryModel.list,
                                menuItem = dessertListModel.list,
                                dessert
                            )
                            sub_menu_recyclerView.layoutManager =
                                LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )

                            val len:Int = dessertListModel.list.toString().length

                            val MAX_LEN = 2000
                            if (len > MAX_LEN) {
                                var pos = 0
                                var nextPos = 0
                                while (pos < len) {
                                    nextPos += MAX_LEN
                                    Log.e(
                                        "dessert2",
                                        dessertListModel.list.toString().substring(pos, if (nextPos > pos) pos else nextPos)
                                    )
                                    pos = nextPos
                                }
                            } else {
                                Log.e("dessert2",  dessertListModel.list.toString())
                            }


                            Log.d("category", categoryModel.toString())
                            Log.d("dessert", dessertListModel.list.toString())
                        }
                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "${categoryModel.connection}오류",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            Log.d("category", categoryModel.toString())

        }





        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}