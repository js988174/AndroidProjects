package com.rud.mandeumtalk.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rud.mandeumtalk.R
import com.rud.mandeumtalk.databinding.FragmentPortfolioBinding
import com.rud.mandeumtalk.portfolioList.PortfolioModel
import com.rud.mandeumtalk.portfolioList.PortfolioRVAdapter
import kotlinx.android.synthetic.main.fragment_portfolio.*



class PortfolioFragment : Fragment() {

	private lateinit var binding : FragmentPortfolioBinding

	lateinit var  rvAdapter: PortfolioRVAdapter
	val items = ArrayList<PortfolioModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)


	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {

		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_portfolio, container, false)

		rvAdapter = PortfolioRVAdapter(requireContext(), items)

		val rv : RecyclerView = binding.rv
		rv.adapter = rvAdapter

		rv.layoutManager = GridLayoutManager(requireContext(), 2)

		val items = ArrayList<PortfolioModel>()
		rvAdapter.items.add(PortfolioModel("title1", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://cjcourier.com/"))
		rvAdapter.items.add(PortfolioModel("title2", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "https://monak.kr/"))
		rvAdapter.items.add(PortfolioModel("title3", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://www.sejintec.co.kr/"))
		rvAdapter.items.add(PortfolioModel("title4", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://셀프인하우스.com/"))
		rvAdapter.items.add(PortfolioModel("title5", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://www.hyunwoopcb.com/"))
		rvAdapter.items.add(PortfolioModel("title6", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://www.oluxe.kr/"))
		rvAdapter.items.add(PortfolioModel("title6", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://www.oluxe.kr/"))
		rvAdapter.items.add(PortfolioModel("title6", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://www.oluxe.kr/"))
		rvAdapter.items.add(PortfolioModel("title6", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://www.oluxe.kr/"))
		rvAdapter.items.add(PortfolioModel("title6", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://www.oluxe.kr/"))
		rvAdapter.items.add(PortfolioModel("title6", "https://previews.123rf.com/images/karawan/karawan1307/karawan130700065/21127776-%EA%B3%BC%EC%9D%BC-%EC%95%84%EC%9D%B4%EC%BD%98.jpg", "http://www.oluxe.kr/"))


		binding.homeIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_to_guideFragment)
		}
		binding.homeText.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_to_guideFragment)
		}

		binding.portfolioIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_self)
		}
		binding.portfolioText.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_self)
		}

		binding.boardIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_to_homeFragment)
		}
		binding.boardText.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_to_homeFragment)
		}

		binding.contactUsIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_to_boardFragment)
		}
		binding.contactUsText.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_to_boardFragment)
		}

		binding.accountIcon.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_to_accountFragment)
		}
		binding.accountText.setOnClickListener {
			it.findNavController().navigate(R.id.action_portfolioFragment_to_accountFragment)
		}
		return binding.root
	}
}