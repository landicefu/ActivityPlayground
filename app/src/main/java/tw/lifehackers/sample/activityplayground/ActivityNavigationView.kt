package tw.lifehackers.sample.activityplayground

import android.app.Activity
import android.content.Intent

interface ActivityNavigationView {

    fun provideActivity(): Activity

    fun launchMainActivityInStandardMode() {
        val activity = provideActivity()
        activity.startActivity(Intent(activity.applicationContext, MainActivity::class.java))
    }

    fun launchSecondActivityInStandardMode() {
        val activity = provideActivity()
        activity.startActivity(Intent(activity.applicationContext, SecondActivity::class.java))
    }

    fun getActivityIdentity(): String {
        val activity = provideActivity()
        val identity = IdentityStores.classStore.getSimpleNameAndSerialNumber(activity)
        val taskIdentity = IdentityStores.namedStore.getSerialNumber(activity.taskId, "taskId")
        return "$identity on task #$taskIdentity"
    }
}