package tw.lifehackers.sample.activityplayground

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import tw.lifehackers.sample.activityplayground.ActivityFlags.Companion.FLAGS
import tw.lifehackers.sample.activityplayground.extension.tryOrNull

interface ActivityNavigationView {

    val flags: MutableMap<Int, Boolean>

    fun provideActivity(): Activity

    fun launchActivity(activityClass: Class<out Activity>) {
        val activity = provideActivity()
        activity.startActivity(Intent(activity.applicationContext, activityClass).apply {
            this@ActivityNavigationView.flags.forEach {
                val (index, value) = it
                if (value) addFlags(FLAGS[index].second)
            }
        })
    }

    fun launchMainActivityInStandardMode() = launchActivity(MainActivity::class.java)
    fun launchSecondActivityInStandardMode() = launchActivity(SecondActivity::class.java)
    fun launchSingleTaskActivity() = launchActivity(SingleTaskActivity::class.java)
    fun launchSingleInstanceActivity() = launchActivity(SingleInstanceActivity::class.java)

    fun getActivityInfo(): String {
        val activity = provideActivity()
        val identity = IdentityStores.classStore.getSimpleNameAndSerialNumber(activity)
        val taskIdentity = IdentityStores.namedStore.getSerialNumber(activity.taskId, "taskId")
        val info = StringBuilder("$identity on task #$taskIdentity")
        tryOrNull {
            val am = activity.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            am.appTasks[0].taskInfo
        }?.let {
            info.append("(${it.numActivities} activities)")
        }
        return info.toString()
    }
}