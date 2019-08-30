package tw.lifehackers.sample.activityplayground

abstract class IdentityStore<T> {
    private val map : HashMap<T, MutableList<Int>> = hashMapOf()

    protected abstract fun getGroup(obj: Any): T

    fun getSerialNumber(obj: Any, providedGroup: T? = null): Int {
        val group = providedGroup ?: getGroup(obj)
        val list = map[group]
        val hash = obj.hashCode()
        if (list == null) {
            map[group] = mutableListOf(hash)
            return 0
        }

        val index = list.indexOf(hash)
        if (index != -1) {
            return index
        }
        val generatedIndex = list.size
        list.add(hash)
        return generatedIndex
    }

    fun putIdentity(obj: Any, providedGroup: T? = null) = getSerialNumber(obj, providedGroup)

    fun getSimpleNameAndSerialNumber(obj: Any, providedGroup: T? = null): String {
        val serialNumber = getSerialNumber(obj, providedGroup)
        return "${obj.javaClass.simpleName}@$serialNumber"
    }
}

class IdentityStores {
    companion object {
        val classStore = object : IdentityStore<Class<*>>() {
            override fun getGroup(obj: Any): Class<*> = obj.javaClass
        }

        val namedStore = object : IdentityStore<String>() {
            override fun getGroup(obj: Any): String = "default_group"
        }
    }
}

