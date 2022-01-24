package com.mandeum.dessert39.Main.My39.Info

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.mandeum.dessert39.Login.ServerApi.Model.Info.UserImageModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.databinding.FragmentMyInfoBinding
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.concurrent.thread
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isGone
import androidx.navigation.fragment.findNavController
import com.mandeum.dessert39.Find.Password.FindPw1Activity
import com.mandeum.dessert39.R
import android.R.attr.path
import android.database.Cursor
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import java.lang.Exception


class MyInfoFragment() : Fragment() {

    private var _binding : FragmentMyInfoBinding? = null
    private val binding get() = _binding!!

    private val cameraRequestCode: Int = 300
    private val galleryRequestCode: Int = 400
    private lateinit var photoPath: String
    private val permissionsRequestCode: Int = 40

    lateinit var thread : HomeActivity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMyInfoBinding.inflate(layoutInflater)
        thread = context as HomeActivity
        var photoFile = getTempFolder()


        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")
//        photoPath = saveImage()

        // 카테고리
        var coffee: Boolean = false
        var non_coffee: Boolean = false
        var milkTea: Boolean = false
        var blending: Boolean = false
        var aid: Boolean = false
        var smoothie:Boolean = false
        var fromage: Boolean = false
        var shavedIce:Boolean = false

        // 브랜드
        var starbuks:Boolean = false
        var twosomePlace: Boolean = false
        var pallbarSet: Boolean = false
        var ediya: Boolean = false
        var megaCoffee: Boolean = false
        var backCoffee:Boolean = false
        var gongCha: Boolean = false
        var coffeeBean: Boolean = false

        // 키워드
        var sweet:Boolean = false
        var fresh: Boolean = false
        var refreshing: Boolean = false
        var savory: Boolean = false
        var salty: Boolean = false
        var healthy:Boolean = false
        var sugar: Boolean = false
        var fruit: Boolean = false
        var large: Boolean = false
        var popular: Boolean = false

        // 스타벅스
        var mocha:Boolean = false
        var chocolate: Boolean = false
        var caramel: Boolean = false
        var coldBlue: Boolean = false
        var blackTea: Boolean = false
        var doubleVanilla:Boolean = false
        var toffeeNut: Boolean = false
        var Blonde: Boolean = false
        var coolLime: Boolean = false

        // 투썸플레이스
        var royalMilk :Boolean = false
        var strawberryFrappe : Boolean = false
        var honeyLemon : Boolean = false
        var spanishMilk : Boolean = false
        var sangriaAde: Boolean = false
        var mugwort :Boolean = false
        var grapefruit : Boolean = false
        var jejuFrappe: Boolean = false
        var signature : Boolean = false

        // 폴바
        var dolceLatte :Boolean = false
        var jejuAid : Boolean = false
        var vanillaLatte : Boolean = false
        var caramel2 : Boolean = false
        var sweetMilk: Boolean = false
        var strawberryYogurt :Boolean = false
        var jejuLatte : Boolean = false
        var mangoFrappe : Boolean = false
        var milkCholate : Boolean = false

        // 이디야
        var peachiceTea :Boolean = false
        var dalgonaLatte : Boolean = false
        var condensedLatte : Boolean = false
        var vanillaLatte2 : Boolean = false
        var mintFlatcino: Boolean = false
        var toffeeNut2 :Boolean = false
        var grapefruitAde : Boolean = false
        var honeyPeach : Boolean = false
        var einspanner : Boolean = false

        // 메가커피
        var icemilkTea :Boolean = false
        var strawberryLatte : Boolean = false
        var honeyGrapefruitTea : Boolean = false
        var appleCitronTea : Boolean = false
        var megaAid: Boolean = false
        var plainpongCrush :Boolean = false
        var coconutCoffee : Boolean = false
        var condensedLatte2 : Boolean = false
        var peachiceTea2 : Boolean = false

        // 빽다방
        var dolceLatte2 :Boolean = false
        var vanillaLatte3 : Boolean = false
        var caramel3 : Boolean = false
        var sweetMilk2 : Boolean = false
        var strawberryBacksuccino: Boolean = false
        var watermelon :Boolean = false
        var lemonAid : Boolean = false
        var peachOlongTea  : Boolean = false
        var fondantcino : Boolean = false

        // 공차
        var blackMilkTea :Boolean = false
        var brownSuga : Boolean = false
        var taroMilkTea : Boolean = false
        var mangoSmoothie : Boolean = false
        var mintChocoSmoothie: Boolean = false
        var strawberryCookie :Boolean = false
        var chocolateSmoothie : Boolean = false
        var realChocolateTea : Boolean = false
        var chocoCookieSmoothie : Boolean = false

        // 커피빈
        var englishLatte :Boolean = false
        var vinspener : Boolean = false
        var dalgonaCreamLatte : Boolean = false
        var cafeSua : Boolean = false
        var lemonCitronTea: Boolean = false
        var sparklingTea :Boolean = false
        var whiteForest : Boolean = false
        var veriveryIB : Boolean = false
        var mangobananaIB : Boolean = false

        var coffeeText : String = ""

        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val coffeeBtn = binding.coffeeBtn
        val nonCoffeeBtn = binding.teaBtn
        val milkTeaBtn = binding.dCafeBtn
        val blendingBtn = binding.smoodiBtn
        val aidBtn = binding.aidBtn
        val smoothieBtn = binding.shakeBtn
        val fromageBtn = binding.milkTeaBtn
        val iceBtn = binding.iceBtn

        val sweetBtn = binding.juice1
        val freshBtn = binding.juice2
        val refreshingBtn = binding.juice3
        val savoryBtn = binding.juice4
        val saltyBtn = binding.juice5
        val healthyBtn = binding.juice6
        val sugarBtn = binding.juice7
        val fruitBtn = binding.juice8
        val largeBtn = binding.juice9
        val popularBtn = binding.juice10

        val brand1 = binding.brand1
        val brand2 = binding.brand2
        val brand3 = binding.brand3
        val brand4 = binding.brand4
        val brand5 = binding.brand5
        val brand6 = binding.brand6
        val brand7 = binding.brand7
        val brand8 = binding.brand8

        val starbuksText1 = binding.likeBrand
        val starbuksText2 = binding.likeBar
        val starbuksText3 = binding.likeSelect
        val starbuks1 = binding.favorite1
        val starbuks2 = binding.favorite2
        val starbuks3 = binding.favorite3
        val starbuks4 = binding.favorite4
        val starbuks5 = binding.favorite5
        val starbuks6 = binding.favorite6
        val starbuks7 = binding.favorite7
        val starbuks8 = binding.favorite8
        val starbuks9 = binding.favorite9

        val twosomePlaceText1 = binding.likeBrand2
        val twosomePlaceText2 = binding.likeBar2
        val twosomePlaceText3 = binding.likeSelect2
        val twosomePlace1 = binding.favorite11
        val twosomePlace2 = binding.favorite12
        val twosomePlace3 = binding.favorite13
        val twosomePlace4 = binding.favorite14
        val twosomePlace5 = binding.favorite15
        val twosomePlace6 = binding.favorite16
        val twosomePlace7 = binding.favorite17
        val twosomePlace8 = binding.favorite18
        val twosomePlace9 = binding.favorite19

        val pallbarSetText1 = binding.likeBrand3
        val pallbarSetText2 = binding.likeBar3
        val pallbarSetText3 = binding.likeSelect3
        val pallbarSet1 = binding.favorite21
        val pallbarSet2 = binding.favorite22
        val pallbarSet3 = binding.favorite23
        val pallbarSet4 = binding.favorite24
        val pallbarSet5 = binding.favorite25
        val pallbarSet6 = binding.favorite26
        val pallbarSet7 = binding.favorite27
        val pallbarSet8 = binding.favorite28
        val pallbarSet9 = binding.favorite29

        val ediyaText1 = binding.likeBrand4
        val ediyaText2 = binding.likeBar4
        val ediyaText3 = binding.likeSelect4
        val ediya1 = binding.favorite31
        val ediya2 = binding.favorite32
        val ediya3 = binding.favorite33
        val ediya4 = binding.favorite34
        val ediya5 = binding.favorite35
        val ediya6 = binding.favorite36
        val ediya7 = binding.favorite37
        val ediya8 = binding.favorite38
        val ediya9 = binding.favorite39

        val megaCoffeeText1 = binding.likeBrand5
        val megaCoffeeText2 = binding.likeBar5
        val megaCoffeeText3 = binding.likeSelect5
        val megaCoffee1 = binding.favorite41
        val megaCoffee2 = binding.favorite42
        val megaCoffee3 = binding.favorite43
        val megaCoffee4 = binding.favorite44
        val megaCoffee5 = binding.favorite45
        val megaCoffee6 = binding.favorite46
        val megaCoffee7 = binding.favorite47
        val megaCoffee8 = binding.favorite48
        val megaCoffee9 = binding.favorite49

        val backCoffeeText1 = binding.likeBrand6
        val backCoffeeText2 = binding.likeBar6
        val backCoffeeText3 = binding.likeSelect6
        val backCoffee1 = binding.favorite51
        val backCoffee2 = binding.favorite52
        val backCoffee3 = binding.favorite53
        val backCoffee4 = binding.favorite54
        val backCoffee5 = binding.favorite55
        val backCoffee6 = binding.favorite56
        val backCoffee7 = binding.favorite57
        val backCoffee8 = binding.favorite58
        val backCoffee9 = binding.favorite59

        val gongChaText1 = binding.likeBrand7
        val gongChaText2 = binding.likeBar7
        val gongChaText3 = binding.likeSelect7
        val gongCha1 = binding.favorite61
        val gongCha2 = binding.favorite62
        val gongCha3 = binding.favorite63
        val gongCha4 = binding.favorite64
        val gongCha5 = binding.favorite65
        val gongCha6 = binding.favorite66
        val gongCha7 = binding.favorite67
        val gongCha8 = binding.favorite68
        val gongCha9 = binding.favorite69

        val coffeeBeanText1 = binding.likeBrand8
        val coffeeBeanText2 = binding.likeBar8
        val coffeeBeanText3 = binding.likeSelect8
        val coffeeBean1 = binding.favorite71
        val coffeeBean2 = binding.favorite72
        val coffeeBean3 = binding.favorite73
        val coffeeBean4 = binding.favorite74
        val coffeeBean5 = binding.favorite75
        val coffeeBean6 = binding.favorite76
        val coffeeBean7 = binding.favorite77
        val coffeeBean8 = binding.favorite78
        val coffeeBean9 = binding.favorite79

        var starbuksSum = 0
        var twosomePlaceSum = 0
        var pallbarSetSum = 0
        var ediyaSum = 0
        var megaCoffeeSum = 0
        var backCoffeeSum = 0
        var gongChaSum = 0
        var coffeeBeanSum = 0

