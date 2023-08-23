package com.angelina.wallet_application.worker

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.camera.core.ExperimentalGetImage
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.angelina.wallet_application.MainActivity
import com.angelina.wallet_application.R

private const val CHANNEL_ID = "12345678"

@ExperimentalGetImage
class NotificationWorker(private val appContext: Context, private val params: WorkerParameters) :
    Worker(appContext, params) {

    @SuppressLint("MissingPermission")
    override fun doWork(): Result {
        val intent = Intent(appContext, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(appContext, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val builder = NotificationCompat.Builder(appContext, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Кошелек")
            .setContentText("Уже ходили за покупками? Не забудьте открыть наше приложение!")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val name = CHANNEL_ID
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance)
        val notificationManager: NotificationManager =
            appContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
        with(NotificationManagerCompat.from(appContext)) {
            notify(12123, builder.build())
        }

        Log.e("NotificationWorker", params.inputData.getString("title") ?: "")

        return Result.success(
            Data.Builder()
                .putString("title", "12312enkwdfnks")
                .build()
        )
    }
}