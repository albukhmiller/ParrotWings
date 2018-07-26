package alex.parrotwings.presentations.mvp.presenterImpls

import alex.parrotwings.domain.intetactors.AuthorizationInteractor
import alex.parrotwings.domain.sharedPreference.LocalRepository
import alex.parrotwings.models.AuthInfo
import alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners.BaseInteractorListener
import alex.parrotwings.presentations.mvp.presenters.RegistrationPresenter
import alex.parrotwings.presentations.mvp.views.RegistrationView
import javax.inject.Inject

class RegistrationPresenterImpl @Inject constructor(private val redistrationInteractor: AuthorizationInteractor,
                                                    private val custodian: LocalRepository) : BasePresenterImpl<RegistrationView>(), RegistrationPresenter, BaseInteractorListener {

    init {
        redistrationInteractor.attachListener(this)
    }

    override fun redistrationNewUser(user: AuthInfo) {
        redistrationInteractor.registrationUser(user)
    }

    override fun <T> onSuccessLoading(data: T?, status: Int?) {

        when (status) {
            400 -> mView?.showMessageError()
            201 -> {
                mView?.hideMessageError()
                custodian.saveIdToken("idToken", data as String)
                mView?.onSuccessRegistration()
            }
        }
    }

    override fun onFailureLoading() {
        mView?.onFailureRegistration()
    }
}