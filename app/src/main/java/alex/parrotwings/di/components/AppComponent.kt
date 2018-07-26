package alex.parrotwings.di.components

import alex.parrotwings.presentations.ui.activities.TransactionActivity
import alex.parrotwings.presentations.ui.activities.AccountActivity
import alex.parrotwings.presentations.ui.activities.LoginActivity
import alex.parrotwings.di.modules.AppModule
import alex.parrotwings.di.modules.DataModule
import alex.parrotwings.di.modules.PresenterModule
import alex.parrotwings.presentations.ui.activities.RegistrationActivity
import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (PresenterModule::class),
    (DataModule::class)])
interface AppComponent {

    fun inject(app: Application)
    fun inject(productActivity: LoginActivity)
    fun inject(regActivity: RegistrationActivity)
    fun inject(accountActivity: AccountActivity)
    fun inject(transactionActivity: TransactionActivity)
}