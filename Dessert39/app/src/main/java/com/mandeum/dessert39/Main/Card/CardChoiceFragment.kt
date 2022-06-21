package com.mandeum.dessert39.Main.Card

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.Main.Card.Slide.*
import com.mandeum.dessert39.databinding.FragmentCardChoiceBinding
import java.io.IOException
import java.text.SimpleDateFormat
import java.io.File


class CardChoiceFragment : Fragment() {


    private val PERM_STOREGE = 99
    private val PERM_CAMERA = 100
    val REQ_CAMERA = 101
    val REQ_STORAGE = 102
    val permissionsRequestCode = 103
    var realUri: Uri? = null
    private lateinit var findUserCard: String

    private var _binding: FragmentCardChoiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraImage: String


    var cardItem: MutableList<CardTypeModel>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentCardChoiceBinding.inflate(layoutInflater)

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

//        binding.plusIcon.setOnClickListener {

//        }

//                val cardChoiceFragment = CardChoiceFragmentDirections.actionCardChoiceFragmentToCardFragment()
//                it.findNavController().navigate(cardChoiceFragment)

//        if (findUserCard == "no") {
//            changeBtn.isGone = true
//            design_change_card.isGone = true
//        } else if (findUserCard == "yes") {
//            textView36.text = "변경"
//            changeBtn.isGone = false
//            design_change_card.isGone = false
//        }


        cardItem = mutableListOf()


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


        val rvAdapter1 = CardTypeAdapter(requireContext(), cardTypeModel, "Change", null)
        val rv: RecyclerView = binding.rv1
        rv.adapter = rvAdapter1

        cardItem?.let {
            rvAdapter1.TypeItem
        }

        rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)



        binding.plusIcon.setOnClickListener {

            if (ContextCompat.checkSelfPermission(requireContext().applicationContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireContext().applicationContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(requireContext().applicationContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE), permissionsRequestCode)

            }

            var items = arrayOf("카메라", "갤러리", )
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("선택하세요.")

            alertDialog.setItems(items, DialogInterface.OnClickListener { _, i ->

                if (i == 0) {
                        if (ContextCompat.checkSelfPermission(requireContext().applicationContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        ) {    if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {

                            requestPermission ()

                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {

                            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                                var photoFile: File? = null
                                try {
                                    photoFile = createImageUri2()
                                } catch (e: IOException) {
                                    e.printStackTrace()
                                }
                                if (photoFile != null) {
                                    val photoURI: Uri = FileProvider.getUriForFile(requireContext(),
                                        "com.mandeum.dessert39.fileprovider",
                                        photoFile)
                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                                    startActivityForResult(intent, REQ_CAMERA)
                                }
                                }
                            }
                        }  else if (ContextCompat.checkSelfPermission(requireContext().applicationContext, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                            val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            if (camera.resolveActivity(requireActivity().packageManager) != null) {
                                var photoFile : File? = null
                                try {
                                    photoFile = createImageUri2()
                                } catch (e : IOException) {
                                    e.printStackTrace()
                                }
                                if (photoFile != null) {
                                    val photoURI : Uri = FileProvider.getUriForFile(requireContext(), "com.mandeum.dessert39.fileprovider", photoFile)
                                    camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                                    startActivityForResult(camera, REQ_CAMERA)
                                }
                            }
                        }

                } else if (i == 1) {
                    if (ContextCompat.checkSelfPermission(requireContext().applicationContext,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(requireContext().applicationContext,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    ) {

                        if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            && shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                        ) {

                            requestPermission()

                        } else if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            && !shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                        ) {

                            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                            startActivityForResult(intent, REQ_STORAGE)

                        }

                    } else if (ContextCompat.checkSelfPermission(requireContext().applicationContext,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(requireContext().applicationContext,
                            Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    ) {

                        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                        startActivityForResult(intent, REQ_STORAGE)

                    }
                }


                //  여백 눌러도 창 안없어지게
                alertDialog.setCancelable(false)

            })

            val dialog = alertDialog.create()

            dialog.show()

        }



        return binding.root
    }

    private fun requestPermission() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            permissionGranted(requestCode)
//        } else {
//            val isAllPermissionsGranted = permissions.all {
//                ContextCompat.checkSelfPermission(requireContext().applicationContext,
//                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
//            }
//            if (isAllPermissionsGranted) {
//                permissionGranted(requestCode)
//            } else {
//                ActivityCompat.requestPermissions(requireActivity(), permissions, requestCode)
//            }
//        }
    }

//    private fun permissionGranted(requestCode: Int) {
//        when (requestCode) {
//            PERM_STOREGE -> setViews()
//            PERM_CAMERA -> openCamera()
//        }
//    }

//    private fun permissionDenied(requestCode: Int) {
//        when (requestCode) {
//            PERM_STOREGE -> {
//                Toast.makeText(requireContext(), "외부저장소 권한을 승인해야 앱을 사용할 수 있습니다.", Toast.LENGTH_LONG)
//                    .show()
//            }
//            PERM_CAMERA -> Toast.makeText(requireContext(),
//                "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.",
//                Toast.LENGTH_LONG).show()
//        }
//    }

//    fun setViews() {
//        activity?.let {
//            if (ContextCompat.checkSelfPermission(it.applicationContext,
//                    android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
//            ) {
//                requirePermissions(arrayOf(android.Manifest.permission.CAMERA), PERM_CAMERA)
//            }
//        }
//    }
//
//    fun openCamera() {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (intent.resolveActivity(requireActivity().packageManager) != null) {
//            var photoFile: File? = null
//            try {
//                photoFile = createImageUri2()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//            if (photoFile != null) {
//                val photoURI: Uri = FileProvider.getUriForFile(requireContext(),
//                    "com.mandeum.dessert39.fileprovider",
//                    photoFile)
//                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
//                startActivityForResult(intent, REQ_CAMERA)
//            }
//        }
//
////            createImageUri(newFileName(), "image/jpg")?.let { uri ->
////                realUri = uri
////                intent.putExtra(MediaStore.EXTRA_OUTPUT, realUri)
////                startActivityForResult(intent, REQ_CAMERA)
////            }
//
//    }

    fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, REQ_STORAGE)

    }

    fun newFileName(): String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())

        return "$filename.jpg"
    }

    fun createImageUri(filename: String, mimeType: String): Uri? {
        var values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
        return requireContext().contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            values)
    }

    private fun createImageUri2(): File {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())

        val storageDir: File? =
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image: File = File.createTempFile(filename,
            ".jpg",
            storageDir
        )

        cameraImage = image.absolutePath

        return image
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val photoUri = File(cameraImage)
            var image: Bitmap? = null
            if (Build.VERSION.SDK_INT >= 29) {
                when (requestCode) {
                    REQ_CAMERA -> {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(
                            requireContext().contentResolver,
                            Uri.fromFile(photoUri))
                        try {
                            image = ImageDecoder.decodeBitmap(source)
                            if (image != null) {
                                val direction =
                                    CardChoiceFragmentDirections.actionCardChoiceFragmentToCardChargeFragment(
                                        "",
                                        cameraImage,
                                        "",
                                        "")
                                findNavController().navigate(direction)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            } else if (Build.VERSION.SDK_INT <= 28) {
                when (requestCode) {
                    REQ_CAMERA -> {
                        try {
                            image =
                                MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,
                                    Uri.fromFile(photoUri))
                            if (image != null) {
                                val direction =
                                    CardChoiceFragmentDirections.actionCardChoiceFragmentToCardChargeFragment(
                                        "",
                                        cameraImage,
                                        "",
                                        "")
                                findNavController().navigate(direction)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        } else if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQ_STORAGE -> {
                        val image: Uri? = data?.data
                        val albumImage: String = image.toString()
                        //  binding.registeredCard.setImageURI(uri)
                        val direction = CardChoiceFragmentDirections.actionCardChoiceFragmentToCardChargeFragment(
                                "",
                                "",
                                "",
                                albumImage)
                        findNavController().navigate(direction)

                }
            }
        }
    }

    fun showDialog() {


    }


//    fun requirePermissions(permissions: Array<String>, requestCode: Int) {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            permissionGranted(requestCode)
//        } else {
//            val isAllPermissionsGranted = permissions.all {
//                ContextCompat.checkSelfPermission(requireContext().applicationContext,
//                    Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
//            }
//            if (isAllPermissionsGranted) {
//                permissionGranted(requestCode)
//            } else {
//                ActivityCompat.requestPermissions(requireActivity(), permissions, requestCode)
//            }
//        }
//    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {

        if (requestCode == PERM_CAMERA) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                val camera: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (camera.resolveActivity(requireActivity().packageManager) != null) {
                    var photoFile: File? = null
                    try {
                        photoFile = createImageUri2()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    if (photoFile != null) {
                        val photoURI: Uri = FileProvider.getUriForFile(requireContext(),
                            "com.mandeum.dessert39.fileprovider",
                            photoFile)
                        camera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(camera, REQ_CAMERA)
                    }
                }
            }
        } else if (requestCode == PERM_STOREGE) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                val gallery: Intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
                startActivityForResult(gallery, REQ_STORAGE)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    }