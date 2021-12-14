package com.mandeum.dessert39.Main.Alarm

import android.app.Dialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.ViewCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Intro.MainActivity
import com.mandeum.dessert39.Main.Order.sub.Cart.OrderCartFragmentDirections
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentAlarmBinding
import com.mandeum.dessert39.databinding.FragmentCardBinding


class AlarmFragment : Fragment() {

    private var _binding: FragmentAlarmBinding? = null
    private val binding get() = _binding!!
    lateinit var rvAdapter: AlarmAdapter

    private val alarmData = ArrayList<AlarmData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAlarmBinding.inflate(layoutInflater)

        val rvAdapter = AlarmAdapter(requireContext(), alarmData)
        val rv: RecyclerView = binding.alarmRecyclerView
        rv.adapter = rvAdapter

        val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.alarmRecyclerView.layoutManager = layoutManager



       rvAdapter.alarmData.add(AlarmData(R.drawable.sprout, "디저트39 가입을 환영합니다 :)", "", "2021.10.10 11:11:10", ""))

        rvAdapter.alarmData.add(AlarmData(R.drawable.sprout,
            "매장에 주문서가 전달되었습니다.",
            "[청라호수공원점] 픽업 가능 시간을 안내해드릴께요.\n잠시만 기다려 주세요 :)",
            "2021.10.10 11:11:10", ""))

        rvAdapter.alarmData.add(AlarmData(R.drawable.sprout,
            "주문이 접수 되었습니다.",
            "[ 청라호수공원점 | 픽업 가능 시간 30분 ]10분동안 결제를 하지 않으시면 주문이 자동으로 취소됩니다. 결제를 하여 주문을 완료해 주세요.",
            "2021.10.10 11:11:10", "결제하러 가기"))

        rvAdapter.alarmData.add(AlarmData(R.drawable.sprout,
            "결제 가능 시간이 10분 초과되어 주문이 취소 되었습니다. T^T ",
            "주문을 원하시면 ORDER 페이지에서 주문을 해주세요.",
            "2021.10.10 11:11:10", "ORDER 바로가기"))

        rvAdapter.alarmData.add(AlarmData(R.drawable.sprout,
            "결제가 완료되었습니다.",
            "메뉴 준비 시 알려드릴게요 :)",
            "2021.10.10 11:11:10", ""))

        rvAdapter.alarmData.add(AlarmData(R.drawable.sprout,
            "주문하신 메뉴가 준비중입니다.",
            "[ 청라호수공원점 | 픽업 가능 시간 30분 | 주문번호 373 ] 픽업 시간에 맞춰 매장에 방문해 주세요.",
            "2021.10.10 11:11:10", ""))

        rvAdapter.alarmData.add(AlarmData(R.drawable.sprout,
            "메뉴가 모두 준비되었습니다.",
            "매장 픽업대에서 주문하신 메뉴를 픽업해 주세요 :)",
            "2021.10.10 11:11:10", ""))

        rvAdapter.alarmData.add(AlarmData(R.drawable.sprout,
            "감사합니다. 길동님 안녕히가세요.",
            "다음에 또 방문해 주세요 :)",
            "2021.10.10 11:11:10", ""))

        rvAdapter.alarmData.add(AlarmData(R.drawable.sprout,
            "길동님 주문하신 메뉴들은 어떠셨나요?",
            "즐거운 디저트39 이용되셨다면 고객님의 소중한 리뷰를 남겨주세요.",
            "2021.10.10 11:11:10", "리뷰 남기기"))

        rvAdapter.alarmData.add(AlarmData(R.drawable.sprout,
            "축하드립니다! 이벤트 당첨 안내",
            "이벤트 당첨 내역을 확인해 보세요 :)",
            "2021.10.10 11:11:10", "바로가기"))


        binding.deleteBtn.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_all_delete)
            dialog.findViewById<Button>(R.id.no).setOnClickListener {
                dialog.dismiss()
            }
            dialog.findViewById<Button>(R.id.ok).setOnClickListener {
                dialog.dismiss()
            }
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCanceledOnTouchOutside(false)
            dialog.setCancelable(false)
            dialog.show()
        }

        binding.navHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_alarmFragment_to_homeFragment2)
        }
        binding.navCard.setOnClickListener {
            it.findNavController().navigate(R.id.action_alarmFragment_to_cardFragment3)
        }
        binding.navMy39.setOnClickListener {
            it.findNavController().navigate(R.id.action_alarmFragment_to_profileFragment3)
        }
        binding.orderIcon.setOnClickListener {
            it.findNavController().navigate(R.id.action_alarmFragment_to_orderFragment3)
        }
        binding.settingBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_alarmFragment_to_settingFragment)

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}