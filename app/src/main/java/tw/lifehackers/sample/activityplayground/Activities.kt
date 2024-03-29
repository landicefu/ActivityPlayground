package tw.lifehackers.sample.activityplayground

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import tw.lifehackers.sample.activityplayground.databinding.ActivityMainBinding

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), ActivityNavigationView {

    override val flags: MutableMap<Int, Boolean> = mutableMapOf()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IdentityStores.classStore.putIdentity(this)
        IdentityStores.namedStore.putIdentity(taskId, "taskId")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            navigationView = this@BaseActivity
        }
    }

    override fun provideActivity(): Activity = this

    override fun onResume() {
        super.onResume()
        binding.invalidateAll()
    }
}

class MainActivity : BaseActivity()
class SecondActivity : BaseActivity()
class SingleTaskActivity : BaseActivity()
class SingleInstanceActivity : BaseActivity()

