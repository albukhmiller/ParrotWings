package alex.parrotwings.presentations.mvp.presenterImpls

import alex.parrotwings.domain.intetactors.AuthorizationInteractor
import alex.parrotwings.data.local.sharedPreference.LocalRepository
import alex.parrotwings.models.AuthInfo
import alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners.BaseInteractorListener
import alex.parrotwings.presentations.mvp.presenters.LoginPresenter
import alex.parrotwings.presentations.mvp.views.LoginView
import javax.inject.Inject

class LoginPresenterImpl @Inject constructor(private val loginInteractor: AuthorizationInteractor,
                                             private val localRepository: LocalRepository) : BasePresenterImpl<LoginView>(), LoginPresenter, BaseInteractorListener {

    init {
        loginInteractor.attachListener(this)
    }

    override fun login(user: AuthInfo) {
        loginInteractor.loginUser(user)
    }

    override fun <T> onSuccessLoading(data: T?, status: Int?) {

        when (status) {
            401 -> mView?.showMessageError()
            201 -> {
                mView?.hideMessageError()
                localRepository.saveIdToken("idToken", data.toString())
                mView?.onSuccessLogin(data.toString())
            }
        }
    }

    override fun onFailureLoading() {
        mView?.onFailureLogin()
    }
}