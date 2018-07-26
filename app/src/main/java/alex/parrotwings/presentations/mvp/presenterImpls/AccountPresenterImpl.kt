package alex.parrotwings.presentations.mvp.presenterImpls

import alex.parrotwings.domain.intetactors.LoaderUserDataInteractor
import alex.parrotwings.domain.sharedPreference.LocalRepository
import alex.parrotwings.models.Transaction
import alex.parrotwings.models.User
import alex.parrotwings.presentations.mvp.presenterImpls.interactorListeners.LoaderUserDataInteractorListener
import alex.parrotwings.presentations.mvp.presenters.AccountPresenter
import alex.parrotwings.presentations.mvp.views.AccountView
import java.util.*
import javax.inject.Inject


class AccountPresenterImpl @Inject constructor(private val loaderUserData: LoaderUserDataInteractor,
                                               private val localRepository: LocalRepository) : BasePresenterImpl<AccountView>(), AccountPresenter, LoaderUserDataInteractorListener {

    init {
        loaderUserData.attachListener(this)
    }

    private val timerUpdater = Timer()

    private var user: User? = null

    override fun getUser() = user!!

    override fun logout() {
        localRepository.deleteIdToken()
    }

    override fun loadUserInfo() {
        timerUpdater.schedule(loaderUserData, 0, 1000)
    }

    override fun stopTimer() {
        timerUpdater.cancel()
    }

    override fun loadTransactions() {
        loaderUserData.getAllTransaction()
    }

    override fun <T> onSuccessLoading(data: T?, status: Int?) {
        if (status == 401) {
            localRepository.deleteIdToken()
            mView?.openAuthorization()
        } else {
            this.user = data as User
            mView?.onSuccessLoadUserData(data as User)
        }
    }

    override fun onFailureLoading() {
        mView?.onFailureLoadUserData()
    }

    override fun onSuccessLoadTransactions(transactions: ArrayList<Transaction>) {
        mView?.onSuccessLoadTransaction(transactions)
    }

    override fun onFailureTransactions() {
        mView?.onFailureLoadTransactions()
    }
}