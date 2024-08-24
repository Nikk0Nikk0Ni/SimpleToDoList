package di.components

import android.app.Application
import com.niko.todoapp.Activity.MainActivity
import com.niko.todoapp.Activity.ShopItemActivity
import com.niko.todoapp.Fragments.ShopItemFragment
import dagger.BindsInstance
import dagger.Component
import di.annotation.ApplicationScope
import di.modules.DataModule
import di.modules.ViewModelModule
@ApplicationScope
@Component(modules = [DataModule::class,ViewModelModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: ShopItemActivity)
    fun inject(fragment: ShopItemFragment)
    @Component.Factory
    interface Factory{
        fun create(@BindsInstance application: Application): MainComponent
    }
}