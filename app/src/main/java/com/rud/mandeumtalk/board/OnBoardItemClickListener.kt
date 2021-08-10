package com.rud.mandeumtalk.board

import android.view.View

interface OnBoardItemClickListener {

    fun onItemClick (holder : BoardAdapter.ViewHolder?, view : View?, position : Int) {

    }
}