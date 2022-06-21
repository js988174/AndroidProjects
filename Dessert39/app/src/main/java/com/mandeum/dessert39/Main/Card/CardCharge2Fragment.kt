package com.mandeum.dessert39.Main.Card

import android.Manifest
import android.Manifest.permission.CAMERA
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
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Login.ServerApi.Model.Card.CardChoiceModel
import com.mandeum.dessert39.Login.ServerApi.Model.Info.UserImageModel
import com.mandeum.dessert39.Login.ServerApi.ServerApi
import com.mandeum.dessert39.Main.Card.Slide.CardListModel
import com.mandeum.dessert39.Main.Card.Slide.CardTypeAdapter
import com.mandeum.dessert39.Main.Card.Slide.CardTypeModel
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.R
import com.mandeum.dessert39.databinding.FragmentCardCharge3Binding
import java.io.File
import java.io.IOException
import java.util.*
import kotlin.concurrent.thread


class CardCharge2Fragment : Fragment() {

    private lateinit var binding: FragmentCardCharge3Binding
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
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardCharge3Binding.inflate(layoutInflater)
        val cardImage : ImageView = binding.cardImg
        var cardItem: MutableList<CardTypeModel>? = null
        var photoFile = createImageFile()

        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val token = shared.getString("LoginToken", "")

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }



//        val direction = CardCharge2FragmentDirections.actionCardCharge2FragmentToCardFragment("",
//            CardImage.toString()
//        )
//        findNavController().navigate(direction)

        binding.changeBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_cardCharge2Fragment_to_cardFragment)
        }

        cardItem = mutableListOf()
        thread = context as HomeActivity

        val item1 = CardListModel("https://ifh.cc/g/uFcjhM.png")
        val item2 = CardListModel("https://ifh.cc/g/uFcjhM.png")
        val item3 = CardListModel("https://ifh.cc/g/uFcjhM.png")
        val item4 = CardListModel("https://ifh.cc/g/uFcjhM.png")
        val item5 = CardListModel("https://ifh.cc/g/uFcjhM.png")
        val item6 = CardListModel("https://ifh.cc/g/uFcjhM.png")

        val listModel: MutableList<CardListModel> = mutableListOf()
        listModel.add(item1)
        listModel.add(item2)
        listModel.add(item3)
        listModel.add(item4)

        val listModel2: MutableList<CardListModel> = mutableListOf()
        listModel2.add(item3)
        listModel2.add(item4)
        listModel2.add(item5)
        listModel2.add(item6)

        val listModel3: MutableList<CardListModel> = mutableListOf()
        listModel3.add(item5)
        listModel3.add(item6)
        listModel3.add(item1)
        listModel3.add(item2)
        val listModel4: MutableList<CardListModel> = mutableListOf()
        listModel4.add(item5)
        listModel4.add(item6)
        listModel4.add(item1)
        listModel4.add(item2)
        val listModel5: MutableList<CardListModel> = mutableListOf()
        listModel5.add(item5)
        listModel5.add(item6)
        listModel5.add(item1)
        listModel5.add(item2)
        val listModel6: MutableList<CardListModel> = mutableListOf()
        listModel6.add(item5)
        listModel6.add(item6)
        listModel6.add(item1)
        listModel6.add(item2)

        val cardTypeModel = mutableListOf<CardTypeModel>()

        cardTypeModel.add(CardTypeModel("D39 Special", listModel))
        cardTypeModel.add(CardTypeModel("Basic Design", listModel2))
        cardTypeModel.add(CardTypeModel("Season Design", listModel3))
        cardTypeModel.add(CardTypeModel("Season Design", listModel4))
        cardTypeModel.add(CardTypeModel("Season Design", listModel5))
        cardTypeModel.add(CardTypeModel("Season Design", listModel6))


        val rvAdapter1 = CardTypeAdapter(requireContext(), cardTypeModel, type = "select", null)
        val rv: RecyclerView = binding.rv1
        rv.adapter = rvAdapter1

        cardItem.let {
            rvAdapter1.TypeItem
        }

        rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)




        binding.customCardLayout.setOnClickListener {
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
            val builder = AlertDialog.Builder(requireContext())

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
                            if (photoFile != null) {
                                val photoURI: Uri = FileProvider.getUriForFile(
                                    requireContext(),
                                    "com.mandeum.dessert39.fileprovider",
                                    photoFile!!
                                )
                                camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                                startActivityForResult(camera, cameraRequestCode)
                            }
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

                        val gallery: Intent =
                            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                        startActivityForResult(gallery, galleryRequestCode)

                    }
                }
            })
            val dialog = builder.create()
            dialog.show()
        }

        return binding.root
    }
    private fun requestPermission() {
        AlertDialog.Builder(requireContext())
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

            val file = File(photoPath)

            if (Build.VERSION.SDK_INT >= 29) {

                val source: ImageDecoder.Source =
                    ImageDecoder.createSource(requireContext().contentResolver, Uri.fromFile(file))

                try {
                    val bitmap = ImageDecoder.decodeBitmap(source)

                    val imageSrc = createImageFile()
                    binding.cardImg.setImageBitmap(bitmap)


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
                        val imageSrc = createImageFile()
                        val shared = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
                        val token = shared.getString("LoginToken", "")

                        setUser(token.toString(), imageSrc)
                        binding.cardImg.setImageBitmap(bitmap)


                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

        } else if (resultCode == AppCompatActivity.RESULT_OK && requestCode == galleryRequestCode) {
            binding.cardImg.setImageURI(data?.data)

        }
    }



    fun setUser(TOKEN: String, file: File) {

        thread(start = true) {
            val user: UserImageModel = ServerApi.cardChoice(TOKEN, file)
            val cardModel = UserImageModel(user.Connection, user.errCode, user.userImg)

            Log.d("error", user.errCode)
            Log.d("image_rute", user.userImg)

            if (cardModel.Connection) {
                if (cardModel.errCode == "0000") {
                    thread.runOnUiThread {
                        Toast.makeText(requireContext(), "통신 성공", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }

}