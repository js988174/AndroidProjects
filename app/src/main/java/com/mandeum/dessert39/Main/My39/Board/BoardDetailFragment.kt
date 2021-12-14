package com.mandeum.dessert39.Main.My39.Board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.mandeum.dessert39.Main.My39.Event.EventDetailFragmentArgs
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentBoardDetailBinding


class BoardDetailFragment : Fragment() {

    private var _binding: FragmentBoardDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBoardDetailBinding.inflate(layoutInflater)

        val argument: BoardDetailFragmentArgs by navArgs()
        val title: String = argument.title1
        val title2: String = argument.title2
        val date: String = argument.date
        val content: String = argument.content
        val image: String? = argument.image

        binding.emergency.text = title
        binding.boardTitle.text = title2
        binding.date.text = date
        binding.content.text = content

        val boardImage: ImageView = binding.boardImage
        if (image.isNullOrEmpty()) {
            boardImage.isGone = true
        } else {
            Glide.with(requireContext()).load(image).into(boardImage)
        }

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_boardDetailFragment_to_orderFragment)
        }

        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_boardDetailFragment_to_homeFragment)
        }
        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_boardDetailFragment_to_cardFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_boardDetailFragment_to_alarmFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}