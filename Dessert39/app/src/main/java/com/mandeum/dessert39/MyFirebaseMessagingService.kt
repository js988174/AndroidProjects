package com.mandeum.dessert39

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mandeum.dessert39.Intro.MainActivity
import com.mandeum.dessert39.Main.HomeActivity
import com.mandeum.dessert39.SharedPreferences.MyApplication
import androidx.navigation.fragment.NavHostFragment





class MyFirebaseMessagingService: FirebaseMessagingService() {
    private val logTag = "MyFirebaseMessagingService.Tag"

    override fun onNewToken(token: String) {
        val shared = getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val editor = shared.edit()
        editor.putString("FirebaseToken", token)
        editor.apply()
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(logTag, "remoteMessage = $remoteMessage")
        Log.d(logTag, "remoteMessage.from = ${remoteMessage.from}")
        Log.d(logTag, "remoteMessage.data = ${remoteMessage.data}")
        sendNotification(remoteMessage)


        // log 받으면,push 받을 시 현재 플래그먼트 뱃지 활성화
//        HomeActivity().findViewById<Imge>(R.id.adfs).isGone = false
    }

    @SuppressLint("UnspecifiedImmutableFlag", "WrongConstant")
    private fun sendNotification(remoteMessage: RemoteMessage) {
        // RequestCode, Id를 고유값으로 지정하여 알림이 개별 표시되도록 함
        val uniId: Int = (System.currentTimeMillis() / 7).toInt()
        // 일회용 PendingIntent
        // PendingIntent : Intent 의 실행 권한을 외부의 어플리케이션에게 위임한다.
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) // Activity Stack 을 경로만 남긴다. A-B-C-D-B => A-B
        val pendingIntent = PendingIntent.getActivity(this, uniId, intent, PendingIntent.FLAG_ONE_SHOT)
        // 알림 채널 이름
        val channelId = getString(R.string.firebase_notification_channel_id)
        // 알림 소리
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        // 알림에 대한 UI 정보와 작업을 지정한다.
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(remoteMessage.data["title"])
            .setContentText(remoteMessage.data["body"])
//            .setContentTitle(remoteMessage.notification?.title) // 제목
//            .setContentText(remoteMessage.notification?.body) // 타이틀
//            .setContentText(null) // 메시지 내용
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setSound(soundUri) // 알림 소리
            .setContentIntent(pendingIntent) // 알림 실행 시 Intent
            .setNumber(1)


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
        // 오레오 버전 이후에는 채널이 필요하다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "Notice",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.setShowBadge(false)
            notificationManager.createNotificationChannel(channel)
        }

        // 알림 생성
        notificationManager.notify(uniId, notificationBuilder.build())

    }


}