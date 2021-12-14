package com.mandeum.dessert39.Main.My39.Receipt

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
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
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandeum.dessert39.databinding.FragmentReceiptDetailBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


class ReceiptDetailFragment : Fragment() {

    private var _binding : FragmentReceiptDetailBinding? = null
    private val binding get() = _binding!!

    companion object {
        const val REQUEST_CODE = 1
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentReceiptDetailBinding.inflate(layoutInflater)

        binding.imageDownload.setOnClickListener {

           requestRuntimePermissions()

        }

        val receiptModel : ArrayList<ReceiptDetailModel> = ArrayList()
        val rvAdapter = ReceiptDetailAdapter(requireContext(), receiptModel)
        val rv : RecyclerView = binding.receiptDetailRecyclerView

        binding.findImage.setOnClickListener {
            requireActivity().onBackPressed()
        }

        rv.adapter = rvAdapter

        rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        receiptModel.add(ReceiptDetailModel("오리지널도코룔(S)", "1", "4,800"))

        receiptModel.add(ReceiptDetailModel("오리지널도코룔(S)", "1", "4,800"))

        receiptModel.add(ReceiptDetailModel("오리지널도코룔(S)", "1", "4,800"))
        receiptModel.add(ReceiptDetailModel("오리지널도코룔(S)", "1", "4,800"))
        receiptModel.add(ReceiptDetailModel("오리지널도코룔(S)", "1", "4,800"))
        receiptModel.add(ReceiptDetailModel("오리지널도코룔(S)", "1", "4,800"))






        return binding.root
    }






    private fun getScreenShotFromView(v: View): Bitmap? {

        var screenshot: Bitmap? = null
        try {
            screenshot = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshot)
            canvas.drawColor(Color.WHITE)

            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("fail", "fail" + e.message)
        }
        // return the bitmap
        return screenshot
    }

    private fun saveMediaToStorage(bitmap: Bitmap) {
        val filename = "${System.currentTimeMillis()}.jpg"

        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requireActivity().contentResolver?.also { resolver ->

                val contentValues = ContentValues().apply {

                    // putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imageUri: Uri? = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(requireContext() , "갤러리 저장" , Toast.LENGTH_SHORT).show()
        }
    }


    private fun requestRuntimePermissions() {
        if (ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED) {
            val bitmap = getScreenShotFromView(binding.captureLayout)
            if (bitmap != null) {
                saveMediaToStorage(bitmap)
            }
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
            ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE)
            showDialogToGetPermission()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {

        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    val bitmap = getScreenShotFromView(binding.captureLayout)
                    if (bitmap != null) {
                        saveMediaToStorage(bitmap)
                    }
                    Toast.makeText(requireContext(), "권한이 설정되었습니다", Toast.LENGTH_LONG).show()
                } else {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) && ActivityCompat.shouldShowRequestPermissionRationale(
                            requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                    ) {
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            REQUEST_CODE
                        )
                        ActivityCompat.requestPermissions(
                            requireActivity(),
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            REQUEST_CODE
                        )
                    } else {
                        showDialogToGetPermission()
                    }
                }
            }
        }
    }



    private fun showDialogToGetPermission() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("권한 설정")
            .setMessage("권한설정이 거부되었습니다. " +
                    "권한을 설정하로 가시겠습니까?")

        builder.setPositiveButton("확인") { dialogInterface, i ->
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", "com.mandeum.dessert39", null))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)   // 6
        }
        builder.setNegativeButton("취소") { dialogInterface, i ->
            // ignore
        }
        val dialog = builder.create()
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}