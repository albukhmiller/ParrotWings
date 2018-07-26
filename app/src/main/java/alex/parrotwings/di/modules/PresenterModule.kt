package alex.parrotwings.di.modules

import alex.parrotwings.presentations.mvp.presenterImpls.AccountPresenterImpl
import alex.parrotwings.presentations.mvp.presenterImpls.LoginPresenterImpl
import alex.parrotwings.presentations.mvp.presenterImpls.RegistrationPresenterImpl
import alex.parrotwings.presentations.mvp.presenterImpls.TransactionPresenterImpl
import alex.parrotwings.presentations.mvp.presenters.AccountPresenter
import alex.parrotwings.presentations.mvp.presenters.LoginPresenter
import alex.parrotwings.presentations.mvp.presenters.RegistrationPresenter
import alex.parrotwings.presentations.mvp.presenters.TransactionPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun providesLoginPresenter(presenterImpl: LoginPresenterImpl): LoginPresenter = presenterImpl

    @Provides
    fun providesRegistrationPresenter(presenterImpl: RegistrationPresenterImpl): RegistrationPresenter = presenterImpl

    @Provides
    fun providesAccountPresenter(presenterImpl: AccountPresenterImpl): AccountPresenter = presenterImpl

    @Provides
    fun providesTransactionPresenter(presenterImpl: TransactionPresenterImpl): TransactionPresenter = presenterImpl
}