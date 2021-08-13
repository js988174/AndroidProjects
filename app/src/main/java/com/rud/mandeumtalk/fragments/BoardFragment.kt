package com.rud.mandeumtalk.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.board.BoardAdapter
import com.rud.mandeumtalk.board.BoardModel
import com.rud.mandeumtalk.board.BoardWriteActivity
import com.rud.mandeumtalk.board.OnBoardItemClickListener
import com.rud.mandeumtalk.databinding.FragmentBoardBinding


class BoardFragment : Fragment() {

	private lateinit var binding : FragmentBoardBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)



	}

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {

		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_board, container, false)

		/*
		/////////// Start of Recycler View Area ///////////
		val layoutManager = LinearLayoutManager (activity, LinearLayoutManager.VERTICAL, false)

		binding.boardRecyclerView.layoutManager = layoutManager

		val adapter = BoardAdapter()

		adapter.items.add(BoardModel("애국가 1절", "동해물과 백두산이 마르고 닳도록 하느님이 보우하사 우리나라 만세"))
		adapter.items.add(BoardModel("애국가 2절", "남산 위에 저 소나무, 철갑을 두른 듯 바람서리 불변함은 우리 기상일세"))
		adapter.items.add(BoardModel("애국가 3절", "가을 하늘 공활한데 높고 구름 없이 밝은 달은 우리 가슴 일편단심일세"))
		adapter.items.add(BoardModel("애국가 4절", "이 기상과 이 맘으로 충성을 다하여 괴로우나 즐거우나 나라 사랑하세"))

		binding.boardRecyclerView.adapter = adapter

		adapter.listener = object : OnBoardItemClickListener {

			override fun onItemClick(holder: BoardAdapter.ViewHolder?, view: View?, position: Int) {

				val title = view?.findViewById<TextView>(R.id.input1)?.text
				val contents = view?.findViewById<TextView>(R.id.input2)?.text

				Toast.makeText(activity, "Item Click $position\nTitle : $title\nContents : $contents", Toast.LENGTH_SHORT).show()

			}
		}
		 */

		/////////// End of Recycler View Area ///////////

		// Board Write Button
		/*
		binding.boardWriteButton.setOnClickListener {
			val intent = Intent (activity, BoardWriteActivity::class.java)
			startActivity(intent)
		}
		 */


		binding.homeIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_boardFragment_self)
		}
		binding.homeText.setOnClickListener {
			it.findNavController().navigate(R.id.action_boardFragment_self)
		}

		binding.portfolioIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_portfolioFragment)
		}
		binding.portfolioText.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_portfolioFragment)
		}

		binding.boardIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_homeFragment)
		}
		binding.boardText.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_homeFragment)
		}

		binding.contactUsIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_boardFragment)
		}
		binding.contactUsText.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_boardFragment)
		}

		binding.accountIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_accountFragment)
		}
		binding.accountText.setOnClickListener {
			it.findNavController().navigate(R.id.action_guideFragment_to_accountFragment)
		}



		return binding.root
	}
}