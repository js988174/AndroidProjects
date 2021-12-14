package com.mandeum.dessert39.Main.Order.sub.Cart

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Card.CardChargeFragmentDirections
import com.mandeum.dessert39.Main.Card.animation.collapse
import com.mandeum.dessert39.Main.Card.animation.expand
import com.mandeum.dessert39.Main.Ord.CartModel
import com.mandeum.dessert39.Main.Order.OrderFragmentDirections
import com.mandeum.dessert39.Main.Order.dialog.CouponAdapter
import com.mandeum.dessert39.Main.Order.dialog.CouponFragment
import com.mandeum.dessert39.Main.Order.dialog.CouponModel
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentOrderCartBinding
import kotlinx.android.synthetic.main.card_custom_dialog.*
import kotlinx.android.synthetic.main.fragment_card_charge.*
import kotlinx.android.synthetic.main.fragment_order2.*
import kotlinx.android.synthetic.main.fragment_order_cart.*
import kotlinx.android.synthetic.main.order_cart_item.*


class OrderCartFragment : Fragment(),View.OnClickListener {

    private var _binding : FragmentOrderCartBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOrderCartBinding.inflate(layoutInflater)

        val cartModel: ArrayList<CartModel> = ArrayList()
        val rvAdapter : CartAdapter = CartAdapter(cartModel, requireContext())
        val rv : RecyclerView = binding.cartRecyclerView
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)


        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        cartModel.add(CartModel("https://ifh.cc/g/RwgS7v.png", "달고나"
        ,"ICED","GRANDE","개인컵","6.700","샷추가+1",
        "물적게","얼음 많이","500","텀블러할인5%","500","2","25.300"))

//        var totalPrice = ShoppingCart.getCart()
//            .fold(0.toDouble()) { acc, cartItem -> acc + cartItem.quantity.times(cartItem.product.price!!.toDouble()) }
//
//        count_price.text = "$${totalPrice}"




        binding.checkBtn.setOnClickListener {
                val dialog = Dialog(requireContext())
                dialog.setContentView(R.layout.dialog_order_receipt)
                dialog.findViewById<Button>(R.id.order_cancel).setOnClickListener {
                    dialog.dismiss()
                }
                dialog.findViewById<Button>(R.id.order_ok).setOnClickListener {
                    val action = OrderCartFragmentDirections.actionOrderCartFragmentToPaymentFragment()
                    findNavController().navigate(action)
                    dialog.dismiss()
                }
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCanceledOnTouchOutside(false)
                dialog.setCancelable(false)
                dialog.show()
        }

        binding.orderUp2.setOnClickListener(this)
        binding.upText2.setOnClickListener(this)

        binding.deleteBtn.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_menu_all_delete)
            dialog.findViewById<Button>(R.id.menu_cancel).setOnClickListener {
                dialog.dismiss()
            }
            dialog.findViewById<Button>(R.id.menu_all_delete).setOnClickListener {
                dialog.dismiss()
            }
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(false)
            dialog.show()
        }


        return binding.root
    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                R.id.order_up2, R.id.upText2 -> {
                    if (detail2.visibility == View.GONE) {
                        detail2.expand(scrollView = nested_scroll_view)
                        upText2.text = "보기"
                        order_up2.setImageResource(R.drawable.down)
                    } else { //VISIBLE
                        detail2.collapse()
                        upText2.text = "접기"
                        order_up2.setImageResource(R.drawable.up)
                    }
                }
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}