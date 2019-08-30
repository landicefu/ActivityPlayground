package tw.lifehackers.sample.activityplayground

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import tw.lifehackers.sample.activityplayground.extension.tryOrNull

interface ActivityNavigationView {
    companion object {
        val FLAGS = listOf(
            "single top" to Intent.FLAG_ACTIVITY_SINGLE_TOP,
            "clear top" to Intent.FLAG_ACTIVITY_CLEAR_TOP,
            "clear task" to Intent.FLAG_ACTIVITY_CLEAR_TASK,
            "new document" to Intent.FLAG_ACTIVITY_NEW_DOCUMENT,
            "new task" to Intent.FLAG_ACTIVITY_NEW_TASK,
            "no history" to Intent.FLAG_ACTIVITY_NO_HISTORY,
            "multiple task" to Intent.FLAG_ACTIVITY_MULTIPLE_TASK,
            "forward result" to Intent.FLAG_ACTIVITY_FORWARD_RESULT,
            "previous is top" to Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP,
            "exclude from recents" to Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS,
            "brought to front" to Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT,
            "reset task if needed" to Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED,
            "launched from history" to Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY,
            "no user action" to Intent.FLAG_ACTIVITY_NO_USER_ACTION,
            "reorder to front" to Intent.FLAG_ACTIVITY_REORDER_TO_FRONT,
            "no animation" to Intent.FLAG_ACTIVITY_NO_ANIMATION,
            "task on home" to Intent.FLAG_ACTIVITY_TASK_ON_HOME,
            "retain in recents" to Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS,
            "launch adjacent" to Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT,
            "match external" to Intent.FLAG_ACTIVITY_MATCH_EXTERNAL
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