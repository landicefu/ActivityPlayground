package tw.lifehackers.sample.activityplayground

import android.app.Activity
import android.content.Intent

interface ActivityNavigationView {
    companion object {
        val FLAGS = listOf(
            "single top" to Intent.FLAG_ACTIVITY_SINGLE_TOP,
            "clear top" to Intent.FLAG_ACTIVITY_CLEAR_TOP,
            "clear task" to Intent.FLAG_ACTIVITY_CLEAR_TASK,
            "new document" to Intent.FLAG_ACTIVITY_NEW_DOCUMENT,
            "new task" to Intent.FLAG_ACTIVITY_NEW_TASK
        )
    }

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

    fun getActivityIdentity(): String {
        val activity = provideActivity()
        val identity = IdentityStores.classStore.getSimpleNameAndSerialNumber(activity)
        val taskIdentity = IdentityStores.namedStore.getSerialNumber(activity.taskId, "taskId")
        return "$identity on task #$taskIdentity"
    }
}