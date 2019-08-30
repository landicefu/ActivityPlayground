package tw.lifehackers.sample.activityplayground

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Process

class App : Application() {
    companion object {
        lateinit var instance: App private set

        @JvmStatic
        fun restart() {
            val pendingIntent = PendingIntent.getActivity(
                instance, 1,
                Intent(instance, MainActivity::class.java),
                PendingIntent.FLAG_CANCEL_CURRENT
            )
            val alarmManager = instance.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 300, pendingIntent)
            Handler().postDelayed({
                Process.killProcess(Process.myPid())
            }, 50)
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}