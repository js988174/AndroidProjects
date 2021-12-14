package com.mandeum.dessert39.Main.My39.Event

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
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentEventDetailBinding


class EventDetailFragment : Fragment() {

    private var _binding: FragmentEventDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEventDetailBinding.inflate(layoutInflater)

        val argument: EventDetailFragmentArgs by navArgs()
        val title: String = argument.title1
        val title2: String = argument.title2
        val startDate: String = argument.startDate
        val endDate: String = argument.endDate
        val content: String = argument.content
        val image: String? = argument.image

        binding.title.text = title
        binding.boardTitle.text = title2
        binding.sDay.text = startDate
        binding.eDay.text = endDate
        binding.content.text = content

        val contentImage: ImageView = binding.contentImage
        if (image.isNullOrEmpty()) {
            contentImage.isGone = true
        } else {
            Glide.with(requireContext()).load(image).into(contentImage)
        }


        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventDetailFragment_to_orderFragment)
        }

        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventDetailFragment_to_homeFragment)
        }
        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventDetailFragment_to_cardFragment)
        }
        binding.navAlarm.setOnClickListener {
            it.findNavController().navigate(R.id.action_eventDetailFragment_to_alarmFragment)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}