package com.mandeum.dessert39.Main.Order.sub.Cart

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mandeum.dessert39.Main.Ord.CartModel
import com.mandeum.dessert39.Main.Order.selectShop.select.SelectShopAdapter
import com.mandeum.dessert39.Main.Order.selectShop.select.SelectShopModel
import com.mandeum.dessert39.R
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import kotlinx.android.synthetic.main.fragment_order_menu_detail.view.*
import kotlinx.android.synthetic.main.order_cart_item.view.*
import kotlinx.android.synthetic.main.order_menu_item.view.*

class CartAdapter(private val cartModel : ArrayList<CartModel>, private val context : Context) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.order_cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems (cartModel[position])
    }

    override fun getItemCount(): Int {
        return cartModel.size
    }

    inner class ViewHolder (itemView : View) : RecyclerView.ViewHolder (itemView) {

        fun bindItems (item : CartModel) {

            val imageView = itemView.findViewById<ImageView>(R.id.imageView)
            val name = itemView.findViewById<TextView>(R.id.dessert_name)
            val temper = itemView.findViewById<TextView>(R.id.ice_hot)
            val size = itemView.findViewById<TextView>(R.id.size)
            val cup = itemView.findViewById<TextView>(R.id.cup_kinds)
            val price1 = itemView.findViewById<TextView>(R.id.main_price)
            val shot = itemView.findViewById<TextView>(R.id.shot)
            val water = itemView.findViewById<TextView>(R.id.wather_less)
            val ice = itemView.findViewById<TextView>(R.id.ice_much)
            val optionPrice = itemView.findViewById<TextView>(R.id.option_price)
            val discount = itemView.findViewById<TextView>(R.id.sale)
            val disPrice = itemView.findViewById<TextView>(R.id.sale_price)
            val quantity = itemView.findViewById<TextView>(R.id.number1)
            val total = itemView.findViewById<TextView>(R.id.count_price)
            val minusButton = itemView.findViewById<ConstraintLayout>(R.id.minus_btn1)
            val plusButton = itemView.findViewById<ConstraintLayout>(R.id.plus_btn1)

            name.text = item.title
            temper.text = item.temper
            size.text = item.size
            cup.text = item.cupKinds
            price1.text = item.dessertPrice
            shot.text = item.shot
            water.text = item.water
            ice.text = item.ice
            optionPrice.text = item.optionPrice
            discount.text = item.discount
            disPrice.text = item.discountPrice
            quantity.text = item.quantity
            total.text = item.totalPrice


            Glide.with(itemView)
                .load(item.image)
                .into(imageView)

//            itemView.dessert_name.text = item.product.Kname
//
//            itemView.count_price.text = item.product.price
//
//            itemView.number1.text = item.quantity.toString()



            itemView.findViewById<ImageView>(R.id.close_btn).setOnClickListener {
                val dialog = Dialog(context)
                dialog.setContentView(R.layout.dialog_menu_delete)
                dialog.findViewById<Button>(R.id.menu_cancel).setOnClickListener {
                    dialog.dismiss()
                }
                dialog.findViewById<Button>(R.id.menu_delete).setOnClickListener {
                    dialog.dismiss()
                }
                dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog.setCanceledOnTouchOutside(false)
                dialog.setCancelable(false)
                dialog.show()
            }

        }
    }
}