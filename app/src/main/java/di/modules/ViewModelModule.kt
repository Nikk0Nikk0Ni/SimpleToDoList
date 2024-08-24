package di.modules

import androidx.lifecycle.ViewModel
import com.niko.todoapp.ViewModels.AddEditViewModel
import com.niko.todoapp.ViewModels.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import di.annotation.ViewModelKey
@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddEditViewModel::class)
    fun bindAddEditViewModel(viewModel: AddEditViewModel): ViewModel
}