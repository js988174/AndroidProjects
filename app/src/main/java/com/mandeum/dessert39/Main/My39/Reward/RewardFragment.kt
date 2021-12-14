package com.mandeum.dessert39.Main.My39.Reward

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import androidx.navigation.findNavController
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentRewardBinding
import java.text.DecimalFormat
import java.util.*
import kotlin.concurrent.timer


class RewardFragment : Fragment() {

    private var _binding: FragmentRewardBinding? = null
    private val binding get() = _binding!!


    lateinit var rewardTimer : Timer
    lateinit var thread: HomeActivity

    var progressBar : Int = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRewardBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }


        thread = context as HomeActivity

        val progress : ProgressBar = binding.rewardProgressBar

        progress.progress = 0
        when (progressBar) {
            1 -> {
                progressBar()
            }
            2 -> {
                progressBar()
            }
            3 -> {
                progressBar()
            }
            4 -> {
                progressBar()
            }
            5 -> {
                progressBar()
            }
            6 -> {
                progressBar()
            }
            7 -> {
                progressBar()
            }
            8 -> {
                progressBar()
            }
            9 -> {
                progressBar()
            }
            10 -> {
                progressBar()
            }
            11 -> {
                progressBar()
            }
            12 -> {
                progressBar()
            }
        }


        return binding.root
    }
    override fun onPause() {
        stopLife()
        super.onPause()
    }

    override fun onDestroy() {
        stopLife()
        super.onDestroy()
    }

    override fun onDetach() {
        stopLife()
        super.onDetach()
    }

    private fun progressBar() {
        var count = 0

        val number1 = DecimalFormat("00")
        val a = number1.format(1)
        val b = number1.format(2)
        val c = number1.format(3)
        val d = number1.format(4)
        val e = number1.format(5)
        val f = number1.format(6)
        val g = number1.format(7)
        val h = number1.format(8)
        val i = number1.format(9)


        rewardTimer = timer(period = 15, initialDelay = 500) {

            binding.rewardProgressBar.progress = count

            thread.runOnUiThread {
                if (count == 8) {
                    binding.rewardNumber.text = a
                    if (progressBar == 1) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                } else if (count == 17) {
                    binding.rewardNumber.text = b
                    if (progressBar == 2) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }  else if (count == 25) {
                    binding.rewardNumber.text = c
                    if (progressBar == 3) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }   else if (count == 34) {
                    binding.rewardNumber.text = d
                    if (progressBar == 4) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 42) {
                    binding.rewardNumber.text = e
                    if (progressBar == 5) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 51) {
                    binding.rewardNumber.text = f
                    if (progressBar == 6) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 60) {
                    binding.rewardNumber.text = g
                    if (progressBar == 7) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 68) {
                    binding.rewardNumber.text = h
                    if (progressBar == 8) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 75) {
                    binding.rewardNumber.text = i
                    if (progressBar == 9) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 83) {
                    binding.rewardNumber.text = 10.toString()
                    if (progressBar == 10) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }    else if (count == 90) {
                    binding.rewardNumber.text = 11.toString()
                    if (progressBar == 11) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                }   else if (count == 97) {
                    binding.rewardNumber.text = 12.toString()
                    if (progressBar == 12) {
                        rewardTimer.cancel()
                    } else {
                        count++
                    }
                } else {
                    count++
                }
            }
        }
    }
    private fun stopLife () {
        rewardTimer.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}