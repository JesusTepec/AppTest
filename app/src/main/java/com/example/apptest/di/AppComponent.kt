package di

import dagger.Component
import ui.LoginActivity
import javax.inject.Singleton

@Component(modules = [AppRepositoryModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: LoginActivity)
}