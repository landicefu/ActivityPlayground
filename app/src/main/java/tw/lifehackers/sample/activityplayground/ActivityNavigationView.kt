package tw.lifehackers.sample.activityplayground

import android.app.Activity
import android.content.Intent
import tw.lifehackers.sample.activityplayground.extension.simpleNameAndHash

interface ActivityNavigationView {

    fun provideActivity(): Activity

    fun launchActivityInStandardMode() {
        val activity = provideActivity()
        activity.startActivity(Intent(activity.applicationContext, activity::class.java))
    }

    fun getActivityIdentity(): String = provideActivity().simpleNameAndHash()
}