package tw.lifehackers.sample.activityplayground.extension

fun Any.simpleNameAndHash(): String {
    val simpleName = javaClass.simpleName
    val hashCode = hashCode().toString(16)
    return "$simpleName@$hashCode"
}