package tw.lifehackers.sample.activityplayground

import android.app.Activity
import android.content.Intent

interface ActivityNavigationView {

    fun provideActivity(): Activity

    fun launchActivity(activityClass: Class<out Activity>, vararg additionalFlags: Int) {
        val activity = provideActivity()
        activity.startActivity(Intent(activity.applicationContext, activityClass).apply {
            additionalFlags.forEach { addFlags(it) }
        })
    }

    fun launchMainActivityInStandardMode() = launchActivity(MainActivity::class.java)
    fun launchSecondActivityInStandardMode() = launchActivity(SecondActivity::class.java)
    fun launchMainActivityInSingleTopMode() = launchActivity(MainActivity::class.java, Intent.FLAG_ACTIVITY_SINGLE_TOP)
    fun launchSecondActivityInSingleTopMode() = launchActivity(SecondActivity::class.java, Intent.FLAG_ACTIVITY_SINGLE_TOP)
    fun launchSingleTaskActivity() = launchActivity(SingleTaskActivity::class.java)
    fun launchSingleInstanceActivity() = launchActivity(SingleInstanceActivity::class.java)

    fun getActivityIdentity(): String {
        val activity = provideActivity()
        val identity = IdentityStores.classStore.getSimpleNameAndSerialNumber(activity)
        val taskIdentity = IdentityStores.namedStore.getSerialNumber(activity.taskId, "taskId")
        return "$identity on task #$taskIdentity"
    }
}