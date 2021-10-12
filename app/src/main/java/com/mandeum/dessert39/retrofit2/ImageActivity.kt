package com.mandeum.dessert39.retrofit2

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mandeum.dessert39.R
import kotlinx.android.synthetic.main.activity_image.*
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread


class ImageActivity : AppCompatActivity() {

    var mp:MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        button.setOnClickListener {
            thread {
                val url = URL("https://is3-ssl.mzstatic.com/image/thumb/Video125/v4/a6/32/96/a6329655-9a7c-cdfb-c94a-5bf3c7d43269/source/512x512bb.jpg")
                val conn = url.openConnection() as HttpURLConnection

                val bitmap = BitmapFactory.decodeStream(conn.inputStream)
                runOnUiThread{
                    imageView.setImageBitmap(bitmap)
                }
            }
        }

        button2.setOnClickListener {
            if(mp == null){
                val uri = Uri.parse("https://itunes.apple.com/us/movie/captain-marvel/id1453792625?uo=4")
                mp = MediaPlayer.create(this, uri)
                mp?.start()
            }
        }

        button3.setOnClickListener {
            if(mp != null){
                mp?.stop()
                mp = null
            }
        }

        button4.setOnClickListener {
            if(videoView.isPlaying == false){
                val uri = Uri.parse("https://video-ssl.itunes.apple.com/itunes-assets/Video123/v4/d0/82/35/d08235df-f75c-a7da-559d-1b0622687af1/mzvf_3013349080441859076.640x356.h264lc.U.p.m4v")
                videoView.setVideoURI(uri)
                videoView.start()
            }
        }

        button5.setOnClickListener {
            if(videoView.isPlaying == true){
                videoView.stopPlayback()
            }
        }
    }
}

