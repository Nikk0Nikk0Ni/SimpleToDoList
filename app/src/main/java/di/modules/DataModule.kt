package di.modules

import android.app.Application
import com.niko.data.Repository.ListItemRepositoryImplementation
import com.niko.data.database.MainDB
import com.niko.data.database.MainDBDao
import com.niko.domain.Repository.ListItemRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import di.annotation.ApplicationScope
@Module
interface DataModule {
    @Binds
    @ApplicationScope
    fun provideListItemRepository(repository: ListItemRepositoryImplementation): ListItemRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideMainDb(application: Application): MainDBDao {
            return MainDB.getInstance(application).getDao()
        }
    }
}