        var starbuks1Text: String = ""
        var starbucks2Text : String = ""
        var starbuks3Text: String = ""
        var starbuks4Text : String = ""
        var starbuks5Text : String = ""
        var starbuks6Text : String = ""
        var starbuks7Text: String = ""
        var starbuks8Text : String = ""
        var starbuks9Text : String = ""

        var twosomePlace1Text : String = ""
        var twosomePlace2Text : String = ""
        var twosomePlace3Text : String = ""
        var twosomePlace4Text : String = ""
        var twosomePlace5Text : String = ""
        var twosomePlace6Text : String = ""
        var twosomePlace7Text : String = ""
        var twosomePlace8Text: String = ""
        var twosomePlace9Text : String = ""

        var pallbarSet1Text :String = ""
        var pallbarSet2Text :String = ""
        var pallbarSet3Text : String = ""
        var pallbarSet4Text : String = ""
        var pallbarSet5Text : String = ""
        var pallbarSet6Text : String = ""
        var pallbarSet7Text : String = ""
        var pallbarSet8Text : String = ""
        var pallbarSet9Text : String = ""

        var ediya9Text : String = ""
        var ediya8Text: String = ""
        var ediya7Text: String = ""
        var ediya6Text: String = ""
        var ediya5Text: String = ""
        var ediya4Text: String = ""
        var ediya3Text: String = ""
        var ediya2Text: String = ""
        var ediya1Text: String = ""

        var megaCoffee9Text : String = ""
        var megaCoffee8Text : String = ""
        var megaCoffee7Text : String = ""
        var megaCoffee6Text : String = ""
        var megaCoffee5Text : String = ""
        var megaCoffee4Text : String = ""
        var megaCoffee3Text : String = ""
        var megaCoffee2Text : String = ""
        var megaCoffee1Text : String = ""

        var backCoffee9Text : String = ""
        var backCoffee8Text : String = ""
        var backCoffee7Text : String = ""
        var backCoffee6Text : String = ""
        var backCoffee5Text : String = ""
        var backCoffee4Text : String = ""
        var backCoffee3Text : String = ""
        var backCoffee2Text : String = ""
        var backCoffee1Text : String = ""

        var coffeeBean9Text : String = ""
        var coffeeBean8Text : String = ""
        var coffeeBean7Text : String = ""
        var coffeeBean6Text : String = ""
        var coffeeBean5Text : String = ""
        var coffeeBean4Text : String = ""
        var coffeeBean3Text : String = ""
        var coffeeBean2Text : String = ""
        var coffeeBean1Text : String = ""

        var gongCha9Text : String = ""
        var gongCha8Text : String = ""
        var gongCha7Text : String = ""
        var gongCha6Text : String = ""
        var gongCha5Text : String = ""
        var gongCha4Text : String = ""
        var gongCha3Text : String = ""
        var gongCha2Text : String = ""
        var gongCha1Text : String = ""

        coffeeBtn.setOnClickListener {
            if (!coffee) {
                btnEvent1(coffeeBtn)
                coffeeText = "커피,"
                coffee = true
            } else if (coffee) {
                btnEvent2(coffeeBtn)
                coffee = false
                coffeeText = ""
            }

        }

        var nonCoffeeText : String = ""
        nonCoffeeBtn.setOnClickListener {
            if (!non_coffee) {
                btnEvent1(nonCoffeeBtn)
                non_coffee = true
                nonCoffeeText = "Non-커피,"
            } else if (non_coffee) {
                btnEvent2(nonCoffeeBtn)
                non_coffee = false
                nonCoffeeText = ""
            }
        }

        var milkTeaText : String = ""
        milkTeaBtn.setOnClickListener {
            if (!milkTea) {
                btnEvent1(milkTeaBtn)
                milkTea = true
                milkTeaText = "밀크티,"
            } else if (milkTea) {
                btnEvent2(milkTeaBtn)
                milkTea = false
                milkTeaText = ""
            }
        }

        var blendingText : String = ""
        blendingBtn.setOnClickListener {
            if (!blending) {
                btnEvent1(blendingBtn)
                blending = true
                blendingText = "티&티 블랜딩,"
            } else if (blending) {
                btnEvent2(blendingBtn)
                blending = false
                blendingText = ""
            }
        }

        var aidText : String = ""
        aidBtn.setOnClickListener {
            if (!aid) {
                btnEvent1(aidBtn)
                aid = true
                aidText = "에이드,"
            } else if (aid) {
                btnEvent2(aidBtn)
                aid = false
                aidText = ""
            }
        }

        var smoothieText : String = ""
        smoothieBtn.setOnClickListener {
            if (!smoothie) {
                btnEvent1(smoothieBtn)
                smoothie = true
                smoothieText = "스무디&프라페,"
            } else if (smoothie) {
                btnEvent2(smoothieBtn)
                smoothie = false
                smoothieText = ""
            }
        }

        var fromageText : String = ""
        fromageBtn.setOnClickListener {
            if (!fromage) {
                btnEvent1(fromageBtn)
                fromage = true
                fromageText = "프로마쥬,"
            } else if (fromage) {
                btnEvent2(fromageBtn)
                fromage = false
                fromageText = ""
            }
        }

        var iceText : String = ""
        iceBtn.setOnClickListener {
            if (!shavedIce) {
                btnEvent1(iceBtn)
                shavedIce = true
                iceText = "빙수&파르페,"
            } else if (shavedIce) {
                btnEvent2(iceBtn)
                shavedIce = false
                iceText = ""
            }
        }


