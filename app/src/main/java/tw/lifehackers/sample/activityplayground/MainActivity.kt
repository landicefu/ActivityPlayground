package tw.lifehackers.sample.activityplayground

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import tw.lifehackers.sample.activityplayground.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ActivityNavigationView {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IdentityStores.classStore.putIdentity(this)
        IdentityStores.namedStore.putIdentity(taskId, "taskId")
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            navigationView = this@MainActivity
        }
    }

    override fun provideActivity(): Activity = this
}
