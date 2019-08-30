package tw.lifehackers.sample.activityplayground

import android.content.Intent

class ActivityFlags {

    companion object {
        @JvmStatic
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

}