        // 키워드
        var sum = 0
        var sweetText: String = ""
        sweetBtn.setOnClickListener {
            if (!sweet) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(sweetBtn)
                    sweet = true
                    sum++
                    sweetText = "달달한,"
                }
            } else if (sweet) {
                btnEvent2(sweetBtn)
                sweet = false
                sweetText = ""
                sum--
            }

        }

        var freshText : String = ""
        freshBtn.setOnClickListener {
            if (!fresh) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(freshBtn)
                    fresh = true
                    sum++
                    freshText = "상큼한,"
                }
            } else if (fresh) {
                btnEvent2(freshBtn)
                fresh = false
                freshText = ""
                sum--
            }
        }

        var refreshingText : String = ""
        refreshingBtn.setOnClickListener {
            if (!refreshing) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(refreshingBtn)
                    refreshing = true
                    sum++
                    refreshingText = "청량한,"
                }
            } else if (refreshing) {
                btnEvent2(refreshingBtn)
                refreshing = false
                refreshingText = ""
                sum--
            }
        }

        var savoryText : String = ""
        savoryBtn.setOnClickListener {
            if (!savory) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(savoryBtn)
                    savory = true
                    sum++
                    savoryText = "고소한,"
                }
            } else if (savory) {
                btnEvent2(savoryBtn)
                savory = false
                savoryText = ""
                sum--
            }
        }

        var saltyText : String = ""
        saltyBtn.setOnClickListener {
            if (!salty) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(saltyBtn)
                    salty = true
                    sum++
                    saltyText = "단짠,"
                }
            } else if (salty) {
                btnEvent2(saltyBtn)
                salty = false
                saltyText = ""
                sum--
            }
        }

        var healthyText: String = ""

        healthyBtn.setOnClickListener {
            if (!healthy) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(healthyBtn)
                    healthy = true
                    sum++
                    healthyText = "건강한,"
                    Log.d("sum", sum.toString())
                }
            } else if (healthy) {
                btnEvent2(healthyBtn)
                healthy = false
                healthyText = ""
                sum--
            }
        }

        var sugarText : String = ""

        sugarBtn.setOnClickListener {
            if (!sugar) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(sugarBtn)
                    sugar = true
                    sum++
                    sugarText = "당충전,"
                }
            } else if (sugar) {
                btnEvent2(sugarBtn)
                sugar = false
                sugarText = ""
                sum--
            }
        }

        var fruitText: String = ""

        fruitBtn.setOnClickListener {
            if (!fruit) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(fruitBtn)
                    fruit = true
                    sum++
                    fruitText = "과일류,"
                }
            } else if (fruit) {
                btnEvent2(fruitBtn)
                fruit = false
                sum--
                fruitText = ""
            }
        }

        var largeText : String = ""
        largeBtn.setOnClickListener {
            if (!large) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(largeBtn)
                    large = true
                    sum++
                    largeText = "대용량,"
                }
            } else if (large) {
                btnEvent2(largeBtn)
                large = false
                sum--
                largeText = ""
            }
        }

        var popularText : String = ""
        popularBtn.setOnClickListener {
            if (!popular) {
                if (sum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 음료 키워드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (sum < 3) {
                    btnEvent1(popularBtn)
                    popular = true
                    sum++
                    popularText = "인기메뉴,"
                }
            } else if (popular) {
                btnEvent2(popularBtn)
                popular = false
                popularText = ""
                sum--
            }
        }

        // 브랜드
        var brandsum = 0
        var brand1Text : String = ""
        brand1.setOnClickListener {
            if (!starbuks) {
                if (brandsum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 카페 브랜드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (brandsum < 3) {
                    btnEvent1(brand1)
                    starbuks = true
                    brandsum++
                    brand1Text = "스타벅스,"

                }
            } else if (starbuks) {
                btnEvent2(brand1)
                starbuks = false
                brandsum--
                brand1Text = ""

                if (mocha) {
                    btnEvent2(starbuks1)
                    mocha = false
                    starbuksSum--
                    starbuks1Text = ""
                }
                if (chocolate) {
                    btnEvent2(starbuks2)
                    chocolate = false
                    starbuksSum--
                    starbucks2Text = ""
                }
                if (caramel) {
                    btnEvent2(starbuks3)
                    caramel = false
                    starbuksSum--
                    starbuks3Text = ""
                }
                if (coldBlue) {
                    btnEvent2(starbuks4)
                    coldBlue = false
                    starbuksSum--
                    starbuks4Text = ""
                }
                if (blackTea) {
                    btnEvent2(starbuks5)
                    blackTea = false
                    starbuksSum--
                    starbuks5Text = ""
                }
                if (doubleVanilla) {
                    btnEvent2(starbuks6)
                    doubleVanilla = false
                    starbuksSum--
                    starbuks6Text = ""
                }
                if (toffeeNut) {
                    btnEvent2(starbuks7)
                    toffeeNut = false
                    starbuksSum--
                    starbuks7Text = ""
                }
                if (Blonde) {
                    btnEvent2(starbuks8)
                    Blonde = false
                    starbuksSum--
                    starbuks8Text = ""
                }
                if (coolLime) {
                    btnEvent2(starbuks9)
                    coolLime = false
                    starbuksSum--
                    starbuks9Text = ""
                }
            }

            brandSelect(
                starbuks, starbuksText1, starbuksText2, starbuksText3,
                starbuks1,starbuks2,starbuks3,starbuks4,starbuks5,starbuks6,starbuks7,starbuks8,
                starbuks9)
        }

        var brand2Text : String = ""
        brand2.setOnClickListener {
            if (!twosomePlace) {
                if (brandsum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 카페 브랜드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (brandsum < 3) {
                    btnEvent1(brand2)
                    twosomePlace = true
                    brandsum++
                    brand2Text = "투썸플레이스,"
                }
            } else if (twosomePlace) {
                btnEvent2(brand2)
                twosomePlace = false
                brandsum--
                brand2Text = ""

               if (royalMilk) {
                   btnEvent2(twosomePlace1)
                   royalMilk = false
                   twosomePlaceSum--
                   twosomePlace1Text = ""
               }
                if (strawberryFrappe) {
                    btnEvent2(twosomePlace2)
                    strawberryFrappe = false
                    twosomePlaceSum--
                    twosomePlace2Text = ""
                }
                if (honeyLemon) {
                    btnEvent2(twosomePlace3)
                    honeyLemon = false
                    twosomePlaceSum--
                    twosomePlace3Text = ""
                }
                if (spanishMilk) {
                    btnEvent2(twosomePlace4)
                    spanishMilk = false
                    twosomePlaceSum--
                    twosomePlace4Text = ""
                }
                if (sangriaAde) {
                    btnEvent2(twosomePlace5)
                    sangriaAde = false
                    twosomePlaceSum--
                    twosomePlace5Text = ""
                }
                if (mugwort) {
                    btnEvent2(twosomePlace6)
                    mugwort = false
                    twosomePlaceSum--
                    twosomePlace6Text = ""
                }
                if (grapefruit) {
                    btnEvent2(twosomePlace7)
                    grapefruit = false
                    twosomePlaceSum--
                    twosomePlace7Text = ""
                }
                if (jejuFrappe) {
                    btnEvent2(twosomePlace8)
                    jejuFrappe = false
                    twosomePlaceSum--
                    twosomePlace8Text = ""
                }
                if (signature) {
                    btnEvent2(twosomePlace9)
                    signature = false
                    twosomePlaceSum--
                    twosomePlace9Text = ""
                }

            }
            brandSelect(
                twosomePlace, twosomePlaceText1, twosomePlaceText2, twosomePlaceText3,
                twosomePlace1,twosomePlace2,twosomePlace3,
                twosomePlace4,twosomePlace5,twosomePlace6,
                twosomePlace7,twosomePlace8, twosomePlace9)
        }

        var brand3Text : String = ""
        brand3.setOnClickListener {
            if (!pallbarSet) {
                if (brandsum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 카페 브랜드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (brandsum < 3) {
                    btnEvent1(brand3)
                    pallbarSet = true
                    brandsum++
                    brand3Text = "폴바셋,"
                }
            } else if (pallbarSet) {
                btnEvent2(brand3)
                pallbarSet = false
                brandsum--
                brand3Text = ""

                if (dolceLatte) {
                    btnEvent2(pallbarSet1)
                    dolceLatte = false
                    pallbarSetSum--
                    pallbarSet1Text = ""
                }
                if (jejuAid) {
                    btnEvent2(pallbarSet2)
                    jejuAid = false
                    pallbarSetSum--
                    pallbarSet2Text = ""
                }
                if (vanillaLatte) {
                    btnEvent2(pallbarSet3)
                    vanillaLatte = false
                    pallbarSetSum--
                    pallbarSet3Text = ""
                }
                if (caramel2) {
                    btnEvent2(pallbarSet4)
                    caramel2 = false
                    pallbarSetSum--
                    pallbarSet4Text = ""
                }
                if (sweetMilk) {
                    btnEvent2(pallbarSet5)
                    sweetMilk = false
                    pallbarSetSum--
                    pallbarSet5Text = ""
                }
                if (strawberryYogurt) {
                    btnEvent2(pallbarSet6)
                    strawberryYogurt = false
                    pallbarSetSum--
                    pallbarSet6Text = ""
                }
                if (jejuLatte) {
                    btnEvent2(pallbarSet7)
                    jejuLatte = false
                    pallbarSetSum--
                    pallbarSet7Text = ""
                }
                if (mangoFrappe) {
                    btnEvent2(pallbarSet8)
                    mangoFrappe = false
                    pallbarSetSum--
                    pallbarSet8Text = ""
                }
                if (milkCholate) {
                    btnEvent2(pallbarSet9)
                    milkCholate = false
                    pallbarSetSum--
                    pallbarSet9Text = ""
                }

            }
            brandSelect(
                pallbarSet, pallbarSetText1, pallbarSetText2, pallbarSetText3,
                pallbarSet1,pallbarSet2,pallbarSet3,
                pallbarSet4,pallbarSet5,pallbarSet6,
                pallbarSet7,pallbarSet8,pallbarSet9)
        }

        var brand4Text: String = ""
        brand4.setOnClickListener {
            if (!ediya) {
                if (brandsum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 카페 브랜드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (brandsum < 3) {
                    btnEvent1(brand4)
                    ediya = true
                    brandsum++
                    brand4Text = "이디야,"
                }
            } else if (ediya) {
                btnEvent2(brand4)
                ediya = false
                brandsum--
                brand4Text = ""

                if (peachiceTea) {
                    btnEvent2(ediya1)
                    peachiceTea = false
                    ediyaSum--
                    ediya1Text = ""
                }
                       if (dalgonaLatte) {
                    btnEvent2(ediya2)
                           dalgonaLatte = false
                    ediyaSum--
                    ediya2Text = ""
                }
                       if (condensedLatte) {
                    btnEvent2(ediya3)
                           condensedLatte = false
                    ediyaSum--
                    ediya3Text = ""
                }
                       if (vanillaLatte2) {
                    btnEvent2(ediya4)
                    vanillaLatte2 = false
                    ediyaSum--
                    ediya4Text = ""
                }
                       if (mintFlatcino) {
                    btnEvent2(ediya5)
                           mintFlatcino = false
                    ediyaSum--
                    ediya5Text = ""
                }
                       if (toffeeNut2) {
                    btnEvent2(ediya6)
                           toffeeNut2 = false
                    ediyaSum--
                    ediya6Text = ""
                }
                       if (grapefruitAde) {
                    btnEvent2(ediya7)
                           grapefruitAde = false
                    ediyaSum--
                    ediya7Text = ""
                }
                       if (honeyPeach) {
                    btnEvent2(ediya8)
                           honeyPeach = false
                    ediyaSum--
                    ediya8Text = ""
                }
                       if (einspanner) {
                    btnEvent2(ediya9)
                           einspanner = false
                    ediyaSum--
                    ediya9Text = ""
                }

            }

            brandSelect(
                ediya, ediyaText1, ediyaText2, ediyaText3,
                ediya1,ediya2,ediya3,
                ediya4,ediya5,ediya6,
                ediya7,ediya8,ediya9)
        }

        var brand5Text : String = ""
        brand5.setOnClickListener {
            if (!megaCoffee) {
                if (brandsum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 카페 브랜드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (brandsum < 3) {
                    btnEvent1(brand5)
                    megaCoffee = true
                    brandsum++
                    brand5Text = "메가커피,"
                }
            } else if (megaCoffee) {
                btnEvent2(brand5)
                megaCoffee = false
                brandsum--
                brand5Text = ""

                if (icemilkTea) {
                    btnEvent2(megaCoffee1)
                    icemilkTea = false
                    megaCoffeeSum--
                    megaCoffee1Text = ""
                }
                if (strawberryLatte) {
                    btnEvent2(megaCoffee2)
                    strawberryLatte = false
                    megaCoffeeSum--
                    megaCoffee2Text = ""
                }
                if (honeyGrapefruitTea) {
                    btnEvent2(megaCoffee3)
                    honeyGrapefruitTea = false
                    megaCoffeeSum--
                    megaCoffee3Text = ""
                }
                if (appleCitronTea) {
                    btnEvent2(megaCoffee4)
                    appleCitronTea = false
                    megaCoffeeSum--
                    megaCoffee4Text = ""
                }
                if (megaAid) {
                    btnEvent2(megaCoffee5)
                    megaAid = false
                    megaCoffeeSum--
                    megaCoffee5Text = ""
                }
                if (plainpongCrush) {
                    btnEvent2(megaCoffee6)
                    plainpongCrush = false
                    megaCoffeeSum--
                    megaCoffee6Text = ""
                }
                if (coconutCoffee) {
                    btnEvent2(megaCoffee7)
                    coconutCoffee = false
                    megaCoffeeSum--
                    megaCoffee7Text = ""
                }
                if (condensedLatte2) {
                    btnEvent2(megaCoffee8)
                    condensedLatte2 = false
                    megaCoffeeSum--
                    megaCoffee8Text = ""
                }
                if (peachiceTea2) {
                    btnEvent2(megaCoffee9)
                    peachiceTea2 = false
                    megaCoffeeSum--
                    megaCoffee9Text = ""
                }

            }
            brandSelect(
                megaCoffee, megaCoffeeText1, megaCoffeeText2, megaCoffeeText3,
                megaCoffee1,megaCoffee2,megaCoffee3,
                megaCoffee4,megaCoffee5,megaCoffee6,
                megaCoffee7,megaCoffee8,megaCoffee9)
        }

        var brand6Text: String = ""

        brand6.setOnClickListener {
            if (!backCoffee) {
                if (brandsum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 카페 브랜드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (brandsum < 3) {
                    btnEvent1(brand6)
                    backCoffee = true
                    brandsum++
                    brand6Text = "빽다방,"
                }
            } else if (backCoffee) {
                btnEvent2(brand6)
                backCoffee = false
                brandsum--
                brand6Text = ""

                if (dolceLatte2) {
                    btnEvent2(backCoffee1)
                    dolceLatte2 = false
                    backCoffeeSum--
                    backCoffee1Text = ""
                }
                if (vanillaLatte3) {
                    btnEvent2(backCoffee2)
                    vanillaLatte3 = false
                    backCoffeeSum--
                    backCoffee2Text = ""
                }
                if (caramel3) {
                    btnEvent2(backCoffee3)
                    caramel3 = false
                    backCoffeeSum--
                    backCoffee3Text = ""
                }
                if (sweetMilk2) {
                    btnEvent2(backCoffee4)
                    sweetMilk2 = false
                    backCoffeeSum--
                    backCoffee4Text = ""
                }
                if (strawberryBacksuccino) {
                    btnEvent2(backCoffee5)
                    strawberryBacksuccino = false
                    backCoffeeSum--
                    backCoffee5Text = ""
                }
                if (watermelon) {
                    btnEvent2(backCoffee6)
                    watermelon = false
                    backCoffeeSum--
                    backCoffee6Text = ""
                }
                if (lemonAid) {
                    btnEvent2(backCoffee7)
                    lemonAid = false
                    backCoffeeSum--
                    backCoffee7Text = ""
                }
                if (peachOlongTea) {
                    btnEvent2(backCoffee8)
                    peachOlongTea = false
                    backCoffeeSum--
                    backCoffee8Text = ""
                }
                if (fondantcino) {
                    btnEvent2(backCoffee9)
                    fondantcino = false
                    backCoffeeSum--
                    backCoffee9Text = ""
                }
            }
            brandSelect(
                backCoffee, backCoffeeText1, backCoffeeText2, backCoffeeText3,
                backCoffee1,backCoffee2,backCoffee3,
                backCoffee4,backCoffee5,backCoffee6,
                backCoffee7,backCoffee8,backCoffee9)
        }

        var brand7Text : String = ""
        brand7.setOnClickListener {
            if (!gongCha) {
                if (brandsum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 카페 브랜드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (brandsum < 3) {
                    btnEvent1(brand7)
                    gongCha = true
                    brandsum++
                    brand7Text = "공차,"

                }
            } else if (gongCha) {
                btnEvent2(brand7)
                gongCha = false
                brandsum--
                brand7Text = ""

                if(blackMilkTea) {
                    btnEvent2(gongCha1)
                    blackMilkTea = false
                    gongCha1Text = ""
                    gongChaSum--
                }
                if (brownSuga) {
                    btnEvent2(gongCha2)
                    brownSuga = false
                    gongCha2Text = ""
                    gongChaSum--
                }
                if (taroMilkTea) {
                    btnEvent2(gongCha3)
                    taroMilkTea = false
                    gongCha3Text = ""
                    gongChaSum--
                }
                if (mangoSmoothie) {
                    btnEvent2(gongCha4)
                    mangoSmoothie = false
                    gongCha4Text = ""
                    gongChaSum--
                }
                if (mintChocoSmoothie) {
                    btnEvent2(gongCha5)
                    mintChocoSmoothie = false
                    gongCha5Text = ""
                    gongChaSum--
                }
                if (strawberryCookie) {
                    btnEvent2(gongCha6)
                    strawberryCookie = false
                    gongCha6Text = ""
                    gongChaSum--
                }
                if (chocolateSmoothie) {
                    btnEvent2(gongCha7)
                    chocolateSmoothie = false
                    gongCha7Text = ""
                    gongChaSum--
                }
                if (realChocolateTea) {
                    btnEvent2(gongCha8)
                    realChocolateTea = false
                    gongCha8Text = ""
                    gongChaSum--
                }
                if (chocoCookieSmoothie) {
                    btnEvent2(gongCha9)
                    chocoCookieSmoothie = false
                    gongCha9Text = ""
                    gongChaSum--
                }


            }
            brandSelect(
                gongCha, gongChaText1, gongChaText2, gongChaText3,
                gongCha1,gongCha2,gongCha3,
                gongCha4,gongCha5,gongCha6,
                gongCha7,gongCha8,gongCha9)
            Log.d("sum", gongChaSum.toString())
            Log.d("sum", blackMilkTea.toString())
        }

        var brand8Text: String = ""
        brand8.setOnClickListener {
            if (!coffeeBean) {
                if (brandsum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "선호하는 카페 브랜드는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (brandsum < 3) {
                    btnEvent1(brand8)
                    coffeeBean = true
                    brandsum++
                    brand8Text = "커피빈,"
                }
            } else if (coffeeBean) {
                btnEvent2(brand8)
                coffeeBean = false
                brandsum--
                brand8Text = ""

                if (englishLatte) {
                    btnEvent2(coffeeBean1)
                    englishLatte = false
                    coffeeBean1Text = ""
                    coffeeBeanSum--
                }
                if (vinspener) {
                    btnEvent2(coffeeBean2)
                    vinspener = false
                    coffeeBean2Text = ""
                    coffeeBeanSum--
                }
                if (dalgonaCreamLatte) {
                    btnEvent2(coffeeBean3)
                    dalgonaCreamLatte = false
                    coffeeBean3Text = ""
                    coffeeBeanSum--
                }
                if (cafeSua) {
                    btnEvent2(coffeeBean4)
                    cafeSua = false
                    coffeeBean4Text = ""
                    coffeeBeanSum--
                }
                if (lemonCitronTea) {
                    btnEvent2(coffeeBean5)
                    lemonCitronTea = false
                    coffeeBean5Text = ""
                    coffeeBeanSum--
                }
                if (sparklingTea) {
                    btnEvent2(coffeeBean6)
                    sparklingTea = false
                    coffeeBean6Text = ""
                    coffeeBeanSum--
                }
                if (whiteForest) {
                    btnEvent2(coffeeBean7)
                    whiteForest = false
                    coffeeBean7Text = ""
                    coffeeBeanSum--
                }
                if (veriveryIB) {
                    btnEvent2(coffeeBean8)
                    veriveryIB = false
                    coffeeBean8Text = ""
                    coffeeBeanSum--
                }
                if (mangobananaIB) {
                    btnEvent2(coffeeBean9)
                    mangobananaIB = false
                    coffeeBean9Text = ""
                    coffeeBeanSum--
                }

            }
            brandSelect(
                coffeeBean, coffeeBeanText1, coffeeBeanText2, coffeeBeanText3,
                coffeeBean1,coffeeBean2,coffeeBean3,
                coffeeBean4,coffeeBean5,coffeeBean6,
                coffeeBean7,coffeeBean8,coffeeBean9)
        }


        val tab = "|"
        starbuks1.setOnClickListener {
            if (!mocha) {
                if (starbuksSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (starbuksSum < 3) {
                    btnEvent1(starbuks1)
                    mocha = true
                    starbuksSum++
                    starbuks1Text = "카페모카,"
                }
            } else if (mocha) {
                btnEvent2(starbuks1)
                mocha = false
                starbuksSum--
                starbuks1Text = ""
            }
        }

        starbuks2.setOnClickListener {
            if (!chocolate) {
                if (starbuksSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (starbuksSum < 3) {
                    btnEvent1(starbuks2)
                    chocolate = true
                    starbuksSum++
                    starbucks2Text = "초콜릿크림칩프라푸치노,"

                }
            } else if (chocolate) {
                btnEvent2(starbuks2)
                chocolate = false
                starbuksSum--
                starbucks2Text = ""
            }

        }

        starbuks3.setOnClickListener {
            if (!caramel) {
                if (starbuksSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (starbuksSum < 3) {
                    btnEvent1(starbuks3)
                    caramel = true
                    starbuksSum++
                    starbuks3Text = "카라멜 마끼아또,"

                }
            } else if (caramel) {
                btnEvent2(starbuks3)
                caramel = false
                starbuksSum--
                starbuks3Text = ""
            }
        }

        starbuks4.setOnClickListener {
            if (!coldBlue) {
                if (starbuksSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (starbuksSum < 3) {
                    btnEvent1(starbuks4)
                    coldBlue = true
                    starbuksSum++
                    starbuks4Text = "돌체 콜드 브루,"

                }
            } else if (coldBlue) {
                btnEvent2(starbuks4)
                coldBlue = false
                starbuksSum--
                starbuks4Text = ""
            }
        }

        starbuks5.setOnClickListener {
            if (!blackTea) {
                if (starbuksSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (starbuksSum < 3) {
                    btnEvent1(starbuks5)
                    blackTea = true
                    starbuksSum++
                    starbuks5Text = "허니자몽블랙티,"
                }
            } else if (blackTea) {
                btnEvent2(starbuks5)
                blackTea = false
                starbuksSum--
                starbuks5Text = ""
            }
        }

        starbuks6.setOnClickListener {
            if (!doubleVanilla) {
                if (starbuksSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (starbuksSum < 3) {
                    btnEvent1(starbuks6)
                    doubleVanilla = true
                    starbuksSum++
                    starbuks6Text = "더블샷바닐라,"

                }
            } else if (doubleVanilla) {
                btnEvent2(starbuks6)
                doubleVanilla = false
                starbuksSum--
                starbuks6Text = ""
            }
        }

        starbuks7.setOnClickListener {
            if (!toffeeNut) {
                if (starbuksSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (starbuksSum < 3) {
                    btnEvent1(starbuks7)
                    toffeeNut = true
                    starbuksSum++
                    starbuks7Text = "토피넛라떼,"

                }
            } else if (toffeeNut) {
                btnEvent2(starbuks7)
                toffeeNut = false
                starbuksSum--
                starbuks7Text = ""
            }
        }

        starbuks8.setOnClickListener {
            if (!Blonde) {
                if (starbuksSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (starbuksSum < 3) {
                    btnEvent1(starbuks8)
                    Blonde = true
                    starbuksSum++
                    starbuks8Text = "블론드바닐라더블샷마키아또,"

                }
            } else if (Blonde) {
                btnEvent2(starbuks8)
                Blonde = false
                starbuksSum--
                starbuks8Text = ""
            }
        }

        starbuks9.setOnClickListener {
            if (!coolLime) {
                if (starbuksSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (starbuksSum < 3) {
                    btnEvent1(starbuks9)
                    coolLime = true
                    starbuksSum++
                    starbuks9Text = "쿨라임피지오,"

                }
            } else if (coolLime) {
                btnEvent2(starbuks9)
                coolLime = false
                starbuksSum--
                starbuks9Text = ""
            }
        }



        twosomePlace1.setOnClickListener {
            if (!royalMilk) {
                if (twosomePlaceSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (twosomePlaceSum < 3) {
                    btnEvent1(twosomePlace1)
                    royalMilk = true
                    twosomePlaceSum++
                    twosomePlace1Text = "로얄밀크티,"

                }
            } else if (royalMilk) {
                btnEvent2(twosomePlace1)
                royalMilk = false
                twosomePlaceSum--
                twosomePlace1Text = ""

            }
        }

        twosomePlace2.setOnClickListener {
            if (!strawberryFrappe) {
                if (twosomePlaceSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (twosomePlaceSum < 3) {
                    btnEvent1(twosomePlace2)
                    strawberryFrappe = true
                    twosomePlaceSum++
                    twosomePlace2Text = "스트로베리피치프라페,"

                }
            } else if (strawberryFrappe) {
                btnEvent2(twosomePlace2)
                strawberryFrappe = false
                twosomePlaceSum--
                twosomePlace2Text = ""
            }
        }


        twosomePlace3.setOnClickListener {
            if (!honeyLemon) {
                if (twosomePlaceSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (twosomePlaceSum < 3) {
                    btnEvent1(twosomePlace3)
                    honeyLemon = true
                    twosomePlaceSum++
                    twosomePlace3Text = "허니레몬티,"

                }
            } else if (honeyLemon) {
                btnEvent2(twosomePlace3)
                honeyLemon = false
                twosomePlaceSum--
                twosomePlace3Text = ""
            }
        }


        twosomePlace4.setOnClickListener {
            if (!spanishMilk) {
                if (twosomePlaceSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (twosomePlaceSum < 3) {
                    btnEvent1(twosomePlace4)
                    spanishMilk = true
                    twosomePlaceSum++
                    twosomePlace4Text = "스페니쉬연유라떼,"

                }
            } else if (spanishMilk) {
                btnEvent2(twosomePlace4)
                spanishMilk = false
                twosomePlaceSum--
                twosomePlace4Text = ""
            }
        }

        twosomePlace5.setOnClickListener {
            if (!sangriaAde) {
                if (twosomePlaceSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (twosomePlaceSum < 3) {
                    btnEvent1(twosomePlace5)
                    sangriaAde = true
                    twosomePlaceSum++
                    twosomePlace5Text = "상그리아에이드,"

                }
            } else if (sangriaAde) {
                btnEvent2(twosomePlace5)
                sangriaAde = false
                twosomePlaceSum--
                twosomePlace5Text = ""
            }
        }

        twosomePlace6.setOnClickListener {
            if (!mugwort) {
                if (twosomePlaceSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (twosomePlaceSum < 3) {
                    btnEvent1(twosomePlace6)
                    mugwort = true
                    twosomePlaceSum++
                    twosomePlace6Text = "쑥라떼,"

                }
            } else if (mugwort) {
                btnEvent2(twosomePlace6)
                mugwort = false
                twosomePlaceSum--
                twosomePlace6Text = ""
            }
        }

        twosomePlace7.setOnClickListener {
            if (!grapefruit) {
                if (twosomePlaceSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (twosomePlaceSum < 3) {
                    btnEvent1(twosomePlace7)
                    grapefruit = true
                    twosomePlaceSum++
                    twosomePlace7Text = "자몽시트러스프라페,"

                }
            } else if (grapefruit) {
                btnEvent2(twosomePlace7)
                grapefruit = false
                twosomePlaceSum--
                twosomePlace7Text = ""
            }
        }


        twosomePlace8.setOnClickListener {
            if (!jejuFrappe) {
                if (twosomePlaceSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (twosomePlaceSum < 3) {
                    btnEvent1(twosomePlace8)
                    jejuFrappe = true
                    twosomePlaceSum++
                    twosomePlace8Text = "제주말차프라페,"

                }
            } else if (jejuFrappe) {
                btnEvent2(twosomePlace8)
                jejuFrappe = false
                twosomePlaceSum--
                twosomePlace8Text = ""
            }
        }

        twosomePlace9.setOnClickListener {
            if (!signature) {
                if (twosomePlaceSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (twosomePlaceSum < 3) {
                    btnEvent1(twosomePlace9)
                    signature = true
                    twosomePlaceSum++
                    twosomePlace9Text = "시그니처뱅쇼,"

                }
            } else if (signature) {
                btnEvent2(twosomePlace9)
                signature = false
                twosomePlaceSum--
                twosomePlace9Text = ""

            }
        }




        pallbarSet1.setOnClickListener {
            if (!dolceLatte) {
                if (pallbarSetSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (pallbarSetSum < 3) {
                    btnEvent1(pallbarSet1)
                    dolceLatte = true
                    pallbarSetSum++
                    pallbarSet1Text = "돌체라떼,"

                }
            } else if (dolceLatte) {
                btnEvent2(pallbarSet1)
                dolceLatte = false
                pallbarSetSum--
                pallbarSet1Text = ""
            }
        }

        pallbarSet2.setOnClickListener {
            if (!jejuAid) {
                if (pallbarSetSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (pallbarSetSum < 3) {
                    btnEvent1(pallbarSet2)
                    jejuAid = true
                    pallbarSetSum++
                    pallbarSet2Text = "제주 한라봉에이드,"
                }
            } else if (jejuAid) {
                btnEvent2(pallbarSet2)
                jejuAid = false
                pallbarSetSum--
                pallbarSet2Text = ""
            }
        }

        pallbarSet3.setOnClickListener {
            if (!vanillaLatte) {
                if (pallbarSetSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (pallbarSetSum < 3) {
                    btnEvent1(pallbarSet3)
                    vanillaLatte = true
                    pallbarSetSum++
                    pallbarSet3Text = "바닐라라떼,"

                }
            } else if (vanillaLatte) {
                btnEvent2(pallbarSet3)
                vanillaLatte = false
                pallbarSetSum--
                pallbarSet3Text = ""
            }
        }

        pallbarSet4.setOnClickListener {
            if (!caramel2) {
                if (pallbarSetSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (pallbarSetSum < 3) {
                    btnEvent1(pallbarSet4)
                    caramel2 = true
                    pallbarSetSum++
                    pallbarSet4Text = "카라멜마끼아토,"

                }
            } else if (caramel2) {
                btnEvent2(pallbarSet4)
                caramel2 = false
                pallbarSetSum--
                pallbarSet4Text = ""
            }
        }

        pallbarSet5.setOnClickListener {
            if (!sweetMilk) {
                if (pallbarSetSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (pallbarSetSum < 3) {
                    btnEvent1(pallbarSet5)
                    sweetMilk = true
                    pallbarSetSum++
                    pallbarSet5Text = "스윗밀크티,"

                }
            } else if (sweetMilk) {
                btnEvent2(pallbarSet5)
                sweetMilk = false
                pallbarSetSum--
                pallbarSet5Text = ""
            }
        }

        pallbarSet6.setOnClickListener {
            if (!strawberryYogurt) {
                if (pallbarSetSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (pallbarSetSum < 3) {
                    btnEvent1(pallbarSet6)
                    strawberryYogurt = true
                    pallbarSetSum++
                    pallbarSet6Text = "스트로베리요거트,"

                }
            } else if (strawberryYogurt) {
                btnEvent2(pallbarSet6)
                strawberryYogurt = false
                pallbarSetSum--
                pallbarSet6Text = ""
            }
        }

        pallbarSet7.setOnClickListener {
            if (!jejuLatte) {
                if (pallbarSetSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (pallbarSetSum < 3) {
                    btnEvent1(pallbarSet7)
                    jejuLatte = true
                    pallbarSetSum++
                    pallbarSet7Text = "제주말차라떼,"

                }
            } else if (jejuLatte) {
                btnEvent2(pallbarSet7)
                jejuLatte = false
                pallbarSetSum--
                pallbarSet7Text = ""

            }
        }
        pallbarSet8.setOnClickListener {
            if (!mangoFrappe) {
                if (pallbarSetSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (pallbarSetSum < 3) {
                    btnEvent1(pallbarSet8)
                    mangoFrappe = true
                    pallbarSetSum++
                    pallbarSet8Text = "망고바나나프라페,"

                }
            } else if (mangoFrappe) {
                btnEvent2(pallbarSet8)
                mangoFrappe = false
                pallbarSetSum--
                pallbarSet8Text = ""
            }
        }
        pallbarSet9.setOnClickListener {
            if (!milkCholate) {
                if (pallbarSetSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (pallbarSetSum < 3) {
                    btnEvent1(pallbarSet9)
                    milkCholate = true
                    pallbarSetSum++
                    pallbarSet9Text = "밀크초콜릿,"

                }
            } else if (milkCholate) {
                btnEvent2(pallbarSet9)
                milkCholate = false
                pallbarSetSum--
                pallbarSet9Text = ""
            }
        }



        ediya1.setOnClickListener {
            if (!peachiceTea) {
                if (ediyaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ediyaSum < 3) {
                    btnEvent1(ediya1)
                    peachiceTea = true
                    ediyaSum++
                    ediya1Text = "복숭아아이스티,"
                }
            } else if (peachiceTea) {
                btnEvent2(ediya1)
                peachiceTea = false
                ediyaSum--
                ediya1Text = ""
            }
        }

        ediya2.setOnClickListener {
            if (!dalgonaLatte) {
                if (ediyaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ediyaSum < 3) {
                    btnEvent1(ediya2)
                    dalgonaLatte = true
                    ediyaSum++
                    ediya2Text = "달고나라떼,"

                }
            } else if (dalgonaLatte) {
                btnEvent2(ediya2)
                dalgonaLatte = false
                ediyaSum--
                ediya2Text = ""
            }

        }

        ediya3.setOnClickListener {
            if (!condensedLatte) {
                if (ediyaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ediyaSum < 3) {
                    btnEvent1(ediya3)
                    condensedLatte = true
                    ediyaSum++
                    ediya3Text = "연유카페라떼,"

                }
            } else if (condensedLatte) {
                btnEvent2(ediya3)
                condensedLatte = false
                ediyaSum--
                ediya3Text = ""
            }
        }

        ediya4.setOnClickListener {
            if (!vanillaLatte2) {
                if (ediyaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ediyaSum < 3) {
                    btnEvent1(ediya4)
                    vanillaLatte2 = true
                    ediyaSum++
                    ediya4Text = "바닐라라떼,"

                }
            } else if (vanillaLatte2) {
                btnEvent2(ediya4)
                vanillaLatte2 = false
                ediyaSum--
                ediya4Text = ""
            }
        }

        ediya5.setOnClickListener {
            if (!mintFlatcino) {
                if (ediyaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ediyaSum < 3) {
                    btnEvent1(ediya5)
                    mintFlatcino = true
                    ediyaSum++
                    ediya5Text = "민트초콜릿칩 플랫치노,"

                }
            } else if (mintFlatcino) {
                btnEvent2(ediya5)
                mintFlatcino = false
                ediyaSum--
                ediya5Text = ""
            }
        }

        ediya6.setOnClickListener {
            if (!toffeeNut2) {
                if (ediyaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ediyaSum < 3) {
                    btnEvent1(ediya6)
                    toffeeNut2 = true
                    ediyaSum++
                    ediya6Text = "토피넛라떼,"

                }
            } else if (toffeeNut2) {
                btnEvent2(ediya6)
                toffeeNut2 = false
                ediyaSum--
                ediya6Text = ""
            }
        }

        ediya7.setOnClickListener {
            if (!grapefruitAde) {
                if (ediyaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ediyaSum < 3) {
                    btnEvent1(ediya7)
                    grapefruitAde = true
                    ediyaSum++
                    ediya7Text = "자몽에이드,"

                }
            } else if (grapefruitAde) {
                btnEvent2(ediya7)
                grapefruitAde = false
                ediyaSum--
                ediya7Text = ""
            }
        }

        ediya8.setOnClickListener {
            if (!honeyPeach) {
                if (ediyaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ediyaSum < 3) {
                    btnEvent1(ediya8)
                    honeyPeach = true
                    ediyaSum++
                    ediya8Text = "꿀복숭아플랫치노,"

                }
            } else if (honeyPeach) {
                btnEvent2(ediya8)
                honeyPeach = false
                ediyaSum--
                ediya8Text = ""
            }
        }

        ediya9.setOnClickListener {
            if (!einspanner) {
                if (ediyaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (ediyaSum < 3) {
                    btnEvent1(ediya9)
                    einspanner = true
                    ediyaSum++
                    ediya9Text = "아인슈패너,"

                }
            } else if (einspanner) {
                btnEvent2(ediya9)
                einspanner = false
                ediyaSum--
                ediya9Text = ""
            }
        }



        megaCoffee1.setOnClickListener {
            if (!icemilkTea) {
                if (megaCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (megaCoffeeSum < 3) {
                    btnEvent1(megaCoffee1)
                    icemilkTea = true
                    megaCoffeeSum++
                    megaCoffee1Text = "아이스밀크티,"

                }
            } else if (icemilkTea) {
                btnEvent2(megaCoffee1)
                icemilkTea = false
                megaCoffeeSum--
                megaCoffee1Text = ""
            }
        }

        megaCoffee2.setOnClickListener {
            if (!strawberryLatte) {
                if (megaCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (megaCoffeeSum < 3) {
                    btnEvent1(megaCoffee2)
                    strawberryLatte = true
                    megaCoffeeSum++
                    megaCoffee2Text = "딸기라떼,"

                }
            } else if (strawberryLatte) {
                btnEvent2(megaCoffee2)
                strawberryLatte = false
                megaCoffeeSum--
                megaCoffee2Text = ""
            }
        }

        megaCoffee3.setOnClickListener {
            if (!honeyGrapefruitTea) {
                if (megaCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (megaCoffeeSum < 3) {
                    btnEvent1(megaCoffee3)
                    honeyGrapefruitTea = true
                    megaCoffeeSum++
                    megaCoffee3Text = "허니자몽블랙티,"

                }
            } else if (honeyGrapefruitTea) {
                btnEvent2(megaCoffee3)
                honeyGrapefruitTea = false
                megaCoffeeSum--
                megaCoffee3Text = ""
            }
        }

        megaCoffee4.setOnClickListener {
            if (!appleCitronTea) {
                if (megaCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (megaCoffeeSum < 3) {
                    btnEvent1(megaCoffee4)
                    appleCitronTea = true
                    megaCoffeeSum++
                    megaCoffee4Text = "사과유자티,"

                }
            } else if (appleCitronTea) {
                btnEvent2(megaCoffee4)
                appleCitronTea = false
                megaCoffeeSum--
                megaCoffee4Text = ""
            }
        }

        megaCoffee5.setOnClickListener {
            if (!megaAid) {
                if (megaCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (megaCoffeeSum < 3) {
                    btnEvent1(megaCoffee5)
                    megaAid = true
                    megaCoffeeSum++
                    megaCoffee5Text = "메가에이드(레몬/자몽/라임),"

                }
            } else if (megaAid) {
                btnEvent2(megaCoffee5)
                megaAid = false
                megaCoffeeSum--
                megaCoffee5Text = ""
            }


        }

        megaCoffee6.setOnClickListener {
            if (!plainpongCrush) {
                if (megaCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (megaCoffeeSum < 3) {
                    btnEvent1(megaCoffee6)
                    plainpongCrush = true
                    megaCoffeeSum++
                    megaCoffee6Text = "플레인 퐁크러쉬,"

                }
            } else if (plainpongCrush) {
                btnEvent2(megaCoffee6)
                plainpongCrush = false
                megaCoffeeSum--
                megaCoffee6Text = ""
            }

        }

        megaCoffee7.setOnClickListener {
            if (!coconutCoffee) {
                if (megaCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (megaCoffeeSum < 3) {
                    btnEvent1(megaCoffee7)
                    coconutCoffee = true
                    megaCoffeeSum++
                    megaCoffee7Text = "코코넛커피스무디,"

                }
            } else if (coconutCoffee) {
                btnEvent2(megaCoffee7)
                coconutCoffee = false
                megaCoffeeSum--
                megaCoffee7Text = ""
            }


        }

        megaCoffee8.setOnClickListener {
            if (!condensedLatte2) {
                if (megaCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (megaCoffeeSum < 3) {
                    btnEvent1(megaCoffee8)
                    condensedLatte2 = true
                    megaCoffeeSum++
                    megaCoffee8Text = "연유라떼,"

                }
            } else if (condensedLatte2) {
                btnEvent2(megaCoffee8)
                condensedLatte2 = false
                megaCoffeeSum--
                megaCoffee8Text = ""
            }


        }

        megaCoffee9.setOnClickListener {
            if (!peachiceTea2) {
                if (megaCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (megaCoffeeSum < 3) {
                    btnEvent1(megaCoffee9)
                    peachiceTea2 = true
                    megaCoffeeSum++
                    megaCoffee9Text = "복숭아아이스티,"

                }
            } else if (peachiceTea2) {
                btnEvent2(megaCoffee9)
                peachiceTea2 = false
                megaCoffeeSum--
                megaCoffee9Text = ""
            }

        }




        backCoffee1.setOnClickListener {
            if (!dolceLatte2) {
                if (backCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (backCoffeeSum < 3) {
                    btnEvent1(backCoffee1)
                    dolceLatte2 = true
                    backCoffeeSum++
                    backCoffee1Text = "돌체라떼,"

                }
            } else if (dolceLatte2) {
                btnEvent2(backCoffee1)
                dolceLatte2 = false
                backCoffeeSum--
                backCoffee1Text = ""
            }


        }

        backCoffee2.setOnClickListener {
            if (!vanillaLatte3) {
                if (backCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (backCoffeeSum < 3) {
                    btnEvent1(backCoffee2)
                    vanillaLatte3 = true
                    backCoffeeSum++
                    backCoffee2Text = "바닐라라떼,"

                }
            } else if (vanillaLatte3) {
                btnEvent2(backCoffee2)
                vanillaLatte3 = false
                backCoffeeSum--
                backCoffee2Text = ""
            }


        }

        backCoffee3.setOnClickListener {
            if (!caramel3) {
                if (backCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (backCoffeeSum < 3) {
                    btnEvent1(backCoffee3)
                    caramel3 = true
                    backCoffeeSum++
                    backCoffee3Text = "카라멜마끼아토,"

                }
            } else if (caramel3) {
                btnEvent2(backCoffee3)
                caramel3 = false
                backCoffeeSum--
                backCoffee3Text = ""

            }


        }

        backCoffee4.setOnClickListener {
            if (!sweetMilk2) {
                if (backCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (backCoffeeSum < 3) {
                    btnEvent1(backCoffee4)
                    sweetMilk2 = true
                    backCoffeeSum++
                    backCoffee4Text = "스윗밀크티,"

                }
            } else if (sweetMilk2) {
                btnEvent2(backCoffee4)
                sweetMilk2 = false
                backCoffeeSum--
                backCoffee4Text = ""
            }


        }

        backCoffee5.setOnClickListener {
            if (!strawberryBacksuccino) {
                if (backCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (backCoffeeSum < 3) {
                    btnEvent1(backCoffee5)
                    strawberryBacksuccino = true
                    backCoffeeSum++
                    backCoffee5Text = "완전딸기빽스치노,"

                }
            } else if (strawberryBacksuccino) {
                btnEvent2(backCoffee5)
                strawberryBacksuccino = false
                backCoffeeSum--
                backCoffee5Text = ""
            }


        }

        backCoffee6.setOnClickListener {
            if (!watermelon) {
                if (backCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (backCoffeeSum < 3) {
                    btnEvent1(backCoffee6)
                    watermelon = true
                    backCoffeeSum++
                    backCoffee6Text = "완전수박,"
                }
            } else if (watermelon) {
                btnEvent2(backCoffee6)
                watermelon = false
                backCoffeeSum--
                backCoffee6Text = ""
            }


        }

        backCoffee7.setOnClickListener {
            if (!lemonAid) {
                if (backCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (backCoffeeSum < 3) {
                    btnEvent1(backCoffee7)
                    lemonAid = true
                    backCoffeeSum++
                    backCoffee7Text = "레몬에이드,"

                }
            } else if (lemonAid) {
                btnEvent2(backCoffee7)
                lemonAid = false
                backCoffeeSum--
                backCoffee7Text = ""
            }



        }

        backCoffee8.setOnClickListener {
            if (!peachOlongTea) {
                if (backCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (backCoffeeSum < 3) {
                    btnEvent1(backCoffee8)
                    peachOlongTea = true
                    backCoffeeSum++
                    backCoffee8Text = "피치우롱티,"

                }
            } else if (peachOlongTea) {
                btnEvent2(backCoffee8)
                peachOlongTea = false
                backCoffeeSum--
                backCoffee8Text = ""
            }


        }

        backCoffee9.setOnClickListener {
            if (!fondantcino) {
                if (backCoffeeSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (backCoffeeSum < 3) {
                    btnEvent1(backCoffee9)
                    fondantcino = true
                    backCoffeeSum++
                    backCoffee9Text = "퐁당치노,"

                }
            } else if (fondantcino) {
                btnEvent2(backCoffee9)
                fondantcino = false
                backCoffeeSum--
                backCoffee9Text = ""
            }
        }




        gongCha1.setOnClickListener {
                if (!blackMilkTea) {
                    if (gongChaSum >= 3) {
                        Toast.makeText(
                            requireContext(),
                            "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (gongChaSum < 3) {
                        btnEvent1(gongCha1)
                        blackMilkTea = true
                        gongChaSum++
                        gongCha1Text = "블랙밀크티,"

                    }
                } else if (blackMilkTea) {
                    btnEvent2(gongCha1)
                    blackMilkTea = false
                    gongChaSum--
                    gongCha1Text = ""

                }

            Log.d("gongCha", gongCha.toString())
            Log.d("sum", gongChaSum.toString())
            }


        gongCha2.setOnClickListener {
            if (!brownSuga) {
                if (gongChaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (gongChaSum < 3) {
                    btnEvent1(gongCha2)
                    brownSuga = true
                    gongChaSum++
                    gongCha2Text = "브라운슈가쥬얼리밀크티,"

                }
            } else if (brownSuga) {
                btnEvent2(gongCha2)
                brownSuga = false
                gongChaSum--
                gongCha2Text = ""
            }
            Log.d("brownSuga", brownSuga.toString())
        }

        gongCha3.setOnClickListener {
            if (!taroMilkTea) {
                if (gongChaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (gongChaSum < 3) {
                    btnEvent1(gongCha3)
                    taroMilkTea = true
                    gongChaSum++
                    gongCha3Text = "타로밀크티,"

                }
            } else if (taroMilkTea) {
                btnEvent2(gongCha3)
                taroMilkTea = false
                gongChaSum--
                gongCha3Text = ""
            }
        }

        gongCha4.setOnClickListener {
            if (!mangoSmoothie) {
                if (gongChaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (gongChaSum < 3) {
                    btnEvent1(gongCha4)
                    mangoSmoothie = true
                    gongChaSum++
                    gongCha4Text = "망고스무디,"
                }
            } else if (mangoSmoothie) {
                btnEvent2(gongCha4)
                mangoSmoothie = false
                gongChaSum--
                gongCha4Text = ""
            }
        }

        gongCha5.setOnClickListener {
            if (!mintChocoSmoothie) {
                if (gongChaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (gongChaSum < 3) {
                    btnEvent1(gongCha5)
                    mintChocoSmoothie = true
                    gongChaSum++
                    gongCha5Text = "민트초코스무디,"

                }
            } else if (mintChocoSmoothie) {
                btnEvent2(gongCha5)
                mintChocoSmoothie = false
                gongChaSum--
                gongCha5Text = ""
            }
        }

        gongCha6.setOnClickListener {
            if (!strawberryCookie) {
                if (gongChaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (gongChaSum < 3) {
                    btnEvent1(gongCha6)
                    strawberryCookie = true
                    gongChaSum++
                    gongCha6Text = "딸기쿠키스무디,"

                }
            } else if (strawberryCookie) {
                btnEvent2(gongCha6)
                strawberryCookie = false
                gongChaSum--
                gongCha6Text = ""
            }

        }

        gongCha7.setOnClickListener {
            if (!chocolateSmoothie) {
                if (gongChaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (gongChaSum < 3) {
                    btnEvent1(gongCha7)
                    chocolateSmoothie = true
                    gongChaSum++
                    gongCha7Text = "초코멜로스무디,"

                }
            } else if (chocolateSmoothie) {
                btnEvent2(gongCha7)
                chocolateSmoothie = false
                gongChaSum--
                gongCha7Text = ""
            }

        }

        gongCha8.setOnClickListener {
            if (!realChocolateTea) {
                if (gongChaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (gongChaSum < 3) {
                    btnEvent1(gongCha8)
                    realChocolateTea = true
                    gongChaSum++
                    gongCha8Text = "리얼초콜릿밀크티,"

                }
            } else if (realChocolateTea) {
                btnEvent2(gongCha8)
                realChocolateTea = false
                gongChaSum--
                gongCha8Text = ""
            }
        }

        gongCha9.setOnClickListener {
            if (!chocoCookieSmoothie) {
                if (gongChaSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (gongChaSum < 3) {
                    btnEvent1(gongCha9)
                    chocoCookieSmoothie = true
                    gongChaSum++
                    gongCha9Text = "초코쿠앤크스무디,"

                }
            } else if (chocoCookieSmoothie) {
                btnEvent2(gongCha9)
                chocoCookieSmoothie = false
                gongChaSum--
                gongCha9Text = ""
            }
        }



        coffeeBean1.setOnClickListener {
            if (!englishLatte) {
                if (coffeeBeanSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coffeeBeanSum < 3) {
                    btnEvent1(coffeeBean1)
                    englishLatte = true
                    coffeeBeanSum++
                    coffeeBean1Text = "잉글리쉬라떼,"

                }
            } else if (englishLatte) {
                btnEvent2(coffeeBean1)
                englishLatte = false
                coffeeBeanSum--
                coffeeBean1Text = ""
            }
        }

        coffeeBean2.setOnClickListener {
            if (!vinspener) {
                if (coffeeBeanSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coffeeBeanSum < 3) {
                    btnEvent1(coffeeBean2)
                    vinspener = true
                    coffeeBeanSum++
                    coffeeBean2Text = "빈슈페너,"

                }
            } else if (vinspener) {
                btnEvent2(coffeeBean2)
                vinspener = false
                coffeeBeanSum--
                coffeeBean2Text = ""
            }
        }

        coffeeBean3.setOnClickListener {
            if (!dalgonaCreamLatte) {
                if (coffeeBeanSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coffeeBeanSum < 3) {
                    btnEvent1(coffeeBean3)
                    dalgonaCreamLatte = true
                    coffeeBeanSum++
                    coffeeBean3Text = "달고나크림라떼,"

                }
            } else if (dalgonaCreamLatte) {
                btnEvent2(coffeeBean3)
                dalgonaCreamLatte = false
                coffeeBeanSum--
                coffeeBean3Text = ""
            }


        }

        coffeeBean4.setOnClickListener {
            if (!cafeSua) {
                if (coffeeBeanSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coffeeBeanSum < 3) {
                    btnEvent1(coffeeBean4)
                    cafeSua = true
                    coffeeBeanSum++
                    coffeeBean4Text = "카페수아,"
                }
            } else if (cafeSua) {
                btnEvent2(coffeeBean4)
                cafeSua = false
                coffeeBeanSum--
                coffeeBean4Text = ""
            }
        }

        coffeeBean5.setOnClickListener {
            if (!lemonCitronTea) {
                if (coffeeBeanSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coffeeBeanSum < 3) {
                    btnEvent1(coffeeBean5)
                    lemonCitronTea = true
                    coffeeBeanSum++
                    coffeeBean5Text = "레몬유자캐모마일티,"

                }
            } else if (lemonCitronTea) {
                btnEvent2(coffeeBean5)
                lemonCitronTea = false
                coffeeBeanSum--
                coffeeBean5Text = ""
            }


        }

        coffeeBean6.setOnClickListener {
            if (!sparklingTea) {
                if (coffeeBeanSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coffeeBeanSum < 3) {
                    btnEvent1(coffeeBean6)
                    sparklingTea = true
                    coffeeBeanSum++
                    coffeeBean6Text = "스파클링스웨디쉬레몬티,"

                }
            } else if (sparklingTea) {
                btnEvent2(coffeeBean6)
                sparklingTea = false
                coffeeBeanSum--
                coffeeBean6Text = ""
            }

        }

        coffeeBean7.setOnClickListener {
            if (!whiteForest) {
                if (coffeeBeanSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coffeeBeanSum < 3) {
                    btnEvent1(coffeeBean7)
                    whiteForest = true
                    coffeeBeanSum++
                    coffeeBean7Text = "화이트포레스트,"

                }
            } else if (whiteForest) {
                btnEvent2(coffeeBean7)
                whiteForest = false
                coffeeBeanSum--
                coffeeBean7Text = ""
            }


        }

        coffeeBean8.setOnClickListener {
            if (!veriveryIB) {
                if (coffeeBeanSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coffeeBeanSum < 3) {
                    btnEvent1(coffeeBean8)
                    veriveryIB = true
                    coffeeBeanSum++
                    coffeeBean8Text = "베리베리IB,"

                }
            } else if (veriveryIB) {
                btnEvent2(coffeeBean8)
                veriveryIB = false
                coffeeBeanSum--
                coffeeBean8Text = ""
            }
        }

        coffeeBean9.setOnClickListener {
            if (!mangobananaIB) {
                if (coffeeBeanSum >= 3) {
                    Toast.makeText(
                        requireContext(),
                        "즐겨먹는 음료는 최대 3개 까지 중복 선택이 가능합니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                } else if (coffeeBeanSum < 3) {
                    btnEvent1(coffeeBean9)
                    mangobananaIB = true
                    coffeeBeanSum++
                    coffeeBean9Text = "망고바나나IB,"

                }
            } else if (mangobananaIB) {
                btnEvent2(coffeeBean9)
                mangobananaIB = false
                coffeeBeanSum--
                coffeeBean9Text = ""
            }
        }



        binding.saveBtn.setOnClickListener {
//            setUser(token.toString(), photoPath)

                val drink1 : String = starbuks1Text + starbucks2Text +starbuks3Text + starbuks4Text +
                        starbuks5Text + starbuks6Text + starbuks7Text + starbuks8Text + starbuks9Text
                var drink11 = ""
                var drink111 = ""
                if (drink1.isNotEmpty()) {
                    drink11 = drink1.substring(0, drink1.length -1)
                    drink111 = drink11 + tab
                }
                val arry11 = drink111

                val drink2 : String = twosomePlace1Text + twosomePlace2Text + twosomePlace3Text + twosomePlace4Text +
                        twosomePlace5Text + twosomePlace6Text + twosomePlace7Text + twosomePlace8Text + twosomePlace9Text
                var drink12 = ""
                var drink122 = ""
                if (drink2.isNotEmpty()) {
                    drink12 = drink2.substring(0, drink2.length-1)
                    drink122 = drink12 + tab
                }
                val arry12 = drink122

                val drink3 : String = pallbarSet1Text + pallbarSet2Text + pallbarSet3Text + pallbarSet4Text +
                        pallbarSet5Text + pallbarSet6Text + pallbarSet7Text + pallbarSet8Text + pallbarSet9Text
                var drink13 = ""
                var drink133 = ""
                if (drink3.isNotEmpty()) {
                    drink13 = drink3.substring(0, drink3.length-1)
                    drink133 = drink13 + tab
                }
                val arry13 = drink133

                val drink4 : String = ediya1Text + ediya2Text + ediya3Text + ediya4Text + ediya5Text +
                        ediya6Text + ediya7Text + ediya8Text + ediya9Text
                var drink14 = ""
                var drink144 = ""
                if (drink4.isNotEmpty()) {
                    drink14 = drink4.substring(0, drink4.length-1)
                    drink144 = drink14 + tab
                }
                val arry14 = drink144

                val drink5 : String =  megaCoffee1Text +
                        megaCoffee2Text + megaCoffee3Text + megaCoffee4Text + megaCoffee5Text +
                        megaCoffee6Text + megaCoffee7Text + megaCoffee8Text + megaCoffee9Text
                var drink15 = ""
                var drink155 = ""
                if (drink5.isNotEmpty()) {
                    drink15 = drink5.substring(0, drink5.length-1)
                    drink155 = drink15 + tab
                }
                val arry15 = drink155

                val drink6 : String = backCoffee1Text + backCoffee2Text + backCoffee3Text + backCoffee4Text +
                        backCoffee5Text + backCoffee6Text + backCoffee7Text + backCoffee8Text + backCoffee9Text
                var drink16 = ""
                var drink166 = ""
                if (drink6.isNotEmpty()) {
                    drink16 = drink6.substring(0, drink6.length-1)
                    drink166 = drink16 + tab
                }
                val arry16 = drink166

                val drink7 : String = gongCha1Text + gongCha2Text + gongCha3Text + gongCha4Text +
                        gongCha5Text + gongCha6Text + gongCha7Text + gongCha8Text + gongCha9Text
                var drink17 = ""
                var drink177 = ""
                if (drink7.isNotEmpty()) {
                    drink17 = drink7.substring(0, drink7.length-1)
                    drink177 = drink17 + tab
                }
                val arry17 = drink177

                val drink8 : String = coffeeBean1Text + coffeeBean2Text + coffeeBean3Text + coffeeBean4Text +
                        coffeeBean5Text + coffeeBean6Text + coffeeBean7Text + coffeeBean8Text + coffeeBean9Text
                var drink18 = ""
                var drink188 = ""
                if (drink8.isNotEmpty()) {
                    drink18 = drink8.substring(0, drink8.length-1)
                    drink188 = drink18 + tab
                }
                val arry18 = drink188

                val CATE : String = coffeeText + nonCoffeeText + milkTeaText + blendingText +
                        aidText + smoothieText + fromageText + iceText
                var cate11 = ""
                if (CATE.isNotEmpty()) {
                    cate11 = CATE.substring(0, CATE.length-1)
                }
                val keyword : String = sweetText + freshText + refreshingText + savoryText +
                        saltyText + healthyText + sugarText + fruitText + largeText + popularText
                var keywordText = ""
                if (keyword.isNotEmpty()) {
                    keywordText = keyword.substring(0, keyword.length-1)
                }

                val brand : String = brand1Text + brand2Text + brand3Text + brand4Text +
                        brand5Text + brand6Text + brand7Text + brand8Text
                var brandText = ""
                if (brand.isNotEmpty()) {
                    brandText = brand.substring(0, brand.length-1)
                }
                val a = arry11 + arry12 + arry13 + arry14 + arry15 + arry16 + arry17 + arry18
                var aa = ""
                if (a.isNotEmpty()) {
                    aa = a.substring(0, a.length - 1)
                }
         
            var check : Boolean = false
            var check2 : Boolean = false
            var check3 : Boolean = false
            var check4 : Boolean = false
            var check5 : Boolean = false
            var check6 : Boolean = false
            var check7 : Boolean = false
            var check8 : Boolean = false


                if (starbuks) {
                    if (starbuksSum < 1) {
                        check = false
                    } else if (starbuksSum > 0) {
                        check = true
                    }
                } else if (!starbuks) {
                    check = true
                }
                if (twosomePlace) {
                    if (twosomePlaceSum < 1) {
                        check2 = false
                    } else if (twosomePlaceSum > 0) {
                        check2 = true
                    }
                } else if (!twosomePlace) {
                    check2 = true
                }
                if (pallbarSet) {
                    if (pallbarSetSum < 1) {
                        check3 = false
                    } else if (pallbarSetSum > 0) {
                        check3 = true
                    }
                } else if (!pallbarSet) {
                    check3 = true
                }
                if (ediya) {
                    if (ediyaSum < 1) {
                        check4 = false
                    } else if (ediyaSum > 0) {
                        check4 = true
                    }
                } else if (!ediya) {
                        check4 = true
                    }
                if (megaCoffee) {
                    if (megaCoffeeSum < 1) {
                        check5 = false
                    } else if (megaCoffeeSum > 0) {
                        check5 = true
                  }
                } else if (!megaCoffee) {
                    check5 = true
                }
                if (backCoffee) {
                    if (backCoffeeSum < 1) {
                        check6 = false
                    } else if (backCoffeeSum > 0) {
                        check6 = true
                  }
                } else if (!backCoffee) {
                    check6 = true
                }
                if (gongCha) {
                    if (gongChaSum < 1) {
                        check7 = false
                    } else if (gongChaSum > 0) {
                        check7 = true
                  }
                } else if(!gongCha) {
                        check7 = true
                    }
                if (coffeeBean) {
                    if (coffeeBeanSum < 1) {
                        check8 = false
                    } else if(coffeeBeanSum > 0) {
                        check8 = true
                    }
                }  else if (!coffeeBean) {
                    check8 = true
                }


            if (!check || !check2 || !check3 || !check4 || !check5 || !check6 || !check7 || !check8){
                Toast.makeText(
                    requireContext(),
                    "카페 브랜드 선택 시\n 즐격먹는 음료 1개 이상 선택해야합니다. ",
                    Toast.LENGTH_SHORT
                ).show()
            } else  {
                Log.d("categoryText", cate11)
                Log.d("categoryText", keywordText)
                Log.d("categoryText", brandText)
                Log.d("categoryText", aa)
                Toast.makeText(requireContext(), "정보를 저장했습니다.", Toast.LENGTH_SHORT).show()
            }





        }

        binding.checkBtnId.setOnClickListener {
//            val intent = Intent(activity, FindPw1Activity::class.java)
//            startActivity(intent)
            val intent = MyInfoFragmentDirections.actionMyInfoFragmentToTestFragment()
            findNavController().navigate(intent)
        }

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.settingSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //체크된 상태 취소 시 코드
            } else {
                //체크된 상태로 만들 시 코드
            }
        }

        binding.imageBtn.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext().applicationContext,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                    requireContext().applicationContext,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                    requireContext().applicationContext,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ), permissionsRequestCode
                )
            }

            val imgArray: Array<String> = arrayOf("카메라", "갤러리")
            val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())

            builder.setTitle("사진 선택")
            builder.setItems(imgArray, DialogInterface.OnClickListener { _, which ->
                if (which == 0) {

                    if (ContextCompat.checkSelfPermission(
                            requireContext().applicationContext,
                            Manifest.permission.CAMERA
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            requestPermission()
                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            val camera: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            if (camera.resolveActivity(requireActivity().packageManager) != null) {
                                var photoFile: File? = null
                                try {
                                    photoFile = createImageFile()
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                                if (photoFile != null) {
                                    val photoURI: Uri = FileProvider.getUriForFile(
                                        requireContext(),
                                        "com.mandeum.dessert39.fileprovider",
                                        photoFile
                                    )

                                    camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                                    startActivityForResult(camera, cameraRequestCode)
                                }
                            }
                        }
                    } else if (ContextCompat.checkSelfPermission(
                            requireContext().applicationContext,
                            Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
                        val camera: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        if (camera.resolveActivity(requireActivity().packageManager) != null) {

                            try {
                                photoFile = createImageFile()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                            val photoURI: Uri = FileProvider.getUriForFile(
                                requireContext(),
                                "com.mandeum.dessert39.fileprovider",
                                photoFile
                            )

                            camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                            startActivityForResult(camera, cameraRequestCode)
                        }
                    }
                } else if (which == 1) {

                    if (ContextCompat.checkSelfPermission(
                            requireContext().applicationContext,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(
                            requireContext().applicationContext,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {

                        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            && shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                        ) {

                            requestPermission()

                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            && !shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                        ) {

                            val gallery: Intent = Intent(
                                Intent.ACTION_PICK,
                                MediaStore.Images.Media.INTERNAL_CONTENT_URI
                            )
                            startActivityForResult(gallery, galleryRequestCode)

                        }

                    } else if (ContextCompat.checkSelfPermission(
                            requireContext().applicationContext,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(
                            requireContext().applicationContext,
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) == PackageManager.PERMISSION_GRANTED
                    ) {
//                        val gallery: Intent =
//                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
//                        startActivityForResult(gallery, galleryRequestCode)
                        val intent = Intent(Intent.ACTION_PICK)
                        intent.type = MediaStore.Images.Media.CONTENT_TYPE
                        intent.type = "image/*"
                        startActivityForResult(intent, galleryRequestCode)
                    }
                }
            })
            val dialog = builder.create()
            dialog.show()
        }


        return binding.root
    }

    private fun requestPermission() {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("권한 설정")
            .setMessage("권한 거절로 인해 일부기능이 제한됩니다.")
            .setPositiveButton("권한 설정하러 가기", DialogInterface.OnClickListener { _, _ ->
                try {
                    val appSetIntent: Intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                        .setData(Uri.parse("package:" + requireActivity().packageName))
                    startActivity(appSetIntent)
                } catch (e: IOException) {
                    e.printStackTrace()
                    val appManageIntent: Intent =
                        Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
                    startActivity(appManageIntent)
                }
            })
            .setNegativeButton("취소", DialogInterface.OnClickListener { _, _ ->

            })
            .create().show()
    }

    private fun getTempFolder(): File {
        val directoryFolder =
            File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "sample-take-image")
        directoryFolder.mkdirs()
        return directoryFolder
    }


    @SuppressLint("SimpleDateFormat")
    private fun createImageFile(): File {
        val timeStamp: String = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        } else {
            TODO("VERSION.SDK_INT < N")
        }

        val imageFileName: String = "JPEG_" + timeStamp + "_"
        val storageDir: File? =
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image: File = File.createTempFile(
            imageFileName,
            ".jpg",
            storageDir
        )


        photoPath = image.absolutePath

        return image
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == AppCompatActivity.RESULT_OK && requestCode == cameraRequestCode) {

            val file = createImageFile()


            if (Build.VERSION.SDK_INT >= 29) {

                val source: ImageDecoder.Source =
                    ImageDecoder.createSource(requireContext().contentResolver, Uri.fromFile(file))

                try {
                    val bitmap = ImageDecoder.decodeBitmap(source)
//                    val imageSrc = saveImage(bitmap)
                    binding.image11.setImageBitmap(bitmap)

                } catch (e: IOException) {
                    e.printStackTrace()
                }
            } else if (Build.VERSION.SDK_INT <= 28) {
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(
                        requireActivity().contentResolver,
                        Uri.fromFile(file)
                    )
                    if (bitmap != null) {
                        binding.image11.setImageBitmap(bitmap)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        } else if (resultCode == AppCompatActivity.RESULT_OK && requestCode == galleryRequestCode) {
            binding.image11.setImageURI(data?.data)

        }
    }


    companion object {
        private val RESOURCE_DIR = "/any-directory"
    }

    fun saveImage(myBitmap: Bitmap): String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val imgDir = File(
            (Environment.getExternalStorageDirectory()).toString() + RESOURCE_DIR
        )

        if (!imgDir.exists()) {
            imgDir.mkdirs()
        }

        try {
            val file = File(
                imgDir,
                String.format("%s.%s", Calendar.getInstance().timeInMillis, "jpg")
            )
            file.createNewFile()
            val fo = FileOutputStream(file)
            fo.write(bytes.toByteArray())
            MediaScannerConnection.scanFile(
                requireContext(),
                arrayOf(file.path),
                arrayOf("image/jpeg"),
                null
            )
            fo.close()

            return file.absolutePath
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }

        return ""
    }


    fun setUser(TOKEN: String, file: String) {

        thread(start = true) {
            val user: UserImageModel = ServerApi.cardChoice(TOKEN, file)
            val userModel = UserImageModel(user.Connection, user.errCode, user.userImg)

            if (userModel.Connection) {
                if (userModel.errCode == "0000") {
                    thread.runOnUiThread {
                        Toast.makeText(requireContext(), "통신 성공", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                thread.runOnUiThread {
                    Toast.makeText(
                        requireContext(), "connection = ${userModel.Connection}\n연결 실패",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            Log.d("error", user.errCode)
        }

    }

    private fun brandSelect(selected: Boolean, textView1: TextView, textView2: TextView,
                            textView3: TextView,
                            button1: Button,
                            button2: Button,
                            button3: Button,
                            button4: Button,
                            button5: Button,
                            button6: Button,
                            button7: Button,
                            button8: Button,
                            button9: Button
    ) : Boolean {

        var select: Boolean = selected

        if (!selected) {
            button1.isGone = true
            button2.isGone = true
            button3.isGone = true
            button4.isGone = true
            button5.isGone = true
            button6.isGone = true
            button7.isGone = true
            button8.isGone = true
            button9.isGone = true
            textView1.isGone = true
            textView2.isGone = true
            textView3.isGone = true
            select = true

        } else if (selected) {
            button1.isGone = false
            button2.isGone = false
            button3.isGone = false
            button4.isGone = false
            button5.isGone = false
            button6.isGone = false
            button7.isGone = false
            button8.isGone = false
            button9.isGone = false
            textView1.isGone = false
            textView2.isGone = false
            textView3.isGone = false
            select = false
        }

        return select
    }

    private fun btnEvent1 (button: Button) {
        button.setBackgroundResource(R.drawable.background_radius_black_button2)
        button.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.white
            )
        )
    }

    private fun btnEvent2 (button: Button) {
        button.setBackgroundResource(R.drawable.background_radius_white_button2)
        button.setTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.black2
            )
        )
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}