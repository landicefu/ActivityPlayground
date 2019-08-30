package tw.lifehackers.sample.activityplayground.extension

fun Any.simpleNameAndHash(): String {
    val simpleName = javaClass.simpleName
    val hashCode = hashCode().toString(16)
    return "$simpleName@$hashCode"
}

fun <T> Any.tryOrNull(block: () -> T): T? {
    return try {
        block()
    } catch (e: Exception) {
        null
    }
}