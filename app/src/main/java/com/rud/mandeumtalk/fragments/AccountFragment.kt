package com.rud.mandeumtalk.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.auth.LoginActivity
import com.rud.mandeumtalk.board.BoardModel
import com.rud.mandeumtalk.board.HateListRVAdapter
import com.rud.mandeumtalk.contentsList.BookmarkRVAdapter
import com.rud.mandeumtalk.databinding.FragmentAccountBinding
import com.rud.mandeumtalk.contentsList.ContentModel

class AccountFragment : Fragment() {

	private lateinit var auth : FirebaseAuth
	private lateinit var binding : FragmentAccountBinding
	lateinit var alertDialog : AlertDialog
	lateinit var builder : AlertDialog.Builder

	// Bookmark Recyclerview
	val bookmarkIdList = mutableListOf<String>()
	val items = ArrayList<ContentModel>()
	val itemKeyList = ArrayList<String>()
	lateinit var rvAdapter: BookmarkRVAdapter

	// Hate List
	val hateList = mutableListOf<String>()
	val hateItems = ArrayList<BoardModel> ()
	val hateItemKeyList = ArrayList<String> ()
	lateinit var hateRvAdapter : HateListRVAdapter

	private lateinit var callback: OnBackPressedCallback

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

		auth = Firebase.auth
		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)

		getBookmarkData()

		// Bookmark
		rvAdapter = BookmarkRVAdapter(requireContext(), items, itemKeyList, bookmarkIdList)
		val rv : RecyclerView = binding.bookmarkRV
		rv.adapter = rvAdapter

		rv.layoutManager = GridLayoutManager(requireContext(), 3)

		// Hate List

		getHateListData()
		hateRvAdapter = HateListRVAdapter(requireContext(), hateItems, hateItemKeyList, hateList)
		val hateRv : RecyclerView = binding.hateListRV
		hateRv.adapter = hateRvAdapter
		hateRv.layoutManager = GridLayoutManager(requireContext(), 3)

		binding.homeIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_guideFragment)
		}
		binding.homeText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_guideFragment)
		}

		binding.portfolioIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_portfolioFragment)
		}
		binding.portfolioText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_portfolioFragment)
		}

		binding.boardIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_homeFragment)
		}
		binding.boardText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_homeFragment)
		}

		binding.contactUsIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_boardFragment)
		}
		binding.contactUsText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_to_boardFragment)
		}

		binding.accountIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_self)
		}
		binding.accountText.setOnClickListener {
			it.findNavController().navigate(R.id.action_accountFragment_self)
		}


		binding.accountBtnLogout.setOnClickListener {
			showdialog()
		}

		val currentUserEmail : String = auth.currentUser?.email.toString()

		if (currentUserEmail == "null" || currentUserEmail == "") {
			binding.userEmail.text = "비회원 사용자"
		} else {
			binding.userEmail.text = currentUserEmail
		}

		return binding.root
	}

	private fun showdialog() {
		try{
			var str_tittle = "로그아웃"
			var str_message = "정말 로그아웃 하시겠습니까?"
			var str_buttonOK = "로그아웃"
			var str_buttonNO = "취소"

			builder = AlertDialog.Builder(getContext())
			builder.setTitle(str_tittle) //팝업창 타이틀 지정
			builder.setIcon(R.drawable.accountmainpink) // 팝업창 이미지
			builder.setMessage(str_message) //팝업창 내용 지정
//			builder.setCancelable(false) //외부 레이아웃 클릭시도 팝업창이 사라지지않게 설정
			builder.setPositiveButton(str_buttonOK, DialogInterface.OnClickListener { dialog, which ->
				auth.signOut()
				Toast.makeText(context, "로그아웃", Toast.LENGTH_SHORT).show()
				val intent = Intent(activity, LoginActivity::class.java)
				intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
				startActivity(intent)
			})
			builder.setNegativeButton(str_buttonNO, DialogInterface.OnClickListener { dialog, which ->
				// TODO Auto-generated method stub
				Toast.makeText(context, "취소했습니다.", Toast.LENGTH_SHORT).show()
			})

			alertDialog = builder.create()
			try {
				alertDialog.show()
			}
			catch (e : Exception){
				e.printStackTrace()
			}
		}
		catch(e : Exception){
			e.printStackTrace()
		}
	}

	private fun getBookmarkData() {

		val postListener = object : ValueEventListener {
			override fun onDataChange(dataSnapshot: DataSnapshot) {

				bookmarkIdList.clear()

				for (dataModel in dataSnapshot.children) {
					bookmarkIdList.add(dataModel.key.toString())
					Log.d("dataModel.key", dataModel.key.toString())
				}
				getEducationData()
				getCookingData()

			}
			override fun onCancelled(databaseError: DatabaseError) {

			}
		}
		Firebase.database.getReference("Bookmark_List").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).addValueEventListener(postListener)
	}

	private fun getEducationData () {

		val postListener = object : ValueEventListener {
			override fun onDataChange(dataSnapshot: DataSnapshot) {

				items.clear()
				for (dataModel in dataSnapshot.children) {

					val item = dataModel.getValue(ContentModel::class.java)

					if(bookmarkIdList.contains(dataModel.key.toString())) {

						items.add(item!!)
						itemKeyList.add(dataModel.key.toString())
					}
				}
				rvAdapter.notifyDataSetChanged()
			}
			override fun onCancelled(databaseError: DatabaseError) {

			}
		}
		Firebase.database.getReference("Education").addValueEventListener(postListener)
	}

	private fun getCookingData () {

		val postListener = object : ValueEventListener {
			override fun onDataChange(dataSnapshot: DataSnapshot) {

//				items.clear()
				for (dataModel in dataSnapshot.children) {

					val item = dataModel.getValue(ContentModel::class.java)

					if(bookmarkIdList.contains(dataModel.key.toString())) {

						items.add(item!!)
						itemKeyList.add(dataModel.key.toString())
					}
				}
				rvAdapter.notifyDataSetChanged()
			}
			override fun onCancelled(databaseError: DatabaseError) {

			}
		}
		Firebase.database.getReference("Cooking").addValueEventListener(postListener)
	}

	////////////////////////////////////////////////////////////////////////////////////////////////

	private fun getHateListData () {

		val postListener = object : ValueEventListener {
			override fun onDataChange(dataSnapshot: DataSnapshot) {

				hateList.clear()

				for (dataModel in dataSnapshot.children) {
					hateList.add(dataModel.key.toString())
					Log.e("hyeonseung", dataModel.key.toString())
				}
				getBoardData ()
			}
			override fun onCancelled(databaseError: DatabaseError) {

			}
		}
		Firebase.database.getReference("Hate_List").child(FirebaseAuth.getInstance().currentUser?.uid.toString()).addValueEventListener(postListener)
		Log.w("hyeonseungson", FirebaseAuth.getInstance().currentUser?.uid.toString())

	}

	private fun getBoardData () {

		val postListener = object : ValueEventListener {
			override fun onDataChange(dataSnapshot: DataSnapshot) {

				for (dataModel in dataSnapshot.children) {

					val item = dataModel.getValue(BoardModel::class.java)

					if (hateList.contains(dataModel.key.toString())) {

						hateItems.add(item!!)
						hateItemKeyList.add(dataModel.key.toString())
					}
				}
				hateRvAdapter.notifyDataSetChanged()
			}
			override fun onCancelled(databaseError: DatabaseError) {

			}
		}
		Firebase.database.getReference("Board").addValueEventListener(postListener)
	}


}